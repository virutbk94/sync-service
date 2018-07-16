package shippo.migrate.rider_service;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import shippo.global.entities.v0.rider.TookanAgent;
import shippo.global.entities.v1.UserIntegrationAccount;
import shippo.global.Mapping;
import shippo.global.PostgressDbConf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserIntegrationMigrate {
    public void run(){
        System.out.println("Start time : " + new Date(System.currentTimeMillis()));

        EbeanServer oldDb = PostgressDbConf.getOldDb();
        EbeanServer riderDb = PostgressDbConf.getRiderDb();

        List<UserIntegrationAccount> userIntegrationAccountList = oldDb.find(UserIntegrationAccount.class)
//                .where().lt("id",10000)
                .findList();

        System.out.println("Read old data done at : " + new Date(System.currentTimeMillis()));

        List<TookanAgent> tookanAgentList = new ArrayList<>();

        for (UserIntegrationAccount userIntegrationAccount : userIntegrationAccountList) {
            if (userIntegrationAccount.getVersion() % 2 == 0) continue;
            TookanAgent riderShift = Mapping.mapUserInterg2Tookan(userIntegrationAccount);
            tookanAgentList.add(riderShift);
        }

        Transaction transaction = riderDb.beginTransaction();
        transaction.setBatchMode(true);
        transaction.setBatchSize(2000);

        System.out.println("Insert data at : " + new Date(System.currentTimeMillis()));
        riderDb.insertAll(tookanAgentList);
        transaction.commit();
        transaction.end();
        System.out.println("Insert data done at : " + new Date(System.currentTimeMillis()));
    }

    public static void main(String[] args) {
        new UserIntegrationMigrate().run();
    }
}
