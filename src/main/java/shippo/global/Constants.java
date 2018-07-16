package shippo.global;

import org.json.JSONObject;
import shippo.global.io.rider.*;
import shippo.global.type.Type;

public class Constants {
    public static JSONObject TOOKAN_INTEGRATION_SETTING;
    public static final Integer DEFAULT_TIME_OFFSET = -420;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        TOOKAN_INTEGRATION_SETTING = riderDAO.getIntegrationSetting(Type.IntegrationSetting.TOOKAN);
    }

}
