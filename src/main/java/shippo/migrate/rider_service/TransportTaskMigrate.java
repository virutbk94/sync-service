package shippo.migrate.rider_service;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import javafx.util.Pair;
import shippo.global.entities.v0.rider.*;
import shippo.global.entities.v1.*;
import shippo.global.Mapping;
import shippo.global.PostgressDbConf;

import java.util.*;

public class TransportTaskMigrate {
    public void run() {
        System.out.println("Start time : " + new Date(System.currentTimeMillis()));

        EbeanServer oldDb = PostgressDbConf.getOldDb();
        EbeanServer riderDb = PostgressDbConf.getRiderDb();

        List<TransportTask> lsTransportTasks = oldDb.find(TransportTask.class)
//                .where().lt("id",10000)
                .findList();
        List<MetaFields> metaFieldsList = oldDb.find(MetaFields.class)
                .where().eq("objectType", "TransportTask").findList();

        System.out.println("Read old data done at : " + new Date(System.currentTimeMillis()));
        Map<Integer, List<Metadata>> metadataMap = new HashMap<>();
        for (MetaFields metaFields : metaFieldsList) {
            Integer key = metaFields.getObjectId();
            metadataMap.computeIfAbsent(key, k -> new ArrayList<>());
            metadataMap.get(key).add(Mapping.mapMetaFieldToMetaData(metaFields));
        }

        List<Pair<TransportationTask, Set<TaskHistory>>> lsPairs = new ArrayList<>();

        for (TransportTask transportTask : lsTransportTasks) {
            if (transportTask.getVersion() % 2 == 0) continue;
            TransportationTask transportationTask = Mapping.mapTransportTaskToTransportationTask(transportTask);
            transportationTask.setMetadata(metadataMap.get(transportationTask.getId()));
            Set<TaskHistory> taskHistorySet = Mapping.mapTaskToTaskHistories(transportTask);
            lsPairs.add(new Pair<>(transportationTask, taskHistorySet));
        }
        List<TransportationTask> transportationTaskList = new ArrayList<>();
        List<TaskHistory> taskHistoryList = new ArrayList<>();

        Transaction transaction = riderDb.beginTransaction();
        transaction.setBatchMode(true);
        transaction.setBatchSize(2000);

        for (Pair<TransportationTask, Set<TaskHistory>> pair : lsPairs) {
            transportationTaskList.add(pair.getKey());
            taskHistoryList.addAll(pair.getValue());

        }
        System.out.println("Insert data at : " + new Date(System.currentTimeMillis()));
        riderDb.insertAll(transportationTaskList);
        riderDb.insertAll(taskHistoryList);
        transaction.commit();
        transaction.end();
        System.out.println("Insert data done at : " + new Date(System.currentTimeMillis()));
    }

    public static void main(String[] args) {
        new TransportTaskMigrate().run();
    }
}
