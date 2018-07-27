package shippo.tookan_service.entities.shippo;

import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import shippo.rider_service.entities.v0.Metadata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the transportation_tasks database table.
 */
@Entity
@Table(name = "transportation_tasks")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransportationTask implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(name = "accepted_at")
    @SerializedName("accepted_at")
    private Timestamp acceptedAt;

    @Column(name = "assigned_at")
    @SerializedName("assigned_at")
    private Timestamp assignedAt;

    private Integer assignee;

    @Column(name = "assignee_type")
    @SerializedName("assignee_type")
    private String assigneeType;

    @Column(name = "cancelled_at")
    @SerializedName("cancelled_at")
    private Timestamp cancelledAt;

    private Double cod;

    @Column(name = "created_at")
    @SerializedName("created_at")
    private Timestamp createdAt;

    @Column(name = "created_by")
    @SerializedName("created_by")
    private Integer createdBy;

    @Column(name = "decline_at")
    @SerializedName("decline_at")
    private Timestamp declineAt;

    @Column(name = "deleted_at")
    @SerializedName("deleted_at")
    private Timestamp deletedAt;

    @Column(name = "deliver_before")
    @SerializedName("deliver_before")
    private Timestamp deliverBefore;

    @Column(name = "deliver_contact_name")
    @SerializedName("deliver_contact_name")
    private String deliverContactName;

    @Column(name = "deliver_contact_phone")
    @SerializedName("deliver_contact_phone")
    private String deliverContactPhone;

    @Column(name = "deliver_detail")
    @SerializedName("deliver_detail")
    private String deliverDetail;

    @Column(name = "deliver_full_address")
    @SerializedName("deliver_full_address")
    private String deliverFullAddress;

    @Column(name = "deliver_latitude")
    @SerializedName("deliver_latitude")
    private String deliverLatitude;

    @Column(name = "deliver_location_ids_path")
    @SerializedName("deliver_location_ids_path")
    private String deliverLocationIdsPath;

    @Column(name = "deliver_longitude")
    @SerializedName("deliver_longitude")
    private String deliverLongitude;

    private String description;

    @Column(name = "fail_reason")
    @SerializedName("fail_reason")
    private String failReason;

    @Column(name = "failed_at")
    @SerializedName("failed_at")
    private Timestamp failedAt;

    @Column(name = "has_delivery")
    @SerializedName("has_delivery")
    private Boolean hasDelivery;

    @Column(name = "has_pickup")
    @SerializedName("has_pickup")
    private Boolean hasPickup;

    @Column(name = "in_progress_at")
    @SerializedName("in_progress_at")
    private Timestamp inProgressAt;

    @Column(name = "internal_reason_fail")
    @SerializedName("internal_reason_fail")
    private String internalReasonFail;

    @Column(name = "is_last_mile_delivery")
    @SerializedName("is_last_mile_delivery")
    private Boolean isLastMileDelivery;

    @Column(name = "last_sync_at")
    @SerializedName("last_sync_at")
    private Timestamp lastSyncAt;

    @Column(name = "merchant_id")
    @SerializedName("merchant_id")
    private Integer merchantId;
    @DbJsonB
    @Column(name = "metadata")
    private List<Metadata> metadata;

    private String note;

    @Column(name = "object_code")
    @SerializedName("object_code")
    private String objectCode;

    @Column(name = "object_id")
    @SerializedName("object_id")
    private Integer objectId;

    @Column(name = "object_type")
    @SerializedName("object_type")
    private String objectType;

    @Column(name = "pick_location_ids_path")
    @SerializedName("pick_location_ids_path")
    private String pickLocationIdsPath;

    @Column(name = "pickup_before")
    @SerializedName("pickup_before")
    private Timestamp pickupBefore;

    @Column(name = "pickup_contact_name")
    @SerializedName("pickup_contact_name")
    private String pickupContactName;

    @Column(name = "pickup_contact_phone")
    @SerializedName("pickup_contact_phone")
    private String pickupContactPhone;

    @Column(name = "pickup_detail")
    @SerializedName("pickup_detail")
    private String pickupDetail;

    @Column(name = "pickup_full_address")
    @SerializedName("pickup_full_address")
    private String pickupFullAddress;

    @Column(name = "recipient_pay_courier_fee")
    @SerializedName("recipient_pay_courier_fee")
    private String recipientPayCourierFee;

    @Column(name = "request_id")
    @SerializedName("request_id")
    private Integer requestId;

    private String state;

    private String type;

    @Column(name = "updated_at")
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

    public Integer getAssignee() {
        return this.assignee;
    }

    public Double getCod() {
        return this.cod;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getDeliverBefore() {
        return this.deliverBefore;
    }

    public String getDeliverContactName() {
        return this.deliverContactName;
    }

    public String getDeliverContactPhone() {
        return this.deliverContactPhone;
    }

    public String getDeliverFullAddress() {
        return this.deliverFullAddress;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMerchantId() {
        return this.merchantId;
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

    public Integer getObjectId() {
        return this.objectId;
    }

    public String getObjectType() {
        return this.objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Timestamp getPickupBefore() {
        return this.pickupBefore;
    }

    public String getPickupContactName() {
        return this.pickupContactName;
    }

    public String getPickupContactPhone() {
        return this.pickupContactPhone;
    }

    public String getPickupFullAddress() {
        return this.pickupFullAddress;
    }

    public String getRecipientPayCourierFee() {
        return this.recipientPayCourierFee;
    }

    public Integer getRequestId() {
        return this.requestId;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
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