package shippo.rider_service.sync.actor;

import akka.actor.Props;
import com.avaje.ebean.EbeanServer;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.api.CRUD;
import shippo.rider_service.Mapping;
import shippo.rider_service.entities.v0.Rider;
import shippo.rider_service.entities.v1.Users;
import shippo.global.sync_actor.AbstractSyncActor;

public class UsersSyncActor extends AbstractSyncActor<Users> {

    public UsersSyncActor() {
        LOG = LoggerFactory.getLogger(UsersSyncActor.class);
        sourceVersion = 1;
        destinationVersion = 0;
        typeClass = Users.class;
    }

    public static Props props() {
        return Props.create(UsersSyncActor.class,() -> new UsersSyncActor());
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected boolean executeSync() {
        Rider rider;
        EbeanServer server = PostgressDbConf.getRiderDb();
        switch (crudOperation) {
            case 'd': {
                if(!before.getRealm().equals(Users.Realm.STAFF)) return true;
                rider = Mapping.mapUser2Rider(before);
                try {
                    CRUD.delete(rider, server);
                } catch (Exception e) {
                    LOG.error("Can't delete rider " + rider + " user " + before + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'u': {
                if(!after.getRealm().equals(Users.Realm.STAFF)) return true;
                rider = Mapping.mapUser2Rider(after);
                try {
                    CRUD.update(rider, server);
                } catch (Exception e) {
                    LOG.error("Can't update rider " + rider + " user " + after + "\n" +
                            e.toString());
                    return false;
                }
                return true;
            }
            case 'c': {
                if(!after.getRealm().equals(Users.Realm.STAFF)) return true;
                rider = Mapping.mapUser2Rider(after);
                try {
                    CRUD.insert(rider, server);
                } catch (Exception e) {
                    LOG.error("Can't create rider " + rider + " user " + after + "\n" +
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
