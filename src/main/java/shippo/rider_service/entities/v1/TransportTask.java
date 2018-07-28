/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippo.rider_service.entities.v1;

import com.avaje.ebean.annotation.DbJson;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author hothucdong
 */
@Entity
@Table(name = "\"TransportTask\"")
@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransportTask implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "\"id\"")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "\"createdTime\"")
    @SerializedName("createdTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdTime;
    @Basic(optional = false)
    @Column(name = "\"createdBy\"")
    @SerializedName("createdBy")
    private Integer createdBy;
    @Column(name = "\"modifiedTime\"")
    @SerializedName("modifiedTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp modifiedTime;
    @Column(name = "\"customerId\"")
    @SerializedName("customerId")
    private Integer customerId;
    @Column(name = "\"pickupDetail\"")
    @SerializedName("pickupDetail")
    private String pickupDetail;
    @Column(name = "\"pickupDistrictId\"")
    @SerializedName("pickupDistrictId")
    private Integer pickupDistrictId;
    @Column(name = "\"pickupProvinceId\"")
    @SerializedName("pickupProvinceId")
    private Integer pickupProvinceId;
    @Column(name = "\"pickupContactName\"")
    @SerializedName("pickupContactName")
    private String pickupContactName;
    @Column(name = "\"pickupContactPhone\"")
    @SerializedName("pickupContactPhone")
    private String pickupContactPhone;
    @Column(name = "\"requestId\"")
    @SerializedName("requestId")
    private Integer requestId;
    @Column(name = "\"deliverDetail\"")
    @SerializedName("deliverDetail")
    private String deliverDetail;
    @Column(name = "\"deliverDistrictId\"")
    private Integer deliverDistrictId;
    @Column(name = "\"deliverProvinceId\"")
    private Integer deliverProvinceId;
    @Column(name = "\"deliverContactName\"")
    private String deliverContactName;
    @Column(name = "\"deliverContactPhone\"")
    private String deliverContactPhone;
    @Column(name = "\"description\"")
    private String description;
    @Column(name = "\"assignedTo\"")
    private Integer assignedTo;
    @Column(name = "\"status\"")
    private String status;
    @Column(name = "\"note\"")
    private String note;
    @Column(name = "\"type\"")
    private String type;
    @Column(name = "\"pickupAddressFull\"")
    private String pickupAddressFull;
    @Column(name = "\"deliverAddressFull\"")
    private String deliverAddressFull;
    @Column(name = "\"reason\"")
    private String reason;
    @Column(name = "\"reasonCode\"")
    private String reasonCode;
    @Column(name = "\"jobId\"")
    private String jobId;
    @Column(name = "\"objectType\"")
    private String objectType;
    @Column(name = "\"objectId\"")
    private Integer objectId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "\"realCod\"")
    private Double realCod;
    @Column(name = "\"startedTime\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startedTime;
    @Column(name = "\"failedTime\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp failedTime;
    @Column(name = "\"successTime\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp successTime;
    @Column(name = "\"cod\"")
    private Double cod;
    @Column(name = "\"objectCode\"")
    private String objectCode;
    @DbJson
    @Column(name = "\"timeline\"")
    private List<TimeLine> timeline;
    @Column(name = "\"pickupBefore\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp pickupBefore;
    @Column(name = "\"deliverBefore\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp deliverBefore;
    @Column(name = "\"taskDeadline\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp taskDeadline;
    @Column(name = "\"batchId\"")
    private Integer batchId;
    @Basic(optional = false)
    @Column(name = "\"assigneeType\"")
    private String assigneeType;
    @Basic(optional = false)
    @Column(name = "\"taskType\"")
    private String taskType;
    @Column(name = "\"internalReasonFail\"")
    private String internalReasonFail;
    @Basic(optional = false)
    @Column(name = "\"feeType\"")
    private String feeType;
    @Column(name = "\"version\"")
    private Integer version;

    public TransportTask() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getPickupDetail() {
        return pickupDetail;
    }

    public void setPickupDetail(String pickupDetail) {
        this.pickupDetail = pickupDetail;
    }

    public Integer getPickupDistrictId() {
        return pickupDistrictId;
    }

    public void setPickupDistrictId(Integer pickupDistrictId) {
        this.pickupDistrictId = pickupDistrictId;
    }

    public Integer getPickupProvinceId() {
        return pickupProvinceId;
    }

    public void setPickupProvinceId(Integer pickupProvinceId) {
        this.pickupProvinceId = pickupProvinceId;
    }

    public String getPickupContactName() {
        return pickupContactName;
    }

    public void setPickupContactName(String pickupContactName) {
        this.pickupContactName = pickupContactName;
    }

    public String getPickupContactPhone() {
        return pickupContactPhone;
    }

    public void setPickupContactPhone(String pickupContactPhone) {
        this.pickupContactPhone = pickupContactPhone;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getDeliverDetail() {
        return deliverDetail;
    }

    public void setDeliverDetail(String deliverDetail) {
        this.deliverDetail = deliverDetail;
    }

    public Integer getDeliverDistrictId() {
        return deliverDistrictId;
    }

    public void setDeliverDistrictId(Integer deliverDistrictId) {
        this.deliverDistrictId = deliverDistrictId;
    }

    public Integer getDeliverProvinceId() {
        return deliverProvinceId;
    }

    public void setDeliverProvinceId(Integer deliverProvinceId) {
        this.deliverProvinceId = deliverProvinceId;
    }

    public String getDeliverContactName() {
        return deliverContactName;
    }

    public void setDeliverContactName(String deliverContactName) {
        this.deliverContactName = deliverContactName;
    }

    public String getDeliverContactPhone() {
        return deliverContactPhone;
    }

    public void setDeliverContactPhone(String deliverContactPhone) {
        this.deliverContactPhone = deliverContactPhone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPickupAddressFull() {
        return pickupAddressFull;
    }

    public void setPickupAddressFull(String pickupAddressFull) {
        this.pickupAddressFull = pickupAddressFull;
    }

    public String getDeliverAddressFull() {
        return deliverAddressFull;
    }

    public void setDeliverAddressFull(String deliverAddressFull) {
        this.deliverAddressFull = deliverAddressFull;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    public Double getRealCod() {
        return realCod;
    }

    public void setRealCod(Double realCod) {
        this.realCod = realCod;
    }

    public Timestamp getStartedTime() {
        return startedTime;
    }

    public void setStartedTime(Timestamp startedTime) {
        this.startedTime = startedTime;
    }

    public Timestamp getFailedTime() {
        return failedTime;
    }

    public void setFailedTime(Timestamp failedTime) {
        this.failedTime = failedTime;
    }

    public Timestamp getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Timestamp successTime) {
        this.successTime = successTime;
    }

    public Double getCod() {
        return cod;
    }

    public void setCod(Double cod) {
        this.cod = cod;
    }

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public List<TimeLine> getTimeline() {
        return timeline;
    }

    public void setTimeline(List<TimeLine> timeline) {
        this.timeline = timeline;
    }

    public Timestamp getPickupBefore() {
        return pickupBefore;
    }

    public void setPickupBefore(Timestamp pickupBefore) {
        this.pickupBefore = pickupBefore;
    }

    public Timestamp getDeliverBefore() {
        return deliverBefore;
    }

    public void setDeliverBefore(Timestamp deliverBefore) {
        this.deliverBefore = deliverBefore;
    }

    public Timestamp getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Timestamp taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getAssigneeType() {
        return assigneeType;
    }

    public void setAssigneeType(String assigneeType) {
        this.assigneeType = assigneeType;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getInternalReasonFail() {
        return internalReasonFail;
    }

    public void setInternalReasonFail(String internalReasonFail) {
        this.internalReasonFail = internalReasonFail;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransportTask)) return false;
        TransportTask that = (TransportTask) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCreatedTime(), that.getCreatedTime()) &&
                Objects.equals(getCreatedBy(), that.getCreatedBy()) &&
                Objects.equals(getModifiedTime(), that.getModifiedTime()) &&
                Objects.equals(getCustomerId(), that.getCustomerId()) &&
                Objects.equals(getPickupDetail(), that.getPickupDetail()) &&
                Objects.equals(getPickupDistrictId(), that.getPickupDistrictId()) &&
                Objects.equals(getPickupProvinceId(), that.getPickupProvinceId()) &&
                Objects.equals(getPickupContactName(), that.getPickupContactName()) &&
                Objects.equals(getPickupContactPhone(), that.getPickupContactPhone()) &&
                Objects.equals(getRequestId(), that.getRequestId()) &&
                Objects.equals(getDeliverDetail(), that.getDeliverDetail()) &&
                Objects.equals(getDeliverDistrictId(), that.getDeliverDistrictId()) &&
                Objects.equals(getDeliverProvinceId(), that.getDeliverProvinceId()) &&
                Objects.equals(getDeliverContactName(), that.getDeliverContactName()) &&
                Objects.equals(getDeliverContactPhone(), that.getDeliverContactPhone()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getAssignedTo(), that.getAssignedTo()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getNote(), that.getNote()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getPickupAddressFull(), that.getPickupAddressFull()) &&
                Objects.equals(getDeliverAddressFull(), that.getDeliverAddressFull()) &&
                Objects.equals(getReason(), that.getReason()) &&
                Objects.equals(getReasonCode(), that.getReasonCode()) &&
                Objects.equals(getJobId(), that.getJobId()) &&
                Objects.equals(getObjectType(), that.getObjectType()) &&
                Objects.equals(getObjectId(), that.getObjectId()) &&
                Objects.equals(getRealCod(), that.getRealCod()) &&
                Objects.equals(getStartedTime(), that.getStartedTime()) &&
                Objects.equals(getFailedTime(), that.getFailedTime()) &&
                Objects.equals(getSuccessTime(), that.getSuccessTime()) &&
                Objects.equals(getCod(), that.getCod()) &&
                Objects.equals(getObjectCode(), that.getObjectCode()) &&
                Objects.equals(getTimeline(), that.getTimeline()) &&
                Objects.equals(getPickupBefore(), that.getPickupBefore()) &&
                Objects.equals(getDeliverBefore(), that.getDeliverBefore()) &&
                Objects.equals(getTaskDeadline(), that.getTaskDeadline()) &&
                Objects.equals(getBatchId(), that.getBatchId()) &&
                Objects.equals(getAssigneeType(), that.getAssigneeType()) &&
                Objects.equals(getTaskType(), that.getTaskType()) &&
                Objects.equals(getInternalReasonFail(), that.getInternalReasonFail()) &&
                Objects.equals(getFeeType(), that.getFeeType()) &&
                Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getCreatedTime(), getCreatedBy(), getModifiedTime(), getCustomerId(), getPickupDetail(), getPickupDistrictId(), getPickupProvinceId(), getPickupContactName(), getPickupContactPhone(), getRequestId(), getDeliverDetail(), getDeliverDistrictId(), getDeliverProvinceId(), getDeliverContactName(), getDeliverContactPhone(), getDescription(), getAssignedTo(), getStatus(), getNote(), getType(), getPickupAddressFull(), getDeliverAddressFull(), getReason(), getReasonCode(), getJobId(), getObjectType(), getObjectId(), getRealCod(), getStartedTime(), getFailedTime(), getSuccessTime(), getCod(), getObjectCode(), getTimeline(), getPickupBefore(), getDeliverBefore(), getTaskDeadline(), getBatchId(), getAssigneeType(), getTaskType(), getInternalReasonFail(), getFeeType(), getVersion());
    }

    @Override
    public String toString() {
        return "TransportTask{" +
                "id=" + id +
                ", createdTime=" + createdTime +
                ", createdBy=" + createdBy +
                ", modifiedTime=" + modifiedTime +
                ", customerId=" + customerId +
                ", pickupDetail='" + pickupDetail + '\'' +
                ", pickupDistrictId=" + pickupDistrictId +
                ", pickupProvinceId=" + pickupProvinceId +
                ", pickupContactName='" + pickupContactName + '\'' +
                ", pickupContactPhone='" + pickupContactPhone + '\'' +
                ", requestId=" + requestId +
                ", deliverDetail='" + deliverDetail + '\'' +
                ", deliverDistrictId=" + deliverDistrictId +
                ", deliverProvinceId=" + deliverProvinceId +
                ", deliverContactName='" + deliverContactName + '\'' +
                ", deliverContactPhone='" + deliverContactPhone + '\'' +
                ", description='" + description + '\'' +
                ", assignedTo=" + assignedTo +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                ", type='" + type + '\'' +
                ", pickupAddressFull='" + pickupAddressFull + '\'' +
                ", deliverAddressFull='" + deliverAddressFull + '\'' +
                ", reason='" + reason + '\'' +
                ", reasonCode='" + reasonCode + '\'' +
                ", jobId='" + jobId + '\'' +
                ", objectType='" + objectType + '\'' +
                ", objectId=" + objectId +
                ", realCod=" + realCod +
                ", startedTime=" + startedTime +
                ", failedTime=" + failedTime +
                ", successTime=" + successTime +
                ", cod=" + cod +
                ", objectCode='" + objectCode + '\'' +
                ", timeline=" + timeline +
                ", pickupBefore=" + pickupBefore +
                ", deliverBefore=" + deliverBefore +
                ", taskDeadline=" + taskDeadline +
                ", batchId=" + batchId +
                ", assigneeType='" + assigneeType + '\'' +
                ", taskType='" + taskType + '\'' +
                ", internalReasonFail='" + internalReasonFail + '\'' +
                ", feeType='" + feeType + '\'' +
                ", version=" + version +
                '}';
    }

    public class TransportTaskType {
        public static final String FINAL = "FINAL";
    }
}
