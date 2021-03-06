package shippo.rider_service.sync.actor;

import akka.actor.Props;
import com.avaje.ebean.EbeanServer;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.Utils;
import shippo.global.api.CRUD;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.TookanAgent;
import shippo.rider_service.entities.v1.UserIntegrationAccount;
import shippo.global.sync_actor.AbstractSyncActor;

public class UserIntegrationAcountSyncActor extends AbstractSyncActor<UserIntegrationAccount> {

    private UserIntegrationAcountSyncActor() {
        LOG = LoggerFactory.getLogger(UserIntegrationAcountSyncActor.class);
        sourceVersion = 1;
        destinationVersion = 0;
        typeClass = UserIntegrationAccount.class;
    }

    public static Props props() {
        return Props.create(UserIntegrationAcountSyncActor.class,UserIntegrationAcountSyncActor::new);
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
                if(!before.getIntegrationId().equals(1)) return true;
                tookanAgent = Mapping.mapUserInterg2Tookan(before);
                try {
                    CRUD.delete(tookanAgent, server);
                } catch (Exception e) {
                    LOG.error("Can't delete tookanAgent " + tookanAgent + " userIntegrationAcount" + before + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'u': {
                if(!after.getIntegrationId().equals(1)) return true;
                tookanAgent = Mapping.mapUserInterg2Tookan(after);
                try {
                    CRUD.update(tookanAgent, server);
                } catch (Exception e) {
                    LOG.error("Can't update tookanAgent " + tookanAgent + " userIntegrationAcount" + after + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'c': {
                if(!after.getIntegrationId().equals(1)) return true;
                tookanAgent = Mapping.mapUserInterg2Tookan(after);
                try {
                    CRUD.insert(tookanAgent, server);
                } catch (Exception e) {
                    LOG.error("Can't create tookanAgent " + tookanAgent + " userIntegrationAcount" + after + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            default:
                return false;
        }
    }
}
