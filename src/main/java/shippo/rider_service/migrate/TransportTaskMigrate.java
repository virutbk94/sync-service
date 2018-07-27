package shippo.rider_service.migrate;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.io.rider.oldDAO;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.Metadata;
import shippo.rider_service.entities.v0.TaskHistory;
import shippo.rider_service.entities.v0.TransportationTask;
import shippo.rider_service.entities.v1.MetaFields;
import shippo.rider_service.entities.v1.TransportTask;

import java.util.*;

public class TransportTaskMigrate extends Thread{
    Logger LOG = LoggerFactory.getLogger(TransportTaskMigrate.class);

    public void run() {
        LOG.info("Start time : " + new Date(System.currentTimeMillis()));

        int max_id = oldDAO.getMaxId("\"TransportTask\"");
        for (int i = 0; i <= max_id; i += 20000) {
            int max = i + 20000;
            miniBatchProcess(i, max);
        }

        LOG.info("Insert data done at : " + new Date(System.currentTimeMillis()));
    }

    private void miniBatchProcess(int i, int max) {
        EbeanServer oldDb = PostgressDbConf.getOldDb();
        EbeanServer newDb = PostgressDbConf.getRiderDb();
        List<TransportTask> lsTransportTasks = oldDb.find(TransportTask.class)
                .where().and()
                .ge("id", i)
                .lt("id", max)
                .endAnd()
                .findList();
        List<MetaFields> metaFieldsList = oldDb.find(MetaFields.class)
                .where().and()
                .lt("objectId", max)
                .ge("objectId", i)
                .eq("objectType", "TransportTask")
                .endAnd().findList();

        LOG.info("Read old data done at : " + new Date(System.currentTimeMillis()));
        Map<Integer, List<Metadata>> metadataMap = new HashMap<>();
        for (MetaFields metaFields : metaFieldsList) {
            Integer key = metaFields.getObjectId();
            if (metadataMap.get(key) == null) {
                metadataMap.put(key, new ArrayList<>());
            }
            metadataMap.get(key).add(Mapping.mapMetaField2MetaData(metaFields));
        }

        metaFieldsList.clear();

        List<Pair<TransportationTask, List<TaskHistory>>> lsPairs = new ArrayList<>();

        for (TransportTask transportTask : lsTransportTasks) {
            if (transportTask.getVersion() % 2 == 0) continue;
            TransportationTask transportationTask = Mapping.mapTransportTask2TransportationTask(transportTask);
            transportationTask.setMetadata(metadataMap.get(transportationTask.getId()));
            List<TaskHistory> taskHistorySet = Mapping.mapTransportTask2TaskHistories(transportTask);
            lsPairs.add(new Pair<>(transportationTask, taskHistorySet));
        }
        metadataMap.clear();

        List<TransportationTask> transportationTaskList = new ArrayList<>();
        List<TaskHistory> taskHistoryList = new ArrayList<>();

        Transaction transaction = newDb.beginTransaction();
        transaction.setBatchMode(true);
        transaction.setBatchSize(2000);

        for (Pair<TransportationTask, List<TaskHistory>> pair : lsPairs) {
            transportationTaskList.add(pair.getKey());
            taskHistoryList.addAll(pair.getValue());
        }
        lsPairs.clear();
        LOG.info("Insert data at : " + new Date(System.currentTimeMillis()) + " with size : " + transportationTaskList.size());
        newDb.insertAll(transportationTaskList);
        transportationTaskList.clear();
        newDb.insertAll(taskHistoryList);
        taskHistoryList.clear();
        transaction.commit();
        transaction.end();
    }

    public static void main(String[] args) {
        new TransportTaskMigrate().run();
    }
}
