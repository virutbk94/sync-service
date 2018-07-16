package shippo.global;

import com.avaje.ebean.EbeanServer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import shippo.global.entities.tookan.Task;
import shippo.global.entities.v0.rider.*;
import shippo.global.entities.v1.*;
import shippo.global.io.rider.oldDAO;
import shippo.global.io.rider.riderDAO;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class Mapping {

    /**
     * map từ Transport Task cũ sang Transportation Task mới
     */
    public static TransportationTask mapTransportTaskToTransportationTask(TransportTask task) {
        return mapTransportTaskToTransportationTask(task, false);
    }

    /**
     * map từ Transport Task cũ sang Transportation Task vẫn giữ dữ liệu trong db
     */
    public static TransportationTask mapTransportTaskToTransportationTask(TransportTask task, boolean holdDbdata) {
        TransportationTask transportationTask = null;

        if (holdDbdata) {
            EbeanServer riderDb = PostgressDbConf.getRiderDb();
            transportationTask = riderDb.find(TransportationTask.class).where().idEq(task.getId()).findUnique();
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
        transportationTask.setIsLastMileDelivery(task.getTaskType().equals(TransportTask.TransportTaskType.FINAL.getCode()));
        transportationTask.setFailReason(task.getReason());
        transportationTask.setRealCod(task.getRealCod());
        transportationTask.setCod(task.getCod());
        transportationTask.setReasonCode(mapStringToReasonCodes(task.getReasonCode()));
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

    // Map task to taskHistory
    public static Set<TaskHistory> mapTaskToTaskHistories(TransportTask recordAfter) {
        Set<TaskHistory> taskHistories = new HashSet<>();
        int taskId = recordAfter.getId();
        int version = recordAfter.getVersion();
        if (recordAfter.getTimeline() == null) {
            return taskHistories;
        }
        for (TransportTask.TimeLine timelineObj : recordAfter.getTimeline()) {
            TaskHistory taskHistory = new TaskHistory();
            taskHistory.setTaskId(taskId);
            taskHistory.setScope(timelineObj.getScope());
            taskHistory.setLabelDescription(timelineObj.getLabel());
            taskHistory.setColor(timelineObj.getColor());
            taskHistory.setCreatedAt(Mapping.postgreTimestampStringToTimestamp(timelineObj.getTime()));
            taskHistory.setVersion(version);
            taskHistories.add(taskHistory);
        }
        return taskHistories;
    }

    public static List<TaskHistory> mapStringToTaskHistories(String jsonString) {
        if (jsonString == null) return new ArrayList<>();
        Type listType = new TypeToken<ArrayList<TaskHistory>>() {
        }.getType();
        return new Gson().fromJson(jsonString, listType);
    }

    public static List<Metadata> mapStringToMetafields(String jsonString) {
        if (jsonString == null) return new ArrayList<>();
        Type listType = new TypeToken<ArrayList<Metadata>>() {
        }.getType();
        return new Gson().fromJson(jsonString, listType);
    }

    public static List<TransportTask.TimeLine> mapStringToTimeLines(String jsonString) {
        if (jsonString == null) return new ArrayList<>();
        Type listType = new TypeToken<ArrayList<TransportTask.TimeLine>>() {
        }.getType();
        return new Gson().fromJson(jsonString, listType);
    }

    public static List<ReasonCode> mapStringToReasonCodes(String jsonString) {
        if (jsonString == null) return new ArrayList<>();
        Type listType = new TypeToken<ArrayList<ReasonCode>>() {
        }.getType();
        return new Gson().fromJson(jsonString, listType);
    }

    public static Metadata mapMetaFieldToMetaData(MetaFields recordAfter) {
        Metadata metadata = new Metadata();
        metadata.setKey(recordAfter.getKey());
        metadata.setLabel(recordAfter.getLabel());
        metadata.setValue(recordAfter.getValue());
        metadata.setTime(Mapping.timespampToPostgreTimestampString(recordAfter.getModifiedTime()));
        return metadata;
    }

    public static TransportTask.TimeLine mapTaskHistoryToTimeLine(TaskHistory recordAfter) {
        TransportTask.TimeLine timeLine = new TransportTask.TimeLine();
        timeLine.setScope(recordAfter.getScope());
        timeLine.setLabel(recordAfter.getLabelDescription());
        timeLine.setColor(recordAfter.getColor());
        timeLine.setTime(Mapping.timespampToPostgreTimestampString(recordAfter.getCreatedAt()));
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
        riderShift.setBarcodes(taskBatch.getBatchData());
        riderShift.setCreatedAt(taskBatch.getCreatedTime());
        riderShift.setUpdatedAt(taskBatch.getModifiedTime());
        riderShift.setVersion(taskBatch.getVersion());

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

    public static TookanAgent mapUserInterg2Tookan(UserIntegrationAccount userIntegrationAccount) {
        TookanAgent tookanAgent = new TookanAgent();
        // mapping here
        tookanAgent.setId(userIntegrationAccount.getId());
        tookanAgent.setAgent(userIntegrationAccount.getAccount());
        tookanAgent.setAgentId(Integer.parseInt(userIntegrationAccount.getAccountId()));
        tookanAgent.setRiderId(userIntegrationAccount.getUserId());
        tookanAgent.setVersion(userIntegrationAccount.getVersion());
        tookanAgent.setUpdatedAt(userIntegrationAccount.getModifiedTime());
        return tookanAgent;
    }

    public static Rider mapUserToRider(Users user) {
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
        Date date = user.getRegisteredDate();
        if (date != null)
            rider.setCreatedAt(new Timestamp(user.getRegisteredDate().getTime()));
        rider.setUpdatedAt(user.getModifiedTime());

        rider.setState(user.getState());

        return rider;
    }

    public static shippo.global.entities.v1.Users mapUserNew2Old(shippo.global.entities.v0.oauth.Users usersV1) {
        shippo.global.entities.v1.Users usersTmp = new shippo.global.entities.v1.Users();
        usersTmp.setId(usersV1.getId());
        usersTmp.setRealm(usersV1.getSection());
        usersTmp.setUsername(usersV1.getUsername());
        usersTmp.setPassword(usersV1.getPassword());
        usersTmp.setEmail(usersV1.getEmail());
        if (usersV1.getIsEmailVerified() != null) {
            usersTmp.setIsEmailVerified(usersV1.getIsEmailVerified() ? (short) 1 : 0);
        }
        usersTmp.setState(usersV1.getState());
        usersTmp.setRegisteredDate(usersV1.getCreatedAt());
        if (usersV1.getIsRequiredChangePass() != null) {
            usersTmp.setNeedChangePass(usersV1.getIsRequiredChangePass() ? (short) 1 : 0);
        }
        usersTmp.setLastChangedPass(usersV1.getChangePassAt());
        usersTmp.setLevel(usersV1.getLevel());
        usersTmp.setVersion(usersV1.getVersion());
        usersTmp.setTagline("");
        usersTmp.setAvatar("");
        usersTmp.setRegisteredDate(new Date());
        usersTmp.setModifiedTime(new Timestamp(System.currentTimeMillis()));
        return usersTmp;
    }

    public static shippo.global.entities.v0.oauth.Users mapUserOld2New(shippo.global.entities.v1.Users usersV0) {
        shippo.global.entities.v0.oauth.Users userTmp = new shippo.global.entities.v0.oauth.Users();
        boolean isEmailVerified = false;
        boolean isNeedChangePass = false;
        userTmp.setId(usersV0.getId());
        userTmp.setSection(usersV0.getRealm());
        userTmp.setUsername(usersV0.getUsername());
        userTmp.setPassword(usersV0.getPassword());
        userTmp.setEmail(usersV0.getEmail());
        if (usersV0.getIsEmailVerified() == 1) {
            isEmailVerified = true;
        }
        userTmp.setIsEmailVerified(isEmailVerified);
        userTmp.setState(usersV0.getState());
        userTmp.setCreatedAt(usersV0.getRegisteredDate() == null ? new Date() : usersV0.getRegisteredDate());

        if (usersV0.getNeedChangePass() != null)
            if (usersV0.getNeedChangePass() == 1) {
                isNeedChangePass = true;
            }

        userTmp.setIsRequiredChangePass(isNeedChangePass);
        userTmp.setUpdatedAt(new Date());
        userTmp.setChangePassAt(usersV0.getLastChangedPass());
        userTmp.setLevel(usersV0.getLevel());
        userTmp.setVersion(usersV0.getVersion());
        return userTmp;
    }

    public static Set<MetaFields> mapTaskToMetaFields(RiderShift recordAfter) {
        Set<MetaFields> metaFields = new HashSet<>();
        int taskId = recordAfter.getId();
        int version = recordAfter.getVersion();
        List<Metadata> metadataList = recordAfter.getMetadata();
        for(Metadata object : metadataList){
            MetaFields metaField = new MetaFields();
            metaField.setObjectId(taskId);
            metaField.setObjectType(shippo.global.type.Type.MetaFieldObjectType.TASK_BATCH);
            metaField.setVersion(version);
            metaField.setLabel(object.getLabel());
            metaField.setKey(object.getKey());
            metaField.setValue(object.getValue());
            metaField.setModifiedTime(Mapping.postgreTimestampStringToTimestamp(object.getTime()));
            metaFields.add(metaField);
        }
        return metaFields;
    }

    public static Set<MetaFields> mapTaskToMetaFields(TransportationTask recordAfter) {
        Set<MetaFields> metaFields = new HashSet<>();
        int taskId = recordAfter.getId();
        int version = recordAfter.getVersion();
        List<Metadata> metadataList = recordAfter.getMetadata();
        for(Metadata object : metadataList){
            MetaFields metaField = new MetaFields();
            metaField.setObjectId(taskId);
            metaField.setObjectType(shippo.global.type.Type.MetaFieldObjectType.TASK_BATCH);
            metaField.setVersion(version);
            metaField.setLabel(object.getLabel());
            metaField.setKey(object.getKey());
            metaField.setValue(object.getValue());
            metaField.setModifiedTime(Mapping.postgreTimestampStringToTimestamp(object.getTime()));
            metaFields.add(metaField);
        }
        return metaFields;
    }

    public static TaskBatch mapRiderShiftsToTaskBatch(RiderShift riderShift) {
        TaskBatch taskBatch = new TaskBatch();
        taskBatch.setId(riderShift.getId());
        taskBatch.setCreatedBy(riderShift.getCreatedBy());
        taskBatch.setShipperId(riderShift.getRiderId());
        taskBatch.setTaskCount(riderShift.getTaskCount());
        taskBatch.setTaskType(riderShift.getTaskType());
        taskBatch.setBatchData(riderShift.getBarcodes());
        taskBatch.setCreatedTime(riderShift.getCreatedAt());
        taskBatch.setVersion(riderShift.getVersion());
        return taskBatch;
    }

    public static TransportTask mapTransportationTaskToTransportTask(TransportationTask transportationTask) {
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

    public static UserIntegrationAccount mapTookan2UserIntergration(TookanAgent tookanAgent) {
        UserIntegrationAccount userIntegrationAccount = new UserIntegrationAccount();
        userIntegrationAccount.setId(0);
        userIntegrationAccount.setIntegrationId(tookanAgent.getId());
        userIntegrationAccount.setUserId(tookanAgent.getRiderId());
        userIntegrationAccount.setAccount(tookanAgent.getAgent());
        userIntegrationAccount.setAccountId(String.valueOf(tookanAgent.getAgentId()));
        userIntegrationAccount.setVersion(tookanAgent.getVersion());
        return userIntegrationAccount;
    }

    public static Users mapRiderToUsers(Rider rider) {
        Users user = new Users();
        user.setId(rider.getId());
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
        // TODO

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

    public static Timestamp postgreTimestampStringToTimestamp(String timestampString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'");
        try {
            java.util.Date date = sdf.parse(timestampString);
            // lay time stamp cua gmt +7
            return new Timestamp(date.getTime() + 7 * 3600000L);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String timespampToPostgreTimestampString(Timestamp timestamp) {
        // lay time stamp cua gmt - 7h truoc
        Timestamp gmtTimestamp = new Timestamp(timestamp.getTime() - 7 * 3600000L);
        java.sql.Date date = new java.sql.Date(gmtTimestamp.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss.SSS\'Z\'");
        return sdf.format(date);
    }
}
