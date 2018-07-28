package shippo.rider_service;

import com.avaje.ebean.EbeanServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import shippo.global.Constants;
import shippo.global.PostgressDbConf;
import shippo.global.Utils;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

import shippo.global.io.rider.oldDAO;
import shippo.global.io.rider.riderDAO;
import shippo.tookan_service.entities.shippo.Team;
import shippo.tookan_service.entities.tookan.Task;
import shippo.tookan_service.entities.tookan.TeamTookan;
import shippo.rider_service.entities.v0.*;
import shippo.rider_service.entities.v1.*;

import java.util.regex.Pattern;

public class Mapping {

    private static Logger LOG = LoggerFactory.getLogger(Mapping.class);

    /**
     * map từ Transport Task cũ sang Transportation Task mới
     */
    public static TransportationTask mapTransportTask2TransportationTask(TransportTask task) {
        return mapTransportTask2TransportationTask(task, false);
    }

    /**
     * map từ Transport Task cũ sang Transportation Task vẫn giữ dữ liệu trong db
     */
    public static TransportationTask mapTransportTask2TransportationTask(TransportTask task, boolean holdDbdata) {
        TransportationTask transportationTask = null;

        if (holdDbdata) {
            EbeanServer riderDb = PostgressDbConf.getRiderDb();
            transportationTask = riderDb.find(TransportationTask.class)
                    .where().idEq(task.getId()).findUnique();
        }

        if (transportationTask == null)
            transportationTask = new TransportationTask();
        transportationTask.setId(task.getId());
        transportationTask.setCreatedBy(task.getCreatedBy());
        transportationTask.setMerchantId(task.getCustomerId());
        transportationTask.setDescription(task.getDescription());
        transportationTask.setAssignee(task.getAssignedTo());
        transportationTask.setAssigneeType(task.getAssigneeType());
        transportationTask.setState(task.getStatus());
        transportationTask.setNote(task.getNote());
        transportationTask.setType(task.getType());
        transportationTask.setPickupDetail(task.getPickupDetail());
        transportationTask.setPickLocationIdsPath(_calculatePickLocationIdsPath(task));
        transportationTask.setPickupContactName(task.getPickupContactName());
        transportationTask.setPickupContactPhone(task.getPickupContactPhone());
        transportationTask.setPickupFullAddress(task.getPickupAddressFull());
        transportationTask.setDeliverDetail(task.getDeliverDetail());
        transportationTask.setDeliverLocationIdsPath(_calculateDeliverLocationIdsPath(task));
        transportationTask.setDeliverContactName(task.getDeliverContactName());
        transportationTask.setDeliverContactPhone(task.getDeliverContactPhone());
        transportationTask.setDeliverFullAddress(task.getDeliverAddressFull());
        transportationTask.setIsLastMileDelivery(task.getTaskType().equals(TransportTask.TransportTaskType.FINAL));
        transportationTask.setFailReason(task.getReason());
        transportationTask.setRealCod(task.getRealCod());
        transportationTask.setCod(task.getCod());
        transportationTask.setReasonCode(mapString2ReasonCodes(task.getReasonCode()));
        transportationTask.setTookanJobId(task.getJobId());
        transportationTask.setObjectType(task.getObjectType());
        transportationTask.setObjectId(task.getObjectId());
        transportationTask.setObjectCode(task.getObjectCode());
        transportationTask.setCod(task.getCod());
        transportationTask.setRealCod(task.getRealCod());
        transportationTask.setTaskDeadline(task.getTaskDeadline());
        transportationTask.setRiderShiftId(task.getBatchId());
        transportationTask.setRequestId(task.getRequestId());
        transportationTask.setInternalReasonFail(task.getInternalReasonFail());
        transportationTask.setRecipientPayCourierFee(task.getFeeType());
        transportationTask.setRequestId(task.getRequestId());
        transportationTask.setCreatedAt(task.getCreatedTime());
        transportationTask.setUpdatedAt(task.getModifiedTime());
        transportationTask.setStartedAt(task.getStartedTime());
        transportationTask.setFailedAt(task.getFailedTime());
        transportationTask.setSuccessAt(task.getSuccessTime());
        transportationTask.setPickupBefore(task.getPickupBefore());
        transportationTask.setDeliverBefore(task.getDeliverBefore());
        transportationTask.setVersion(task.getVersion());

        return transportationTask;
    }

