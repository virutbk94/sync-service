package shippo.rider_service.conf;

public class Constants {
    public static class ObjectType{
        public static String TASK_BATCH = "TaskBatch";
        public static String TRANSPORT_TASK = "TransportTask";
    }

    public static class TaskType{
        public static String PICKUP = "PICKUP";
        public static String DELIVER = "DELIVER";
        public static String PICKUP_AND_DELIVER = "PICKUP_AND_DELIVER";
    }

    public static class FeeType{
        public static final String RECEIVER_PAY_FOR_DELIVERY = "Người nhận trả phí";
    }
}
