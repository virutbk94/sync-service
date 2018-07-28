package shippo.rider_service.tookan_api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.client.api.ContentResponse;
import org.json.JSONObject;
import shippo.global.Constants;
import shippo.global.api.TookanApi;
import shippo.tookan_service.entities.tookan.Task;

public class TaskApi extends TookanApi {
    public ContentResponse insert(Task task) throws JsonProcessingException {
        JSONObject taskJson = new JSONObject(task);
        return super.post(Constants.TOOKAN_TASK_ADD_URL, taskJson);
    }
}
