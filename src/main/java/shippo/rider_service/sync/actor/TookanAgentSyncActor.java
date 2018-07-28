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

public class TookanAgentSyncActor extends AbstractSyncActor<TookanAgent> {

    private TookanAgentSyncActor() {
        LOG = LoggerFactory.getLogger(TookanAgentSyncActor.class);
        sourceVersion = 0;
        destinationVersion = 1;
        typeClass = TookanAgent.class;
    }

    public static Props props() {
        return Props.create(TookanAgentSyncActor.class,() -> new TookanAgentSyncActor());
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected boolean executeSync() {
        UserIntegrationAccount userIntegrationAccount;
        EbeanServer server = PostgressDbConf.getOldDb();
        switch (crudOperation) {
            case 'd': {
                userIntegrationAccount = Mapping.mapTookan2UserIntergration(before);
                try {
                    CRUD.delete(userIntegrationAccount, server);
                } catch (Exception e) {
                    LOG.error("Can't delete userIntegrationAccount " + userIntegrationAccount + " tookanAgent " + before + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'u': {
                userIntegrationAccount = Mapping.mapTookan2UserIntergration(after);
                try {
                    CRUD.update(userIntegrationAccount, server);
                } catch (Exception e) {
                    LOG.error("Can't update userIntegrationAccount " + userIntegrationAccount + " tookanAgent " + after + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'c': {
                userIntegrationAccount = Mapping.mapTookan2UserIntergration(after);
                try {
                    CRUD.insert(userIntegrationAccount, server);
                } catch (Exception e) {
                    LOG.error("Can't create userIntegrationAccount " + userIntegrationAccount + " tookanAgent " + after + "\n" +
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