    /**
     * tính giá trị trường pick_location_ids_path
     */
    private static String _calculatePickLocationIdsPath(TransportTask task) {
        if (task.getPickupProvinceId() == null || task.getPickupDistrictId() == null) return null;

        return task.getPickupProvinceId().toString() + "." + task.getPickupDistrictId().toString();
    }

    /**
     * tính giá trị trường deliver_location_ids_path
     */
    private static String _calculateDeliverLocationIdsPath(TransportTask task) {
        if (task.getDeliverProvinceId() == null || task.getDeliverDistrictId() == null) return null;

        return task.getDeliverProvinceId().toString() + "." + task.getDeliverDistrictId().toString();
    }

    public static TransportTask mapTransportationTask2TransportTask(TransportationTask transportationTask) {
        TransportTask transportTask = new TransportTask();
        transportTask.setId(transportationTask.getId());
        transportTask.setCreatedBy(transportationTask.getCreatedBy());
        transportTask.setCustomerId(transportationTask.getMerchantId());
        transportTask.setDescription(transportationTask.getDescription());
        transportTask.setAssignedTo(transportationTask.getAssignee());
        transportTask.setAssigneeType(transportationTask.getAssigneeType());
        transportTask.setStatus(transportationTask.getState());
        transportTask.setNote(transportationTask.getNote());
        transportTask.setType(transportationTask.getType());
        transportTask.setPickupDetail(transportationTask.getPickupDetail());
        transportTask.setPickupContactName(transportationTask.getPickupContactName());
        transportTask.setPickupContactPhone(transportationTask.getPickupContactPhone());
        transportTask.setPickupAddressFull(transportationTask.getPickupFullAddress());
        transportTask.setDeliverDetail(transportationTask.getDeliverDetail());
        transportTask.setDeliverContactName(transportationTask.getDeliverContactName());
        transportTask.setDeliverContactPhone(transportationTask.getDeliverContactPhone());
        transportTask.setDeliverAddressFull(transportationTask.getDeliverFullAddress());
        transportTask.setTaskType(transportationTask.getIsLastMileDelivery() ? "FINAL" : "MEDIATE");
        transportTask.setReason(transportationTask.getFailReason());

        transportTask.setReasonCode(new JSONArray(transportationTask.getReasonCode()).toString());
        transportTask.setJobId(transportationTask.getTookanJobId());
        transportTask.setObjectType(transportationTask.getObjectType());
        transportTask.setObjectId(transportationTask.getObjectId());
        transportTask.setObjectCode(transportationTask.getObjectCode());
        transportTask.setCod(transportationTask.getCod());
        transportTask.setRealCod(transportationTask.getRealCod());
        transportTask.setTaskDeadline(transportationTask.getTaskDeadline());
        transportTask.setBatchId(transportationTask.getRiderShiftId());
        transportTask.setRequestId(transportationTask.getRequestId());
        transportTask.setInternalReasonFail(transportationTask.getInternalReasonFail());
        transportTask.setFeeType(transportationTask.getRecipientPayCourierFee());
        transportTask.setPickupBefore(transportationTask.getPickupBefore());
        transportTask.setDeliverBefore(transportationTask.getDeliverBefore());
        transportTask.setCreatedTime(transportationTask.getCreatedAt());
        transportTask.setModifiedTime(transportationTask.getUpdatedAt());
        transportTask.setStartedTime(transportationTask.getStartedAt());
        transportTask.setFailedTime(transportationTask.getFailedAt());
        transportTask.setSuccessTime(transportationTask.getSuccessAt());
        transportTask.setVersion(transportationTask.getVersion());

        transportTask.setPickupProvinceId(_calculatePickupProvinceId(transportationTask.getPickLocationIdsPath()));
        transportTask.setPickupDistrictId(_calculatePickupDistrictId(transportationTask.getPickLocationIdsPath()));

        transportTask.setDeliverProvinceId(_calculateDeliverProvinceId(transportationTask.getDeliverLocationIdsPath()));
        transportTask.setDeliverDistrictId(_calculateDeliverDistrictId(transportationTask.getDeliverLocationIdsPath()));
        return transportTask;
    }

