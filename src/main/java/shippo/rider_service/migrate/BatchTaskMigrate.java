package shippo.rider_service.migrate;

import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.io.rider.oldDAO;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.Metadata;
import shippo.rider_service.entities.v0.RiderShift;
import shippo.rider_service.entities.v1.MetaFields;
import shippo.rider_service.entities.v1.TaskBatch;

import java.util.*;

/*
 * @TODO
 * Split loaded batch  to fixed number, dont load all record at once
 * */
public class BatchTaskMigrate extends Thread {
    private Logger LOG = LoggerFactory.getLogger(BatchTaskMigrate.class);

    public void run() {
        System.out.println("Start time : " + new Date(System.currentTimeMillis()));

        int max_id = oldDAO.getMaxId("\"TaskBatch\"");
        for (int i = 0; i <= max_id; i += 20000) {
            int max = i + 20000;
            miniBatchProcess(i, max);
        }
        LOG.info("Insert data done at : " + new Date(System.currentTimeMillis()));
    }

    public void miniBatchProcess(int i, int max) {

        List<TaskBatch> taskBatchList = PostgressDbConf.getOldDb().find(TaskBatch.class)
                .where()
                .and()
                .ge("id", i)
                .lt("id", max)
                .endAnd()
                .findList();
        List<MetaFields> metaFieldsList = PostgressDbConf.getOldDb().find(MetaFields.class)
                .where().and()
                .lt("objectId", max)
                .ge("objectId", i)
                .eq("objectType", "TaskBatch")
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

        List<RiderShift> riderShiftList = new ArrayList<>();

        for (TaskBatch taskBatch : taskBatchList) {
            if (taskBatch.getVersion() % 2 == 0) continue;
            RiderShift riderShift = Mapping.mapTaskBatch2RiderShift(taskBatch);
            riderShift.setMetadata(metadataMap.get(riderShift.getId()));
            riderShiftList.add(riderShift);
        }
        taskBatchList.clear();
        metadataMap.clear();

        Transaction transaction = PostgressDbConf.getRiderDb().beginTransaction();
        transaction.setBatchMode(true);
        transaction.setBatchSize(2000);

        LOG.info("Insert data at : " + new Date(System.currentTimeMillis()));
        PostgressDbConf.getRiderDb().insertAll(riderShiftList);
        riderShiftList.clear();
        transaction.commit();
        transaction.end();
    }

    public static void main(String[] args) {
        new BatchTaskMigrate().run();
    }
}
