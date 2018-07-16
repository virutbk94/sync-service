/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippo.global.entities.v1;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.DbJson;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author hothucdong
 */
@Entity
@Table(name = "\"TransportTask\"", catalog = "shippo_vn_1905", schema = "public")
@XmlRootElement
public class TransportTask extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "\"id\"")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "\"isDeleted\"")
    private short isDeleted;
    @Basic(optional = false)
    @Column(name = "\"createdTime\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdTime;
    @Basic(optional = false)
    @Column(name = "\"createdBy\"")
    private int createdBy;
    @Column(name = "\"modifiedTime\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp modifiedTime;
    @Column(name = "\"modifiedBy\"")
    private Integer modifiedBy;
    @Column(name = "\"customerId\"")
    private Integer customerId;
    @Column(name = "\"pickupDetail\"")
    private String pickupDetail;
    @Column(name = "\"pickupStreetId\"")
    private Integer pickupStreetId;
    @Column(name = "\"pickupPlaceId\"")
    private Integer pickupPlaceId;
    @Column(name = "\"pickupWardId\"")
    private Integer pickupWardId;
    @Column(name = "\"pickupDistrictId\"")
    private Integer pickupDistrictId;
    @Column(name = "\"pickupProvinceId\"")
    private Integer pickupProvinceId;
    @Column(name = "\"pickupContactName\"")
    private String pickupContactName;
    @Column(name = "\"pickupContactPhone\"")
    private String pickupContactPhone;
    @Column(name = "\"requestId\"")
    private Integer requestId;
    @Column(name = "\"deliverDetail\"")
    private String deliverDetail;
    @Column(name = "\"deliverStreetId\"")
    private Integer deliverStreetId;
    @Column(name = "\"deliverPlaceId\"")
    private Integer deliverPlaceId;
    @Column(name = "\"deliverWardId\"")
    private Integer deliverWardId;
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

    public short getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(short isDeleted) {
        this.isDeleted = isDeleted;
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

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
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

    public Integer getPickupStreetId() {
        return pickupStreetId;
    }

    public void setPickupStreetId(Integer pickupStreetId) {
        this.pickupStreetId = pickupStreetId;
    }

    public Integer getPickupPlaceId() {
        return pickupPlaceId;
    }

    public void setPickupPlaceId(Integer pickupPlaceId) {
        this.pickupPlaceId = pickupPlaceId;
    }

    public Integer getPickupWardId() {
        return pickupWardId;
    }

    public void setPickupWardId(Integer pickupWardId) {
        this.pickupWardId = pickupWardId;
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

    public Integer getDeliverStreetId() {
        return deliverStreetId;
    }

    public void setDeliverStreetId(Integer deliverStreetId) {
        this.deliverStreetId = deliverStreetId;
    }

    public Integer getDeliverPlaceId() {
        return deliverPlaceId;
    }

    public void setDeliverPlaceId(Integer deliverPlaceId) {
        this.deliverPlaceId = deliverPlaceId;
    }

    public Integer getDeliverWardId() {
        return deliverWardId;
    }

    public void setDeliverWardId(Integer deliverWardId) {
        this.deliverWardId = deliverWardId;
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransportTask)) {
            return false;
        }
        TransportTask other = (TransportTask) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shippo.sync.entities.v0.TransportTask[ id=" + id + " ]";
    }

    public static class TimeLine {
        private String scope;
        private String label;
        private String color;
        private String time;

        public String getScope() {
            return scope;
        }

        public void setScope(String scope) {
            this.scope = scope;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof TimeLine)) return false;
            TimeLine timeLine = (TimeLine) obj;
            return timeLine.getTime().equals(time)
                    && timeLine.getLabel().equals(label);
        }
    }

    public enum TransportTaskType {
        FINAL("FINAL"), MEDIATE("MEDIATE");

        String code;

        TransportTaskType(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }
    }

    public enum TransportTaskFeeType {
        SENDER_PAY_FOR_DELIVERY("SENDER_PAY_FOR_DELIVERY"),
        RECEIVER_PAY_FOR_DELIVERY("RECEIVER_PAY_FOR_DELIVERY");

        String code;

        TransportTaskFeeType(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }
    }
}
