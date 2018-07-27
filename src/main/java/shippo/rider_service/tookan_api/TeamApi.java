package shippo.rider_service.tookan_api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.client.api.ContentResponse;
import org.json.JSONObject;
import shippo.global.Constants;
import shippo.global.api.TookanApi;
import shippo.tookan_service.entities.tookan.TeamTookan;


public class TeamApi extends TookanApi {
    public ContentResponse createTookanTeam(TeamTookan recordAfter) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String taskString = mapper.writeValueAsString(recordAfter);
        JSONObject taskJson = new JSONObject(taskString);
        return super.post(Constants.CREATE_TOOKAN_TEAM_URL, taskJson);
    }

    public ContentResponse updateTookanTeam(TeamTookan recordAfter) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String taskString = mapper.writeValueAsString(recordAfter);
        JSONObject taskJson = new JSONObject(taskString);
        return super.post(Constants.UPDATE_TOOKAN_TEAM_URL, taskJson);
    }

    public ContentResponse deleteTookanTeam(TeamTookan recordAfter) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        String taskString = mapper.writeValueAsString(recordAfter);
        JSONObject taskJson = new JSONObject(taskString);
        return super.post(Constants.DELETE_TOOKAN_TEAM_URL, taskJson);
    }

    public ContentResponse detailTookanTeam() throws Exception{
        JSONObject taskJson = new JSONObject();
        return super.post(Constants.DELETE_TOOKAN_TEAM_URL, taskJson);
    }

}