    private static Integer _calculatePickupProvinceId(String pickLocationIdsPath) {
        if (pickLocationIdsPath == null) return null;

        String[] ids = pickLocationIdsPath.split(Pattern.quote("."));
        return Integer.parseInt(ids[0]);
    }

    private static Integer _calculatePickupDistrictId(String pickLocationIdsPath) {
        if (pickLocationIdsPath == null) return null;

        String[] ids = pickLocationIdsPath.split(Pattern.quote("."));
        return Integer.parseInt(ids[1]);
    }
    private static Integer _calculateDeliverProvinceId(String deliverLocationIdsPath) {
        if (deliverLocationIdsPath == null) return null;

        String[] ids = deliverLocationIdsPath.split(Pattern.quote("."));
        return Integer.parseInt(ids[0]);
    }

    private static Integer _calculateDeliverDistrictId(String deliverLocationIdsPath) {
        if (deliverLocationIdsPath == null) return null;

        String[] ids = deliverLocationIdsPath.split(Pattern.quote("."));
        return Integer.parseInt(ids[1]);
    }

    // Map task to taskHistory

    public static List<TaskHistory> mapTransportTask2TaskHistories(TransportTask recordAfter) {
        List<TaskHistory> taskHistories = new ArrayList<>();
        int taskId = recordAfter.getId();
        int version = recordAfter.getVersion();
        if (recordAfter.getTimeline() == null) {
            return taskHistories;
        }
        for (TimeLine timelineObj : recordAfter.getTimeline()) {
            TaskHistory taskHistory = new TaskHistory();
            taskHistory.setTaskId(taskId);
            taskHistory.setScope(timelineObj.getScope());
            taskHistory.setLabelDescription(timelineObj.getLabel());
            taskHistory.setColor(timelineObj.getColor());
            taskHistory.setCreatedAt(Utils.postgreTimestampStringToTimestamp(timelineObj.getTime()));
            taskHistory.setUpdatedAt(Utils.postgreTimestampStringToTimestamp(timelineObj.getTime()));
            taskHistory.setVersion(version);
            taskHistories.add(taskHistory);
        }
        return taskHistories;
    }

    public static List<TaskHistory> mapString2TaskHistories(String jsonString) {
        if (jsonString == null) return new ArrayList<>();
        Type listType = new TypeToken<ArrayList<TaskHistory>>() {
        }.getType();
        return new Gson().fromJson(jsonString, listType);
    }

    public static List<Metadata> mapString2Metafields(String jsonString) {
        if (jsonString == null) return new ArrayList<>();
        Type listType = new TypeToken<ArrayList<Metadata>>() {
        }.getType();
        return new Gson().fromJson(jsonString, listType);
    }

    public static List<TimeLine> mapString2TimeLines(String jsonString) {
        if (jsonString == null) return new ArrayList<>();
        Type listType = new TypeToken<ArrayList<TimeLine>>() {
        }.getType();
        return new Gson().fromJson(jsonString, listType);
    }

    public static List<ReasonCode> mapString2ReasonCodes(String jsonString) {
        if (jsonString == null) return new ArrayList<>();
        Type listType = new TypeToken<ArrayList<ReasonCode>>() {
        }.getType();
        return new Gson().fromJson(jsonString, listType);
    }

    public static Metadata mapMetaField2MetaData(MetaFields recordAfter) {
        Metadata metadata = new Metadata();
        metadata.setKey(recordAfter.getKey());
        metadata.setLabel(recordAfter.getLabel());
        metadata.setValue(recordAfter.getValue());
        metadata.setTime(Utils.timespampToPostgreTimestampString(recordAfter.getModifiedTime()));
        return metadata;
    }

