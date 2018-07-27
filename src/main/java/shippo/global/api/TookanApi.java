package shippo.global.api;

import org.eclipse.jetty.client.api.ContentResponse;
import org.json.JSONObject;
import shippo.global.Constants;

public class TookanApi extends HttpApi{
    public ContentResponse post(String url, JSONObject body) {
        body.put("api_key",Constants.TOOKAN_API_KEY);
        JSONObject header = new JSONObject();
        return super.post(url, header, body);
    }
}
