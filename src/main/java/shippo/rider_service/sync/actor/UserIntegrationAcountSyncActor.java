package shippo.rider_service.sync.actor;

import akka.actor.Props;
import com.avaje.ebean.EbeanServer;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.api.CRUD;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.TookanAgent;
import shippo.rider_service.entities.v1.UserIntegrationAccount;
import shippo.global.sync_actor.AbstractSyncActor;

public class UserIntegrationAcountSyncActor extends AbstractSyncActor<UserIntegrationAccount> {

    public UserIntegrationAcountSyncActor() {
        LOG = LoggerFactory.getLogger(UserIntegrationAcountSyncActor.class);
        sourceVersion = 1;
        destinationVersion = 0;
        typeClass = UserIntegrationAccount.class;
    }

    public static Props props() {
        return Props.create(UserIntegrationAcountSyncActor.class,() -> new UserIntegrationAcountSyncActor());
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected boolean executeSync() {
        TookanAgent tookanAgent;
        EbeanServer server = PostgressDbConf.getRiderDb();
        switch (crudOperation) {
            case 'd': {
                tookanAgent = Mapping.mapUserInterg2Tookan(before);
                try {
                    CRUD.delete(tookanAgent, server);
                } catch (Exception e) {
                    LOG.error("Can't delete tookanAgent " + tookanAgent + " userIntegrationAcount" + before + "\n" +
                            e.getStackTrace());
                    return false;
                }
                return true;
            }
            case 'u': {
                tookanAgent = Mapping.mapUserInterg2Tookan(after);
                try {
                    CRUD.update(tookanAgent, server);
                } catch (Exception e) {
                    LOG.error("Can't update tookanAgent " + tookanAgent + " userIntegrationAcount" + after + "\n" +
                            e.getStackTrace());
                    return false;
                }
                return true;
            }
            case 'c': {
                tookanAgent = Mapping.mapUserInterg2Tookan(after);
                try {
                    CRUD.insert(tookanAgent, server);
                } catch (Exception e) {
                    LOG.error("Can't create tookanAgent " + tookanAgent + " userIntegrationAcount" + after + "\n" +
                            e.getStackTrace());
                    return false;
                }
                return true;
            }
            default:
                return false;
        }
    }
}
