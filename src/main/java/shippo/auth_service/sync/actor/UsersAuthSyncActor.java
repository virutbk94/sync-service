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

public class UsersAuthSyncActor extends AbstractSyncActor<UsersAuth> {

    private UsersAuthSyncActor() {
        LOG = LoggerFactory.getLogger(UsersAuthSyncActor.class);
        sourceVersion = 1;
        destinationVersion = 0;
        typeClass = UsersAuth.class;
    }

    public static Props props() {
        return Props.create(UsersAuthSyncActor.class, UsersAuthSyncActor::new);
    }

    @Override
    protected boolean needSync() {
        return !isLoop();
    }

    @Override
    protected boolean executeSync() {
        Users users;
        EbeanServer server = PostgressDbConf.getRiderDb();
        switch (crudOperation) {
            case 'd': {
                users = Mapping.mapUsersAuthToUsers(before);
                try {
                    CRUD.delete(users, server);
                } catch (Exception e) {
                    LOG.error("Can't delete users " + users + " usersAuth " + before + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'u': {
                users = Mapping.mapUsersAuthToUsers(after);
                try {
                    CRUD.update(users, server);
                } catch (Exception e) {
                    LOG.error("Can't update users " + users + " usersAuth " + after + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'c': {
                users = Mapping.mapUsersAuthToUsers(after);
                try {
                    CRUD.insert(users, server);
                } catch (Exception e) {
                    LOG.error("Can't create users " + users + " usersAuth " + after + "\n" +
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
