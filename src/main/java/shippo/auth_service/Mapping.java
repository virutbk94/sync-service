package shippo.auth_service;
import shippo.auth_service.entities.v0.UsersAuth;
import shippo.auth_service.entities.v1.Users;

import java.util.*;

public class Mapping {

    public static Users mapUsersAuthToUsers(UsersAuth usersV1) {
        Users usersTmp = new Users();
        usersTmp.setId(usersV1.getId());
        usersTmp.setRealm(usersV1.getSection());
        usersTmp.setUsername(usersV1.getUsername());
        usersTmp.setPassword(usersV1.getPassword());
        usersTmp.setEmail(usersV1.getEmail());
        usersTmp.setIsEmailVerified(usersV1.getEmailVerified() ? (short) 1 : 0);
        usersTmp.setState(usersV1.getState());
        usersTmp.setRegisteredDate(usersV1.getCreatedAt());
        usersTmp.setNeedChangePass(usersV1.getRequiredChangePass() ? (short) 1 : 0);
        usersTmp.setLastChangedPass(usersV1.getChangePassAt());
        usersTmp.setLevel(usersV1.getLevel());
        usersTmp.setVersion(usersV1.getVersion());
        usersTmp.setRegisteredDate(usersV1.getCreatedAt());
        usersTmp.setModifiedTime(usersV1.getUpdatedAt());
        return usersTmp;
    }

    public static UsersAuth mapUsersToUsersAuth(Users usersV0) {
        UsersAuth userTmp = new UsersAuth();
        userTmp.setId(usersV0.getId());
        userTmp.setSection(usersV0.getRealm());
        userTmp.setUsername(usersV0.getUsername());
        userTmp.setPassword(usersV0.getPassword());
        userTmp.setEmail(usersV0.getEmail());
        userTmp.setEmailVerified(Objects.equals(usersV0.getIsEmailVerified(), new Short((short) 1)));
        userTmp.setState(usersV0.getState());
        userTmp.setCreatedAt(usersV0.getRegisteredDate());
        userTmp.setRequiredChangePass(Objects.equals(usersV0.getNeedChangePass(), new Short((short) 1)));
        userTmp.setChangePassAt(usersV0.getLastChangedPass());
        userTmp.setLevel(usersV0.getLevel());
        userTmp.setVersion(usersV0.getVersion());
        userTmp.setUpdatedAt(usersV0.getModifiedTime());
        return userTmp;
    }
}
