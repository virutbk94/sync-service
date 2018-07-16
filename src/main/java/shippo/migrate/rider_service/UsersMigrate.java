package shippo.migrate.rider_service;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import shippo.global.entities.v0.rider.Rider;
import shippo.global.entities.v1.*;
import shippo.global.Mapping;
import shippo.global.PostgressDbConf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersMigrate {
    public void run(){
        System.out.println("Start time : " + new Date(System.currentTimeMillis()));

        EbeanServer oldDb = PostgressDbConf.getOldDb();
        EbeanServer riderDb = PostgressDbConf.getRiderDb();



        List<Users> usersList = oldDb.find(Users.class)
//                .where().lt("id",10000)
                .findList();

        System.out.println("Read old data done at : " + new Date(System.currentTimeMillis()));

        List<Rider> riderList = new ArrayList<>();

        for (Users users : usersList) {
            if (users.getVersion() % 2 == 0) continue;
            if(!users.getRealm().equals(Users.Realm.STAFF.getCode()))
                continue;
            Rider rider = Mapping.mapUserToRider(users);
            riderList.add(rider);
        }

        Transaction transaction = riderDb.beginTransaction();
        transaction.setBatchMode(true);
        transaction.setBatchSize(2000);

        System.out.println("Insert data at : " + new Date(System.currentTimeMillis()));
        riderDb.insertAll(riderList);
        transaction.commit();
        transaction.end();
        System.out.println("Insert data done at : " + new Date(System.currentTimeMillis()));
    }

    public static void main(String[] args) {
        new UsersMigrate().run();
    }
}
