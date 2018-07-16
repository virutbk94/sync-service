package shippo.sync_check.rider_service.actor;

import shippo.global.Mapping;
import shippo.global.entities.v0.rider.Rider;
import shippo.global.entities.v1.Users;
import shippo.sync_check.sync_check_common.actor.AbstractSync;
import shippo.sync_check.sync_check_common.constant.ErrorCode;

import java.util.List;

public class UsersRiderActor extends AbstractSync<Users, Rider> {

    public UsersRiderActor(String firstDatabase, String secondDatabase) {
        super(Users.class, Rider.class, firstDatabase, secondDatabase);
    }

    @Override
    public int syncCheck(List<Users> firstTableData, List<Rider> secondTableData) {
        int rs = ErrorCode.OK;
        StringBuilder builder = new StringBuilder();

        //Users to Rider
        builder.append("Users to Rider : ");
        for(Users user : firstTableData){
            Rider rider = Mapping.mapUserToRider(user);
            if(!secondTableData.contains(rider)){
                builder.append(user.getId()+",");
                rs = ErrorCode.DATA_NOT_MATCH;
            }
        }
        //Rider to Users
        builder.append("\nRider to Users");
        for(Rider rider : secondTableData){
            Users user = Mapping.mapRiderToUsers(rider);
            if(!firstTableData.contains(user)){
                builder.append(rider.getId()+",");
                rs = ErrorCode.DATA_NOT_MATCH;
            }
        }

        errorNotify = builder.toString();
        return rs;
    }
}
