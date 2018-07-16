package shippo.global.entities.v0.rider;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.DbJsonB;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the transportation_tasks database table.
 * 
 */
@Entity
@Table(name="transportation_tasks")

public class TransportationTask extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="accepted_at")
	@SerializedName("accepted_at")
	private Timestamp acceptedAt;

	@Column(name="assigned_at")
	@SerializedName("assigned_at")
	private Timestamp assignedAt;

	private Integer assignee;

	@Column(name="assignee_type")
	@SerializedName("assignee_type")
	private String assigneeType;

	@Column(name="cancelled_at")
	@SerializedName("cancelled_at")
	private Timestamp cancelledAt;

	private Double cod;

	@Column(name="created_at")
	@SerializedName("created_at")
	private Timestamp createdAt;

	@Column(name="created_by")
	@SerializedName("created_by")
	private Integer createdBy;

	@Column(name="decline_at")
	@SerializedName("decline_at")
	private Timestamp declineAt;

	@Column(name="deleted_at")
	@SerializedName("deleted_at")
	private Timestamp deletedAt;

	@Column(name="deliver_before")
	@SerializedName("deliver_before")
	private Timestamp deliverBefore;

	@Column(name="deliver_contact_name")
	@SerializedName("deliver_contact_name")
	private String deliverContactName;

	@Column(name="deliver_contact_phone")
	@SerializedName("deliver_contact_phone")
	private String deliverContactPhone;

	@Column(name="deliver_detail")
	@SerializedName("deliver_detail")
	private String deliverDetail;

	@Column(name="deliver_full_address")
	@SerializedName("deliver_full_address")
	private String deliverFullAddress;

	@Column(name="deliver_latitude")
	@SerializedName("deliver_latitude")
	private String deliverLatitude;

	@Column(name="deliver_location_ids_path")
	@SerializedName("deliver_location_ids_path")
	private String deliverLocationIdsPath;

	@Column(name="deliver_longitude")
	@SerializedName("deliver_longitude")
	private String deliverLongitude;

	private String description;

	@Column(name="fail_reason")
	@SerializedName("fail_reason")
	private String failReason;

	@Column(name="failed_at")
	@SerializedName("failed_at")
	private Timestamp failedAt;

	@Column(name="has_delivery")
	@SerializedName("has_delivery")
	private Boolean hasDelivery;

	@Column(name="has_pickup")
	@SerializedName("has_pickup")
	private Boolean hasPickup;

	@Column(name="in_progress_at")
	@SerializedName("in_progress_at")
	private Timestamp inProgressAt;

	@Column(name="internal_reason_fail")
	@SerializedName("internal_reason_fail")
	private String internalReasonFail;

	@Column(name="is_last_mile_delivery")
	@SerializedName("is_last_mile_delivery")
	private Boolean isLastMileDelivery;

	@Column(name="last_sync_at")
	@SerializedName("last_sync_at")
	private Timestamp lastSyncAt;

	@Column(name="merchant_id")
	@SerializedName("merchant_id")
	private Integer merchantId;
	@DbJsonB
	@Column(name="metadata")
	private List<Metadata> metadata;

	private String note;

	@Column(name="object_code")
	@SerializedName("object_code")
	private String objectCode;

	@Column(name="object_id")
	@SerializedName("object_id")
	private Integer objectId;

	@Column(name="object_type")
	@SerializedName("object_type")
	private String objectType;

	@Column(name="pick_location_ids_path")
	@SerializedName("pick_location_ids_path")
	private String pickLocationIdsPath;

	@Column(name="pickup_before")
	@SerializedName("pickup_before")
	private Timestamp pickupBefore;

	@Column(name="pickup_contact_name")
	@SerializedName("pickup_contact_name")
	private String pickupContactName;

	@Column(name="pickup_contact_phone")
	@SerializedName("pickup_contact_phone")
	private String pickupContactPhone;

	@Column(name="pickup_detail")
	@SerializedName("pickup_detail")
	private String pickupDetail;

	@Column(name="pickup_full_address")
	@SerializedName("pickup_full_address")
	private String pickupFullAddress;

	@Column(name="pickup_latitude")
	@SerializedName("pickup_latitude")
	private String pickupLatitude;

	@Column(name="pickup_longitude")
	@SerializedName("pickup_longitude")
	private String pickupLongitude;

	@Column(name="real_cod")
	@SerializedName("real_cod")
	private Double realCod;
	@DbJsonB
	@Column(name="reason_code")
	@SerializedName("reason_code")
	private List<ReasonCode> reasonCode;

	@Column(name="recipient_pay_courier_fee")
	@SerializedName("recipient_pay_courier_fee")
	private String recipientPayCourierFee;

	@Column(name="request_id")
	@SerializedName("request_id")
	private Integer requestId;

	@Column(name="rider_shift_id")
	@SerializedName("rider_shift_id")
	private Integer riderShiftId;

	@Column(name="started_at")
	@SerializedName("started_at")
	private Timestamp startedAt;

	private String state;

	@Column(name="success_at")
	@SerializedName("success_at")
	private Timestamp successAt;

	@Column(name="task_deadline")
	@SerializedName("task_deadline")
	private Timestamp taskDeadline;

	@Column(name="tookan_job_id")
	@SerializedName("tookan_job_id")
	private String tookanJobId;

	@Column(name="tracking_link")
	@SerializedName("tracking_link")
	private Boolean trackingLink;

	private String type;

	@Column(name="updated_at")
	@SerializedName("updated_at")
	private Timestamp updatedAt;

	private Integer version;

	public TransportationTask() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getAcceptedAt() {
		return this.acceptedAt;
	}

	public void setAcceptedAt(Timestamp acceptedAt) {
		this.acceptedAt = acceptedAt;
	}

	public Timestamp getAssignedAt() {
		return this.assignedAt;
	}

	public void setAssignedAt(Timestamp assignedAt) {
		this.assignedAt = assignedAt;
	}

	public Integer getAssignee() {
		return this.assignee;
	}

	public void setAssignee(Integer assignee) {
		this.assignee = assignee;
	}

	public String getAssigneeType() {
		return this.assigneeType;
	}

	public void setAssigneeType(String assigneeType) {
		this.assigneeType = assigneeType;
	}

	public Timestamp getCancelledAt() {
		return this.cancelledAt;
	}

	public void setCancelledAt(Timestamp cancelledAt) {
		this.cancelledAt = cancelledAt;
	}

	public Double getCod() {
		return this.cod;
	}

	public void setCod(Double cod) {
		this.cod = cod;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getDeclineAt() {
		return this.declineAt;
	}

	public void setDeclineAt(Timestamp declineAt) {
		this.declineAt = declineAt;
	}

	public Timestamp getDeletedAt() {
		return this.deletedAt;
	}

	public void setDeletedAt(Timestamp deletedAt) {
		this.deletedAt = deletedAt;
	}

	public Timestamp getDeliverBefore() {
		return this.deliverBefore;
	}

	public void setDeliverBefore(Timestamp deliverBefore) {
		this.deliverBefore = deliverBefore;
	}

	public String getDeliverContactName() {
		return this.deliverContactName;
	}

	public void setDeliverContactName(String deliverContactName) {
		this.deliverContactName = deliverContactName;
	}

	public String getDeliverContactPhone() {
		return this.deliverContactPhone;
	}

	public void setDeliverContactPhone(String deliverContactPhone) {
		this.deliverContactPhone = deliverContactPhone;
	}

	public String getDeliverDetail() {
		return this.deliverDetail;
	}

	public void setDeliverDetail(String deliverDetail) {
		this.deliverDetail = deliverDetail;
	}

	public String getDeliverFullAddress() {
		return this.deliverFullAddress;
	}

	public void setDeliverFullAddress(String deliverFullAddress) {
		this.deliverFullAddress = deliverFullAddress;
	}

	public String getDeliverLatitude() {
		return this.deliverLatitude;
	}

	public void setDeliverLatitude(String deliverLatitude) {
		this.deliverLatitude = deliverLatitude;
	}

	public String getDeliverLocationIdsPath() {
		return this.deliverLocationIdsPath;
	}

	public void setDeliverLocationIdsPath(String deliverLocationIdsPath) {
		this.deliverLocationIdsPath = deliverLocationIdsPath;
	}

	public String getDeliverLongitude() {
		return this.deliverLongitude;
	}

	public void setDeliverLongitude(String deliverLongitude) {
		this.deliverLongitude = deliverLongitude;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFailReason() {
		return this.failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public Timestamp getFailedAt() {
		return this.failedAt;
	}

	public void setFailedAt(Timestamp failedAt) {
		this.failedAt = failedAt;
	}

	public Boolean getHasDelivery() {
		return this.hasDelivery;
	}

	public void setHasDelivery(Boolean hasDelivery) {
		this.hasDelivery = hasDelivery;
	}

	public Boolean getHasPickup() {
		return this.hasPickup;
	}

	public void setHasPickup(Boolean hasPickup) {
		this.hasPickup = hasPickup;
	}

	public Timestamp getInProgressAt() {
		return this.inProgressAt;
	}

	public void setInProgressAt(Timestamp inProgressAt) {
		this.inProgressAt = inProgressAt;
	}

	public String getInternalReasonFail() {
		return this.internalReasonFail;
	}

	public void setInternalReasonFail(String internalReasonFail) {
		this.internalReasonFail = internalReasonFail;
	}

	public Boolean getIsLastMileDelivery() {
		return this.isLastMileDelivery;
	}

	public void setIsLastMileDelivery(Boolean isLastMileDelivery) {
		this.isLastMileDelivery = isLastMileDelivery;
	}

	public Timestamp getLastSyncAt() {
		return this.lastSyncAt;
	}

	public void setLastSyncAt(Timestamp lastSyncAt) {
		this.lastSyncAt = lastSyncAt;
	}

	public Integer getMerchantId() {
		return this.merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public List<Metadata> getMetadata() {
		return this.metadata;
	}

	public void setMetadata(List<Metadata> metadata) {
		this.metadata = metadata;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getObjectCode() {
		return this.objectCode;
	}

	public void setObjectCode(String objectCode) {
		this.objectCode = objectCode;
	}

	public Integer getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getObjectType() {
		return this.objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getPickLocationIdsPath() {
		return this.pickLocationIdsPath;
	}

	public void setPickLocationIdsPath(String pickLocationIdsPath) {
		this.pickLocationIdsPath = pickLocationIdsPath;
	}

	public Timestamp getPickupBefore() {
		return this.pickupBefore;
	}

	public void setPickupBefore(Timestamp pickupBefore) {
		this.pickupBefore = pickupBefore;
	}

	public String getPickupContactName() {
		return this.pickupContactName;
	}

	public void setPickupContactName(String pickupContactName) {
		this.pickupContactName = pickupContactName;
	}

	public String getPickupContactPhone() {
		return this.pickupContactPhone;
	}

	public void setPickupContactPhone(String pickupContactPhone) {
		this.pickupContactPhone = pickupContactPhone;
	}

	public String getPickupDetail() {
		return this.pickupDetail;
	}

	public void setPickupDetail(String pickupDetail) {
		this.pickupDetail = pickupDetail;
	}

	public String getPickupFullAddress() {
		return this.pickupFullAddress;
	}

	public void setPickupFullAddress(String pickupFullAddress) {
		this.pickupFullAddress = pickupFullAddress;
	}

	public String getPickupLatitude() {
		return this.pickupLatitude;
	}

	public void setPickupLatitude(String pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}

	public String getPickupLongitude() {
		return this.pickupLongitude;
	}

	public void setPickupLongitude(String pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}

	public Double getRealCod() {
		return this.realCod;
	}

	public void setRealCod(Double realCod) {
		this.realCod = realCod;
	}

	public List<ReasonCode> getReasonCode() {
		return this.reasonCode;
	}

	public void setReasonCode(List<ReasonCode> reasonCode) {
		this.reasonCode = reasonCode;
	}

	public String getRecipientPayCourierFee() {
		return this.recipientPayCourierFee;
	}

	public void setRecipientPayCourierFee(String recipientPayCourierFee) {
		this.recipientPayCourierFee = recipientPayCourierFee;
	}

	public Integer getRequestId() {
		return this.requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}

	public Integer getRiderShiftId() {
		return this.riderShiftId;
	}

	public void setRiderShiftId(Integer riderShiftId) {
		this.riderShiftId = riderShiftId;
	}

	public Timestamp getStartedAt() {
		return this.startedAt;
	}

	public void setStartedAt(Timestamp startedAt) {
		this.startedAt = startedAt;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getSuccessAt() {
		return this.successAt;
	}

	public void setSuccessAt(Timestamp successAt) {
		this.successAt = successAt;
	}

	public Timestamp getTaskDeadline() {
		return this.taskDeadline;
	}

	public void setTaskDeadline(Timestamp taskDeadline) {
		this.taskDeadline = taskDeadline;
	}

	public String getTookanJobId() {
		return this.tookanJobId;
	}

	public void setTookanJobId(String tookanJobId) {
		this.tookanJobId = tookanJobId;
	}

	public Boolean getTrackingLink() {
		return this.trackingLink;
	}

	public void setTrackingLink(Boolean trackingLink) {
		this.trackingLink = trackingLink;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}