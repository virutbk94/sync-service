package shippo.global.type;

public class Type {
    public static class OpType{
        public static final char CREATE = 'c';
        public static final char READ = 'r';
        public static final char UPDATE = 'u';
        public static final char DELETE = 'd';
    }

    public static class BeforAfter{
        public static final int BEFORE = 1;
        public static final int AFTER = 2;
    }

    public static class TaskType{
        public static final String PICKUP = "PICKUP";
        public static final String DELIVER = "DELIVER";
        public static final String PICKUP_AND_DELIVER = "PICKUP_AND_DELIVER";
    }

    public static class DeliverOrderStatus{
        // TODO
        // Them status vao sau
        public static final String WAITING_FOR_PICKUP = "WAITING_FOR_PICKUP";
        public static final String PICKING_UP = "PICKING_UP";
    }

    public static class IntegrationSetting{
        public static final int TOOKAN = 1;
        public static final int SPEEDSMS = 2;
        public static final int GOOGL = 3;
        public static final int ZENDESK = 4;
        public static final int TRUST_SALES = 5;
        public static final int EDUMALL = 6;
        public static final int SALEMALL = 7;
        public static final int EMAIL_SERVICE = 8;
        public static final int HANOI_BUS = 9;
    }

    public static class Shift{
        public static final int MORNING = 1;
        public static final int AFTERNOON = 2;
        public static final int EVENING = 3;
    }

    public static class MetaFieldObjectType{
        public static final String TASK_BATCH = "TaskBatch";
        public static final String TRANSPORT_TASK = "TransportTask";
    }

    public static class FeeType{
        public static final String RECEIVER_PAY_FOR_DELIVERY = "Người nhận trả phí";
    }

    public static class ChargeType{
        public static final String REVICE = "REVICE";
    }
}
