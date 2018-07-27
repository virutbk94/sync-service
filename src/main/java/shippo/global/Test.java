package shippo.global;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlUpdate;
import com.fasterxml.jackson.databind.ObjectMapper;
import shippo.global.api.CRUD;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.RiderShift;
import shippo.rider_service.entities.v0.RiderShiftClone;
import shippo.rider_service.entities.v1.TaskBatch;
import shippo.rider_service.entities.v1.TaskBatchClone;

public class Test {
    public static void main(String[] args) throws Exception{
//        UsersAuth usersAuth = PostgressDbConf.getAuthDb()
//                .find(UsersAuth.class).where().idEq(1000).findUnique();
//        System.out.println(usersAuth);
//        ObjectMapper mapper = new ObjectMapper();
//        System.out.println(mapper.writeValueAsString(usersAuth));

        RiderShift riderShift = PostgressDbConf.getRiderDb()
                .find(RiderShift.class).where().idEq(9136).findUnique();
        System.out.println(riderShift);
        riderShift.setId(10);
        TaskBatch taskBatch = Mapping.mapRiderShift2TaskBatch(riderShift);
        ObjectMapper mapper = new ObjectMapper();
        String barcode = mapper.writeValueAsString(riderShift.getBarcodes());
        System.out.println(mapper.writeValueAsString(riderShift.getBarcodes()));
        CRUD.save(taskBatch,TaskBatch.class,PostgressDbConf.getOldDb());
        RiderShift riderShift1 = Mapping.mapTaskBatch2RiderShift(taskBatch);
        CRUD.save(riderShift1,RiderShift.class,PostgressDbConf.getRiderDb());
//        updateBarcode(barcode);
    }

    private static void updateBarcode(String barcode) {
        EbeanServer server = PostgressDbConf.getRiderDb();
        SqlUpdate query = server.createSqlUpdate("update rider_shifts set barcodes = '"+barcode+"' where id = 175");
        server.execute(query);
    }
}