    public static TimeLine mapTaskHistory2TimeLine(TaskHistory recordAfter) {
        TimeLine timeLine = new TimeLine();
        timeLine.setScope(recordAfter.getScope());
        timeLine.setLabel(recordAfter.getLabelDescription());
        timeLine.setColor(recordAfter.getColor());
        timeLine.setTime(Utils.timespampToPostgreTimestampString(recordAfter.getCreatedAt()));
        return timeLine;
    }

    public static RiderShift mapTaskBatch2RiderShift(TaskBatch taskBatch) {
        return mapTaskBatch2RiderShift(taskBatch, false);
    }

    public static RiderShift mapTaskBatch2RiderShift(TaskBatch taskBatch, boolean dataDbHold) {
        RiderShift riderShift = null;

        if (dataDbHold) {
            EbeanServer riderDb = PostgressDbConf.getRiderDb();
            riderShift = riderDb.find(RiderShift.class)
                    .where().idEq(taskBatch.getId()).findUnique();
        }

        if (riderShift == null) riderShift = new RiderShift();

        riderShift.setId(taskBatch.getId());
        riderShift.setCreatedBy(taskBatch.getCreatedBy());
        riderShift.setRiderId(taskBatch.getShipperId());
        riderShift.setTaskCount(taskBatch.getTaskCount());
        riderShift.setTaskType(taskBatch.getTaskType());
//        riderShift.setBarcodes(mapBatchDataToBarcode(taskBatch.getBatchData()));
        if(taskBatch.getBatchData() != null){
            Map<String, Object> barcodes = new LinkedHashMap<>();
            for (Object object : taskBatch.getBatchData()) {
                Map<String, String> barcodeMap = (Map) object;
                barcodes.put(barcodeMap.get("objectCode"), barcodeMap);
            }
            riderShift.setBarcodes(barcodes);
        }
        riderShift.setCreatedAt(taskBatch.getCreatedTime());
        riderShift.setUpdatedAt(taskBatch.getModifiedTime());
        riderShift.setVersion(taskBatch.getVersion());
        riderShift.setHubId(taskBatch.getHubId());

        // If(create at < 11h and >= 21h
        Timestamp createAt = taskBatch.getCreatedTime();
        int hour = createAt.getHours();
        int shift_id = shippo.global.type.Type.Shift.MORNING;
        if (hour >= 11 && hour < 17)
            shift_id = shippo.global.type.Type.Shift.AFTERNOON;
        if (hour >= 17 && hour < 21)
            shift_id = shippo.global.type.Type.Shift.EVENING;

        riderShift.setShiftId(shift_id);
        return riderShift;
    }

    private static Map mapBatchDataToBarcode(List<BatchData> batchDatas) {
        if(batchDatas == null) return null;
        Map<String, BatchData> barcodes = new HashMap<>();
        for(BatchData batchData : batchDatas){
            if(batchData == null) continue;
            barcodes.put(batchData.getObjectCode(), batchData);
        }
        return barcodes;
    }

    public static TaskBatch mapRiderShift2TaskBatch(RiderShift riderShift) {
        TaskBatch taskBatch = new TaskBatch();
        taskBatch.setId(riderShift.getId());
        taskBatch.setCreatedBy(riderShift.getCreatedBy());
        taskBatch.setShipperId(riderShift.getRiderId());
        taskBatch.setTaskCount(riderShift.getTaskCount());
        taskBatch.setTaskType(riderShift.getTaskType());
//        taskBatch.setBatchData(mapBarcodesToBatchData(riderShift.getBarcodes()));
        if (riderShift.getBarcodes() != null){
            List<Object> batchData = new ArrayList<>();
            for (String key : ((Map<String, Object>) riderShift.getBarcodes()).keySet()) {
                batchData.add(((Map<String, Object>) riderShift.getBarcodes()).get(key));
            }
            taskBatch.setBatchData(batchData);
        }
        taskBatch.setCreatedTime(riderShift.getCreatedAt());
        taskBatch.setModifiedTime(riderShift.getUpdatedAt());
        taskBatch.setVersion(riderShift.getVersion());
        taskBatch.setHubId(riderShift.getHubId());
        return taskBatch;
    }

