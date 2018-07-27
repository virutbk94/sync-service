package shippo.rider_service.migrate;

import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.io.rider.oldDAO;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.TookanAgent;
import shippo.rider_service.entities.v1.UserIntegrationAccount;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserIntegrationMigrate extends Thread{
    private Logger LOG = LoggerFactory.getLogger(UserIntegrationMigrate.class);

    public void run() {
        LOG.info("Start time : " + new Date(System.currentTimeMillis()));

        int max_id = oldDAO.getMaxId("\"UserIntegrationAccount\"");
        for (int i = 0; i <= max_id; i += 20000) {
            int max = i + 20000;
            miniBatchProcess(i, max);
        }
        LOG.info("Insert data done at : " + new Date(System.currentTimeMillis()));
    }

    private void miniBatchProcess(int i, int max) {
        EbeanServer oldDb = PostgressDbConf.getOldDb();
        EbeanServer newDb = PostgressDbConf.getRiderDb();
        List<UserIntegrationAccount> userIntegrationAccountList = oldDb
                .find(UserIntegrationAccount.class)
                .where()
                .and()
                .lt("id", max)
                .ge("id", i)
                .endAnd()
                .findList();

        LOG.info("Read old data done at : " + new Date(System.currentTimeMillis()));

        List<TookanAgent> tookanAgentList = new ArrayList<>();

        for (UserIntegrationAccount userIntegrationAccount : userIntegrationAccountList) {
            if (userIntegrationAccount.getVersion() % 2 == 0) continue;
            TookanAgent riderShift = Mapping.mapUserInterg2Tookan(userIntegrationAccount);
            tookanAgentList.add(riderShift);
        }

        Transaction transaction = newDb.beginTransaction();
        transaction.setBatchMode(true);
        transaction.setBatchSize(2000);

        LOG.info("Insert data at : " + new Date(System.currentTimeMillis()));
        newDb.insertAll(tookanAgentList);
        transaction.commit();
        transaction.end();
    }

    public static void main(String[] args) {
        new UserIntegrationMigrate().run();
    }
}
