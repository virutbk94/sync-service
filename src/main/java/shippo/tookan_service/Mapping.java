package shippo.tookan_service;

import com.avaje.ebean.SqlQuery;
import com.avaje.ebean.SqlRow;
import org.json.JSONArray;
import org.json.JSONObject;
import shippo.global.Constants;
import shippo.global.PostgressDbConf;
import shippo.global.Utils;
import shippo.global.io.rider.oldDAO;
import shippo.global.io.rider.riderDAO;
import shippo.rider_service.entities.v1.Users;
import shippo.tookan_service.entities.shippo.Rider;
import shippo.tookan_service.entities.shippo.Team;
import shippo.tookan_service.entities.shippo.TransportationTask;
import shippo.tookan_service.entities.tookan.Agent;
import shippo.tookan_service.entities.tookan.Task;
import shippo.tookan_service.entities.tookan.TeamTookan;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Mapping {

    public static Task mapTransportationTask2Task(TransportationTask transportationTask) {

        Task task = new Task();
        task.setOrder_id(transportationTask.getId().toString());
        task.setJob_description(transportationTask.getDescription() + "\r\n" + transportationTask.getNote());
        task.setHas_pickup(0);
        task.setHas_delivery(0);
        task.setLayout_type(0);
        task.setTracking_link(1);
        task.setTimezone(Constants.DEFAULT_TIME_OFFSET);
        task.setAuto_assignment(1);
        task.setFleet_id(riderDAO.getAgentIdFromRiderId(transportationTask.getAssignee()));
        task.setTeam_id(riderDAO.getTookanTeamIdFromRiderId(transportationTask.getAssignee()));

        if (transportationTask.getType().equals(shippo.global.type.Type.TaskType.PICKUP)) {
            // Set time
            Timestamp timestamp = transportationTask.getPickupBefore() == null
                    ? transportationTask.getPickupBefore() : new Timestamp(System.currentTimeMillis() + 2 * 3600000);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/DD/YY HH:mm");
            task.setJob_pickup_datetime(simpleDateFormat.format(timestamp));

            // Set order count
            int orderCount = oldDAO.getOrderCount(transportationTask.getRequestId());
            task.setJob_pickup_phone(transportationTask.getPickupContactPhone());
            task.setJob_pickup_name("(" + orderCount + ") " + transportationTask.getPickupContactName());
            task.setJob_pickup_address(transportationTask.getPickupFullAddress());
            task.setPickup_custom_field_template(Constants.TOOKAN_INTEGRATION_SETTING.getString("pickup_custom_field_template"));
            task.setHas_pickup(1);
            JSONArray meta_data = buildPickupCustomField(transportationTask);
            task.setMeta_data(meta_data);

        } else if (transportationTask.getType().equals(shippo.global.type.Type.TaskType.DELIVER)
                || transportationTask.getType().equals(shippo.global.type.Type.TaskType.PICKUP_AND_DELIVER)) {
            // Set time
            Timestamp timestamp = transportationTask.getDeliverBefore() == null
                    ? transportationTask.getDeliverBefore() : new Timestamp(System.currentTimeMillis() + 2 * 3600000);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/DD/YY HH:mm");
            task.setJob_delivery_datetime(simpleDateFormat.format(timestamp));
            task.setCustomer_username(transportationTask.getDeliverContactName());
            task.setCustomer_phone(transportationTask.getDeliverContactPhone());
            task.setCustomer_address(transportationTask.getDeliverFullAddress());
            task.setHas_delivery(1);
            task.setCustom_field_template(Constants.TOOKAN_INTEGRATION_SETTING.getString("deliver_custom_field_template"));
            JSONArray meta_data = buildDeliveryCustomField(transportationTask);
            task.setMeta_data(meta_data);
        }
        return task;
    }

    private static JSONArray buildPickupCustomField(TransportationTask transportationTask) {
        JSONArray jsonArray = new JSONArray();
        Users users = PostgressDbConf.getOldDb().find(Users.class)
                .where().idEq(transportationTask.getMerchantId()).findUnique();
        if (users == null) return jsonArray;
        JSONObject customer_number_field = new JSONObject();
        customer_number_field.put("label", Constants.TOOKAN_INTEGRATION_SETTING.getString("customer_number_field"));
        customer_number_field.put("data", users.getMobile());
        jsonArray.put(customer_number_field);

        JSONObject customer_name_field = new JSONObject();
        customer_name_field.put("label", Constants.TOOKAN_INTEGRATION_SETTING.getString("customer_name_field"));
        customer_name_field.put("data", users.getFullName());
        jsonArray.put(customer_name_field);
        return jsonArray;
    }

    private static JSONArray buildDeliveryCustomField(TransportationTask transportationTask) {
        JSONArray jsonArray = new JSONArray();

        String codText = Utils.formatMonney(transportationTask.getCod());
        JSONObject cod_Field = new JSONObject();
        cod_Field.put("label", Constants.TOOKAN_INTEGRATION_SETTING.get("cod_field"));
        cod_Field.put("data", codText);
        jsonArray.put(cod_Field);

        Users users = PostgressDbConf.getOldDb().find(Users.class)
                .where().idEq(transportationTask.getMerchantId()).findUnique();
        if (users == null) return jsonArray;
        JSONObject customer_number_field = new JSONObject();
        customer_number_field.put("label", Constants.TOOKAN_INTEGRATION_SETTING.getString("customer_number_field"));
        customer_number_field.put("data", users.getMobile());
        jsonArray.put(customer_number_field);

        JSONObject customer_name_field = new JSONObject();
        customer_name_field.put("label", Constants.TOOKAN_INTEGRATION_SETTING.getString("customer_name_field"));
        customer_name_field.put("data", users.getFullName());
        jsonArray.put(customer_name_field);

        // check thu du mac dinh la co check
        JSONObject fullCode = new JSONObject();
        fullCode.put("label", Constants.TOOKAN_INTEGRATION_SETTING.getString("full_cod"));
        fullCode.put("data", true);
        jsonArray.put(fullCode);

        // Them phi thu cua nguoi nhan
        if(Constants.TOOKAN_INTEGRATION_SETTING.isNull("delivery_fee")){
            JSONObject deliveryFee = new JSONObject();
            if(transportationTask.getRecipientPayCourierFee().equals(shippo.global.type.Type.FeeType.RECEIVER_PAY_FOR_DELIVERY)){
                int order_id = transportationTask.getObjectId();
                Double fee = oldDAO.getFeeReviceOfOder(order_id);
                deliveryFee.put("label", Constants.TOOKAN_INTEGRATION_SETTING.getString("delivery_fee"));
                deliveryFee.put("data", Utils.formatMonney(fee));
            }else {
                deliveryFee.put("label", Constants.TOOKAN_INTEGRATION_SETTING.getString("delivery_fee"));
                deliveryFee.put("data", "0");
            }
            jsonArray.put(deliveryFee);
        }

        return jsonArray;
    }

    public static TeamTookan mapTeamToTookanTeam(Team team){
        if(team == null) return null;
        TeamTookan teamTookan = new TeamTookan();
        teamTookan.setTeam_id(team.getTookanId());
        teamTookan.setTeam_name(team.getName());
        return teamTookan;
    }

    public static Agent mapRiderToAgent(Rider rider){
        if(rider == null) return null;
        Agent agent = new Agent();
        agent.setFleet_id(getFleetIdFromRiderId(rider.getId()));
        agent.setPassword(rider.getPassword());
        agent.setUsername(rider.getUsername());
        agent.setName(rider.getUsername());
        agent.setEmail(rider.getEmail());
        agent.setTeamId(getTookanTeamIdFromShippoTeamId(rider.getTeamId()));
        agent.setTransport_type("2");
        agent.setTransport_desc("");
        agent.setLicense("");
        agent.setColor("");
        agent.setTimezone("-430");
        agent.setFirst_name(rider.getFirstName());
        agent.setLast_name(rider.getLastName());
        agent.setPhone(rider.getMobile());
        return agent;
    }

    private static String getTookanTeamIdFromShippoTeamId(Integer teamId) {
        String queryString = "select tookan_id from team where id = " + teamId;
        SqlQuery query = PostgressDbConf.getOldDb().createSqlQuery(queryString);
        SqlRow sqlRow = query.findUnique();
        if(sqlRow != null){
            return sqlRow.getString("tookan_id");
        }
        return null;
    }

    private static Integer getFleetIdFromRiderId(Integer id) {
        String queryString = "select agent_id from tookan_agent where id = " + id;
        SqlQuery query = PostgressDbConf.getRiderDb().createSqlQuery(queryString);
        SqlRow sqlRow = query.findUnique();
        if(sqlRow != null){
            return sqlRow.getInteger("agent_id");
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
