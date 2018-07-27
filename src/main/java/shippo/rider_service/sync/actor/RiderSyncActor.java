package shippo.rider_service.sync.actor;

import akka.actor.Props;
import com.avaje.ebean.EbeanServer;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.api.CRUD;
import shippo.global.sync_actor.AbstractSyncActor;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.Rider;
import shippo.rider_service.entities.v1.Users;

public class RiderSyncActor extends AbstractSyncActor<Rider> {

    public RiderSyncActor() {
        LOG = LoggerFactory.getLogger(RiderSyncActor.class);
        sourceVersion = 0;
        destinationVersion = 1;
        typeClass = Rider.class;
    }

    public static Props props() {
        return Props.create(RiderSyncActor.class,() -> new RiderSyncActor());
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected boolean executeSync() {
        Users user;
        EbeanServer server = PostgressDbConf.getOldDb();
        switch (crudOperation) {
            case 'd': {
                user = Mapping.mapRiderToUsers(before);
                try {
                    CRUD.delete(user, server);
                } catch (Exception e) {
                    LOG.error("Can't delete user " + user + " rider " + before + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'u': {
                user = Mapping.mapRiderToUsers(after);
                try {
                    CRUD.update(user, server);
                } catch (Exception e) {
                    LOG.error("Can't update user " + user + " rider " + after + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'c': {
                user = Mapping.mapRiderToUsers(after);
                try {
                    CRUD.insert(user, server);
                } catch (Exception e) {
                    LOG.error("Can't create user " + user + " rider " + after + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            default:
                return false;
        }
    }

}
