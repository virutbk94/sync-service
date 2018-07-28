package shippo.rider_service.kafka;

import shippo.global.ConfigLoader;
import shippo.global.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class KafkaRef {
    public static Map<String, String> topicMapper = new HashMap();
    static Properties properties = ConfigLoader.getInstance().getProperties();
    final public static String BATCH_TASK_TOPIC = properties.getProperty("old.topic.taskbatch");
//    final public static String BATCH_TASK_TOPIC = "shippo_vn_1905.public.TaskBatch";
    final public static String META_FIELD_TOPIC = properties.getProperty("old.topic.metafields");
//    final public static String META_FIELD_TOPIC = "shippo_vn_1905.public.MetaFields";
    final public static String USERS_TOPIC = properties.getProperty("old.topic.users");
//    final public static String USERS_TOPIC = "shippo_vn_1905.public.Users";
    final public static String TRANSPORT_TASK_TOPIC = properties.getProperty("old.topic.transport_task");
//    final public static String TRANSPORT_TASK_TOPIC = "shippo_vn_1905.public.TransportTask";
    final public static String USER_INTEG_TOPIC = properties.getProperty("old.topic.user_intergration_account");
//    final public static String USER_INTEG_TOPIC = "shippo_vn_1905.public.UserIntegrationAccount";


    final public static String RIDER_TOPIC = properties.getProperty("rider.topic.riders");
//    final public static String RIDER_TOPIC = "rider_service.public.riders";
    final public static String RIDER_SHIFT_TOPIC = properties.getProperty("rider.topic.rider_shifts");
//    final public static String RIDER_SHIFT_TOPIC = "rider_service.public.rider_shifts";
    final public static String TASK_HISTORY_TOPIC = properties.getProperty("rider.topic.task_histories");
//    final public static String TASK_HISTORY_TOPIC = "rider_service.public.task_histories";
    final public static String TEAM_TOPIC = properties.getProperty("rider.topic.teams");
//    final public static String TEAM_TOPIC = "rider_service.public.teams";
    final public static String TOOKAN_AGENT_TOPIC = properties.getProperty("rider.topic.tookan_agents");
//    final public static String TOOKAN_AGENT_TOPIC = "rider_service.public.tookan_agents";
    final public static String TRANSPORTTATION_TASK_TOPIC = properties.getProperty("rider.topic.transportation_tasks");
//    final public static String TRANSPORTTATION_TASK_TOPIC = "rider_service.public.transportation_tasks";


    static{
        // for riderdb parser
        topicMapper.put(RIDER_TOPIC, Constants.RIDER_ACTOR);
        topicMapper.put(RIDER_SHIFT_TOPIC, Constants.RIDER_SHIFT_ACTOR);
        topicMapper.put(TASK_HISTORY_TOPIC, Constants.TASK_HISTORY_ACTOR);
        topicMapper.put(TEAM_TOPIC, Constants.TEAM_ACTOR);
        topicMapper.put(TOOKAN_AGENT_TOPIC, Constants.TOOKAN_AGENT_ACTOR);
        topicMapper.put(TRANSPORTTATION_TASK_TOPIC, Constants.TRANSPORTATION_TASK_ACTOR);
        // for shippo_vn parser
        topicMapper.put(BATCH_TASK_TOPIC, Constants.BATCH_TASK_ACTOR);
        topicMapper.put(META_FIELD_TOPIC, Constants.META_FIELD_ACTOR);
        topicMapper.put(USERS_TOPIC, Constants.USER_ACTOR);
        topicMapper.put(TRANSPORT_TASK_TOPIC, Constants.TRANSPORT_TASK_ACTOR);
        topicMapper.put(USER_INTEG_TOPIC, Constants.USER_INTERG_ACTOR);
    }
    public static String getActorFromTopic(String topic){
        return topicMapper.get(topic);
    }
}
