package shippo.global;

import org.json.JSONObject;
import shippo.global.io.rider.*;
import shippo.global.type.Type;

public class Constants {
    public static JSONObject TOOKAN_INTEGRATION_SETTING;
    public static final Integer DEFAULT_TIME_OFFSET = -420;
    
    // actor
    // Actor name
    public static final String SYSTEM_ACTOR = "sync_rider_system";

    public static final String BATCH_TASK_ACTOR = "batch_task_actor";

    public static final String RIDER_SHIFT_ACTOR = "rider_shift_actor";

    public static final String TASK_HISTORY_ACTOR = "task_history_actor";

    public static final String META_FIELD_ACTOR = "meta_field_actor";

    public static final String TRANSPORT_TASK_ACTOR = "transport_task_actor";

    public static final String TRANSPORTATION_TASK_ACTOR = "transportation_task_actor";

    public static final String TEAM_ACTOR = "team_actor";

    public static final String USER_INTERG_ACTOR = "user_intergrate_actor";
    public static final String TOOKAN_AGENT_ACTOR = "tookan_agent_actor";

    public static final String RIDER_ACTOR = "rider_actor";
    public static final String USER_ACTOR = "user_actor";

    // tookan
    public static String TOOKAN_API_KEY;

    public static String TOOKAN_TASK_ADD_URL;
    public static String CREATE_TOOKAN_TEAM_URL;
    public static String UPDATE_TOOKAN_TEAM_URL;
    public static String DELETE_TOOKAN_TEAM_URL;
    public static String DETAIL_TOOKAN_TEAM_URL;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        TOOKAN_INTEGRATION_SETTING = riderDAO.getIntegrationSetting(Type.IntegrationSetting.TOOKAN);
        TOOKAN_API_KEY = ConfigLoader.getInstance().getProperties().getProperty("tookan.api.key");
        TOOKAN_TASK_ADD_URL = ConfigLoader.getInstance().getProperties().getProperty("tookan.task.add.url");
        CREATE_TOOKAN_TEAM_URL = ConfigLoader.getInstance().getProperties().getProperty("tookan.team.add.url");
        UPDATE_TOOKAN_TEAM_URL = ConfigLoader.getInstance().getProperties().getProperty("tookan.team.edit.url");
        DELETE_TOOKAN_TEAM_URL = ConfigLoader.getInstance().getProperties().getProperty("tookan.team.delete.url");
        DETAIL_TOOKAN_TEAM_URL = ConfigLoader.getInstance().getProperties().getProperty("tookan.team.detail.url");
    }

}
