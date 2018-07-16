package shippo.migrate.rider_service;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import shippo.global.entities.v0.rider.*;
import shippo.global.entities.v1.*;
import shippo.global.Mapping;
import shippo.global.PostgressDbConf;

import java.util.*;

public class BatchTaskMigrate {
    public void run(){
        System.out.println("Start time : " + new Date(System.currentTimeMillis()));

        EbeanServer oldDb = PostgressDbConf.getOldDb();
        EbeanServer riderDb = PostgressDbConf.getRiderDb();

        List<TaskBatch> taskBatchList = oldDb.find(TaskBatch.class)
//                .where().lt("id",10000)
                .findList();
        List<MetaFields> metaFieldsList = oldDb.find(MetaFields.class)
                .where().eq("objectType","TaskBatch").findList();

        System.out.println("Read old data done at : " + new Date(System.currentTimeMillis()));
        Map<Integer,List<Metadata>> metadataMap = new HashMap<>();
        for(MetaFields metaFields : metaFieldsList){
            Integer key = metaFields.getObjectId();
            metadataMap.computeIfAbsent(key, k -> new ArrayList<>());
            metadataMap.get(key).add(Mapping.mapMetaFieldToMetaData(metaFields));
        }

        List<RiderShift> riderShiftList = new ArrayList<>();

        for (TaskBatch taskBatch : taskBatchList) {
            if (taskBatch.getVersion() % 2 == 0) continue;
            RiderShift riderShift = Mapping.mapTaskBatch2RiderShift(taskBatch);
            riderShift.setMetadata(metadataMap.get(riderShift.getId()));
            riderShiftList.add(riderShift);
        }

        Transaction transaction = riderDb.beginTransaction();
        transaction.setBatchMode(true);
        transaction.setBatchSize(2000);

        System.out.println("Insert data at : " + new Date(System.currentTimeMillis()));
        riderDb.insertAll(riderShiftList);
        transaction.commit();
        transaction.end();
        System.out.println("Insert data done at : " + new Date(System.currentTimeMillis()));
    }

    public static void main(String[] args) {
        new BatchTaskMigrate().run();
    }
}
