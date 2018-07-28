package shippo.rider_service.sync.actor;

import akka.actor.Props;
import org.eclipse.jetty.client.api.ContentResponse;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import shippo.global.PostgressDbConf;
import shippo.global.Utils;
import shippo.global.api.CRUD;
import shippo.rider_service.Mapping;
import shippo.rider_service.tookan_api.TeamApi;
import shippo.global.sync_actor.AbstractSyncActor;
import shippo.tookan_service.entities.shippo.Team;
import shippo.tookan_service.entities.tookan.TeamTookan;

public class TeamSyncActor extends AbstractSyncActor<Team> {

    private TeamSyncActor() {
        LOG = LoggerFactory.getLogger(TeamSyncActor.class);
        sourceVersion = 0;
        destinationVersion = 1;
        typeClass = Team.class;
    }

    public static Props props() {
        return Props.create(TeamSyncActor.class,TeamSyncActor::new);
    }

    @Override
    protected boolean needSync() {
        return true;
//        return !isLoop();
    }

    @Override
    protected boolean executeSync() {
        TeamTookan teamTookan;
        switch (crudOperation) {
            case 'd': {
                teamTookan = Mapping.mapTeam2TeamTookan(before);
                try {
                    ContentResponse contentResponse = new TeamApi().deleteTookanTeam(teamTookan);
                    JSONObject jsonObject = new JSONObject(contentResponse.getContentAsString());
                    if(jsonObject.getInt("status") == 200){
                        LOG.info("Delete tookan team {} done!", teamTookan);
                    }else LOG.warn(jsonObject.toString());
                } catch (Exception e) {
                    LOG.error("Can't delete teamTookan " + teamTookan + " team " + before + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'u': {
                teamTookan = Mapping.mapTeam2TeamTookan(after);
                try {
                    ContentResponse contentResponse = new TeamApi().updateTookanTeam(teamTookan);
                    JSONObject jsonObject = new JSONObject(contentResponse.getContentAsString());
                    if(jsonObject.getInt("status") == 200){
                        LOG.info("Update tookan team {} done!", teamTookan);
                    }else LOG.warn(jsonObject.toString());
                } catch (Exception e) {
                    LOG.error("Can't update teamTookan " + teamTookan + " team " + after + "\n" +
                            Utils.getExceptionMessage(e));
                    return false;
                }
                return true;
            }
            case 'c': {
                teamTookan = Mapping.mapTeam2TeamTookan(after);
                try {
                    ContentResponse contentResponse = new TeamApi().createTookanTeam(teamTookan);
                    JSONObject jsonObject = new JSONObject(contentResponse.getContentAsString());
                    if(jsonObject.getInt("status") == 200){
                        LOG.info("Create tookan team {} done!",teamTookan);
                        after.setTookanId(((JSONObject)jsonObject.get("data")).getInt("team_id"));
                        CRUD.update(after,PostgressDbConf.getRiderDb());
                    }else LOG.warn(jsonObject.toString());
                } catch (Exception e) {
                    LOG.error("Can't create teamTookan " + teamTookan + " team " + after + "\n" +
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
