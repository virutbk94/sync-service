package shippo.auth_service.sync.actor;

import akka.actor.Props;
import com.avaje.ebean.EbeanServer;
import org.slf4j.LoggerFactory;
import shippo.auth_service.Mapping;
import shippo.auth_service.entities.v0.UsersAuth;
import shippo.auth_service.entities.v1.Users;
import shippo.global.PostgressDbConf;
import shippo.global.Utils;
import shippo.global.api.CRUD;
import shippo.global.sync_actor.AbstractSyncActor;

public class UsersSyncActor extends AbstractSyncActor<Users> {

    private UsersSyncActor() {
        LOG = LoggerFactory.getLogger(UsersSyncActor.class);
        sourceVersion = 1;
        destinationVersion = 0;
        typeClass = Users.class;
    }

    public static Props props() {
        return Props.create(UsersSyncActor.class, UsersSyncActor::new);
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected boolean executeSync() {
        UsersAuth usersAuth;
        EbeanServer server = PostgressDbConf.getRiderDb();
        switch (crudOperation) {
            case 'd': {
                usersAuth = Mapping.mapUsersToUsersAuth(before);
                try {
                    CRUD.delete(usersAuth, server);
                } catch (Exception e) {
                    LOG.error("Can't delete usersAuth " + usersAuth + " user " + before + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'u': {
                usersAuth = Mapping.mapUsersToUsersAuth(after);
                try {
                    CRUD.update(usersAuth, server);
                } catch (Exception e) {
                    LOG.error("Can't update usersAuth " + usersAuth + " user " + after + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'c': {
                usersAuth = Mapping.mapUsersToUsersAuth(after);
                try {
                    CRUD.insert(usersAuth, server);
                } catch (Exception e) {
                    LOG.error("Can't create usersAuth " + usersAuth + " user " + after + "\n" +
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
