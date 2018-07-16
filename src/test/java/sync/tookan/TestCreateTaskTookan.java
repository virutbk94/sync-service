package sync.tookan;

import jdk.jfr.Description;
import org.junit.Test;

public class TestCreateTaskTookan {
    @Test
    @Description("id(TransportationTask) = order_id (Task)")
    public void testOrder_id(){

    }

    @Test
    @Description("description + \r\n + note (TransportationTask) = job_description (Task)")
    public void testDescription(){

    }

    @Test
    @Description("has_pickup = (type == 'PICKUP') ? 1 : 0")
    public void testHasPickUp(){

    }

    @Test
    @Description("has_pickup = (type == 'DELIVERRY') ? 1 : 0")
    public void testHasDelivery(){

    }

    @Test
    @Description("metadata tuong ung tracking_link tookan tra ve")
    public void testMetadata(){

    }

    @Test
    @Description("team_id tuong ung team cua assignee")
    public void testTeamId(){

    }

    @Test
    @Description("fleet_id tuong ung assignee")
    public void testFleetId(){

    }

    @Test
    @Description("pickupContactPhone = job_pickup_phone")
    public void testPickupContactPhone(){

    }

    @Test
    @Description("PickupContactName = job_pickup_name")
    public void testPickupContactName(){

    }

    @Test
    @Description("PickupFullAddress = job_pickup_address")
    public void testPickupFullAddress(){

    }

    @Test
    @Description("PickupBefore = job_pickup_datetime")
    public void testPickupBefore(){

    }

    @Test
    @Description("pickup_meta_data co chua cac custommer_phone + customer_name")
    public void testPickupMetadata(){

    }

    @Test
    @Description("DeliverContactPhone = customer_phone")
    public void testDeliverContactPhone(){

    }

    @Test
    @Description("DeliverContactName = customer_username")
    public void testDeliverContactName(){

    }

    @Test
    @Description("DeliverFullAddress = customer_address")
    public void testDeliverFullAddress(){

    }

    @Test
    @Description("DeliverBefore = job_delivery_datetime")
    public void testDeliverBefore(){

    }

    @Test
    @Description("meta_data co chua cac custommer_phone + customer_name")
    public void testDeliveryMetadata(){

    }

    @Test
    @Description("tookan_job_id = job_id")
    public void testJobId(){

    }

}
