package shippo.rider_service.migrate;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.io.rider.oldDAO;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.Rider;
import shippo.rider_service.entities.v1.Users;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersMigrate extends Thread{
    private Logger LOG = LoggerFactory.getLogger(UsersMigrate.class);

    public void run() {
        LOG.info("Start time : " + new Date(System.currentTimeMillis()));

        int max_id = oldDAO.getMaxId("\"Users\"");
        for (int i = 0; i <= max_id; i += 20000) {
            int max = i + 20000;

            miniBatchProcess(i,max);
        }
        LOG.info("Insert data done at : " + new Date(System.currentTimeMillis()));
    }

    private void miniBatchProcess(int i, int max) {
        EbeanServer oldDb = PostgressDbConf.getOldDb();
        EbeanServer newDb = PostgressDbConf.getRiderDb();

        List<Users> usersList = oldDb.find(Users.class)
                .where()
                .and()
                .lt("id", max)
                .ge("id", i)
                .eq("realm", Users.Realm.STAFF)
                .endAnd()
                .findList();

        System.out.println("Read old data done at : " + new Date(System.currentTimeMillis()));

        List<Rider> riderList = new ArrayList<>();

        for (Users users : usersList) {
            if (users.getVersion() % 2 == 0) continue;
            if (!users.getRealm().equals(Users.Realm.STAFF))
                continue;
            Rider rider = Mapping.mapUser2Rider(users);
            riderList.add(rider);
        }

        Transaction transaction = newDb.beginTransaction();
        transaction.setBatchMode(true);
        transaction.setBatchSize(2000);

        LOG.info("Insert data at : " + new Date(System.currentTimeMillis()));
        newDb.insertAll(riderList);
        transaction.commit();
        transaction.end();
    }

    public static void main(String[] args) {
        new UsersMigrate().run();
    }
}
