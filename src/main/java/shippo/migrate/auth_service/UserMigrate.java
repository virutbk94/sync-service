package shippo.migrate.auth_service;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import shippo.global.entities.v1.Users;
import shippo.global.Mapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserMigrate {
    public void run(){
        System.out.println("Start time : " + new Date(System.currentTimeMillis()));

        EbeanServer oldDb = Ebean.getServer("shippo_vn_1905");
        EbeanServer newDb = Ebean.getServer("oauth_service");

        List<Users> usersList = oldDb.find(Users.class)
//                .where().lt("id",10000)
                .findList();

        System.out.println("Read old data done at : " + new Date(System.currentTimeMillis()));

        List<shippo.global.entities.v0.oauth.Users> riderList = new ArrayList<>();

        for (Users users : usersList) {
            if (users.getVersion() % 2 == 0) continue;
            shippo.global.entities.v0.oauth.Users rider = Mapping.mapUserOld2New(users);
            riderList.add(rider);
        }

        Transaction transaction = newDb.beginTransaction();
        transaction.setBatchMode(true);
        transaction.setBatchSize(2000);

        System.out.println("Insert data at : " + new Date(System.currentTimeMillis()));
        newDb.insertAll(riderList);
        transaction.commit();
        transaction.end();
        System.out.println("Insert data done at : " + new Date(System.currentTimeMillis()));

    }
}