    private static List<BatchData> mapBarcodesToBatchData(Map<String,BatchData> barcodes) {
        if(barcodes == null) return null;
        List<BatchData> batchDataList = new ArrayList<>();
        for(String key : barcodes.keySet()){
            if(barcodes.get(key) != null){
                batchDataList.add(barcodes.get(key));
            }
        }
        return batchDataList;
    }

//    public static TaskBatchClone mapRiderShift2TaskBath(RiderShiftClone riderShift) {
//        TaskBatchClone taskBatch = new TaskBatchClone();
//
//        taskBatch.setId(riderShift.getId());
//        taskBatch.setCreatedBy(riderShift.getCreatedBy());
//        taskBatch.setShipperId(riderShift.getRiderId());
//        taskBatch.setTaskCount(riderShift.getTaskCount());
//        taskBatch.setTaskType(riderShift.getTaskType());
//
//        if (riderShift.getBarcodes() != null){
//            List<Object> batchData = new ArrayList<>();
//            for (String key : ((Map<String, Object>) riderShift.getBarcodes()).keySet()) {
//                batchData.add(((Map<String, Object>) riderShift.getBarcodes()).get(key));
//            }
//            taskBatch.setBatchData(batchData);
//        }
//        taskBatch.setHubId(riderShift.getHubId());
//
//        taskBatch.setCreatedTime(riderShift.getCreatedAt());
//        taskBatch.setModifiedTime(riderShift.getUpdatedAt());
//        taskBatch.setVersion(riderShift.getVersion());
//
//        return taskBatch;
//    }

    public static TookanAgent mapUserInterg2Tookan(UserIntegrationAccount userIntegrationAccount) {
        TookanAgent tookanAgent = new TookanAgent();
        // If not tookan
        if(!userIntegrationAccount.getIntegrationId().equals(1)) return null;
        // mapping here
        tookanAgent.setId(userIntegrationAccount.getId());
        tookanAgent.setAgent(userIntegrationAccount.getAccount());
        tookanAgent.setAgentId(Integer.parseInt(userIntegrationAccount.getAccountId()));
        tookanAgent.setRiderId(userIntegrationAccount.getUserId());
        tookanAgent.setVersion(userIntegrationAccount.getVersion());
        tookanAgent.setUpdatedAt(userIntegrationAccount.getModifiedTime());
        return tookanAgent;
    }

    public static UserIntegrationAccount mapTookan2UserIntergration(TookanAgent tookanAgent) {
        UserIntegrationAccount userIntegrationAccount = new UserIntegrationAccount();
        userIntegrationAccount.setId(tookanAgent.getId());
        userIntegrationAccount.setUserId(tookanAgent.getRiderId());
        userIntegrationAccount.setAccount(tookanAgent.getAgent());
        // IntegrationId = 1, mac dinh la tookan
        userIntegrationAccount.setIntegrationId(1);
        userIntegrationAccount.setAccountId(String.valueOf(tookanAgent.getAgentId()));
        userIntegrationAccount.setVersion(tookanAgent.getVersion());
        userIntegrationAccount.setModifiedTime(tookanAgent.getUpdatedAt());
        return userIntegrationAccount;
    }

    public static Rider mapUser2Rider(Users user) {
        Rider rider = new Rider();
        rider.setUserId(user.getId());
        rider.setUsername(user.getUsername());
        rider.setEmail(user.getEmail());
        rider.setFirstName(user.getFirstName());
        rider.setLastName(user.getLastName());
        rider.setMobile(user.getMobile());
        rider.setTagLine(user.getTagline());
        rider.setAvatar(user.getAvatar());
        rider.setVersion(user.getVersion());
        rider.setCreatedAt(user.getRegisteredDate());
        rider.setUpdatedAt(user.getModifiedTime());

        rider.setState(user.getState());

        return rider;
    }

    public static Users mapRiderToUsers(Rider rider) {
        Users user = new Users();
        user.setId(rider.getUserId());
        user.setRealm(Users.Realm.STAFF);
        user.setState(rider.getState());
        user.setFirstName(rider.getFirstName());
        user.setLastName(rider.getLastName());
        user.setMobile(rider.getMobile());
        user.setAvatar(rider.getAvatar());
        user.setVersion(rider.getVersion());
        user.setTagline(rider.getTagLine());
        user.setRegisteredDate(rider.getCreatedAt());
        user.setUsername(rider.getUsername());
        user.setEmail(rider.getEmail());
        return user;
    }

