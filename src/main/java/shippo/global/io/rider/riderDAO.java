package shippo.global.io.rider;

import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import org.json.JSONObject;
import shippo.global.entities.v0.rider.Rider;
import shippo.global.entities.v0.rider.Team;
import shippo.global.entities.v1.UserIntegrationAccount;
import shippo.global.PostgressDbConf;

public class riderDAO {

    public static String getAgentIdFromRiderId(int riderId) {
        UserIntegrationAccount userIntegrationAccount =
                PostgressDbConf.getRiderDb().find(UserIntegrationAccount.class)
                        .where().idEq(riderId).findUnique();
        if (userIntegrationAccount != null) return userIntegrationAccount.getAccountId();
        return "";
    }

    public static Integer getTookanTeamIdFromRiderId(int riderId) {
        Rider rider = PostgressDbConf.getRiderDb().find(Rider.class)
                .where().idEq(riderId).findUnique();
        if (rider == null || rider.getTeamId() == null) return 0;
        int teamId = rider.getTeamId();
        Team team =
                PostgressDbConf.getRiderDb().find(Team.class)
                        .where().idEq(teamId).findUnique();
        if (team != null && team.getTookanId() != null) return team.getTookanId();
        return 0;
    }

    public static JSONObject getIntegrationSetting(int id) {
        String queryString = "select settings from integrations where id = " + id + ";";

        SqlQuery query = PostgressDbConf.getRiderDb().createSqlQuery(queryString);
        SqlRow sqlRow = query.findUnique();
        if (sqlRow != null) {
            return new JSONObject(sqlRow.getString("settings"));
        }

        return null;
    }
}