    public static List<MetaFields> mapTask2MetaFields(RiderShift recordAfter) {
        List<MetaFields> metaFields = new ArrayList<>();
        int taskId = recordAfter.getId();
        int version = recordAfter.getVersion();
        List<Metadata> metadataList = recordAfter.getMetadata();
        if(metadataList == null) return metaFields;
        for(Metadata object : metadataList){
            MetaFields metaField = new MetaFields();
            metaField.setObjectId(taskId);
            metaField.setObjectType(shippo.global.type.Type.MetaFieldObjectType.TASK_BATCH);
            metaField.setVersion(version);
            metaField.setLabel(object.getLabel());
            metaField.setKey(object.getKey());
            metaField.setValue(object.getValue());
            metaField.setModifiedTime(Utils.postgreTimestampStringToTimestamp(object.getTime()));
            metaFields.add(metaField);
        }
        return metaFields;
    }

    public static List<MetaFields> mapTask2MetaFields(TransportationTask recordAfter) {
        List<MetaFields> metaFields = new ArrayList<>();
        int taskId = recordAfter.getId();
        int version = recordAfter.getVersion();
        List<Metadata> metadataList = recordAfter.getMetadata();
        if(metadataList == null) return metaFields;
        for(Metadata object : metadataList){
            MetaFields metaField = new MetaFields();
            metaField.setObjectId(taskId);
            metaField.setObjectType(shippo.global.type.Type.MetaFieldObjectType.TASK_BATCH);
            metaField.setVersion(version);
            metaField.setLabel(object.getLabel());
            metaField.setKey(object.getKey());
            metaField.setValue(object.getValue());
            metaField.setModifiedTime(Utils.postgreTimestampStringToTimestamp(object.getTime()));
            metaFields.add(metaField);
        }
        return metaFields;
    }

    public static Task mapTransportationTask2Task(TransportationTask transportationTask) {

        Task task = new Task();
        task.setJob_id(transportationTask.getTookanJobId());
//        task.setStatus(Constants.MAP_TASK_STATUS_WITH_TOOKAN.get(transportationTask.getState()));
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

        if (transportationTask.getType().equals(shippo.rider_service.conf.Constants.TaskType.PICKUP)) {
            // Set time
            Timestamp timestamp = transportationTask.getPickupBefore() != null
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

        } else if (transportationTask.getType().equals(shippo.rider_service.conf.Constants.TaskType.DELIVER)
                || transportationTask.getType().equals(shippo.rider_service.conf.Constants.TaskType.PICKUP_AND_DELIVER)) {
            // Set time
            Timestamp timestamp = transportationTask.getDeliverBefore() != null
                    ? transportationTask.getDeliverBefore() : new Timestamp(System.currentTimeMillis() + 2 * 3600000);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        if (Constants.TOOKAN_INTEGRATION_SETTING.isNull("delivery_fee")) {
            JSONObject deliveryFee = new JSONObject();
            if (transportationTask.getRecipientPayCourierFee().equals(shippo.rider_service.conf.Constants.FeeType.RECEIVER_PAY_FOR_DELIVERY)) {
                int order_id = transportationTask.getObjectId();
                Double fee = oldDAO.getFeeReviceOfOder(order_id);
                deliveryFee.put("label", Constants.TOOKAN_INTEGRATION_SETTING.getString("delivery_fee"));
                deliveryFee.put("data", Utils.formatMonney(fee));
            } else {
                deliveryFee.put("label", Constants.TOOKAN_INTEGRATION_SETTING.getString("delivery_fee"));
                deliveryFee.put("data", "0");
            }
            jsonArray.put(deliveryFee);
        }

        return jsonArray;
    }

    public static TeamTookan mapTeam2TeamTookan(Team team){
        TeamTookan teamTookan = new TeamTookan();
        teamTookan.setTeam_id(team.getTookanId());
        teamTookan.setTeam_name(team.getName());
        return teamTookan;
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
