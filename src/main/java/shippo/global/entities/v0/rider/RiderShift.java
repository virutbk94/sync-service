package shippo.global.entities.v0.rider;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.DbJsonB;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the rider_shifts database table.
 * 
 */
@Entity
@Table(name="rider_shifts")
@NamedQuery(name="RiderShift.findAll", query="SELECT r FROM RiderShift r")
public class RiderShift extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="assigned_at")
    @SerializedName("assigned_at")
	private Timestamp assignedAt;

	@DbJsonB
	private List<Object> barcodes;

	@Column(name="closed_at")
    @SerializedName("closed_at")
	private Timestamp closedAt;

	@Column(name="created_at")
    @SerializedName("created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp createdAt;

	@Column(name="created_by")
    @SerializedName("created_by")
	private Integer createdBy;

	@Column(name="is_closed")
    @SerializedName("is_closed")
	private Boolean isClosed;

	@Column(name="rider_id")
    @SerializedName("rider_id")
	private Integer riderId;

	@Column(name="shift_id")
    @SerializedName("shift_id")
	private Integer shiftId;

	private String state;

	@Column(name="task_count")
    @SerializedName("task_count")
	private Integer taskCount;

	@Column(name="task_type")
    @SerializedName("task_type")
	private String taskType;

	@Column(name="updated_at")
    @SerializedName("updated_at")
	private Timestamp updatedAt;

	@DbJsonB
	@Column(name="metadata")
	@SerializedName("metadata")
	private List<Metadata> metadata;

	public List<Metadata> getMetadata() {
		return metadata;
	}

	public void setMetadata(List<Metadata> metadata) {
		this.metadata = metadata;
	}

	private Integer version;

	public RiderShift() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getAssignedAt() {
		return this.assignedAt;
	}

	public void setAssignedAt(Timestamp assignedAt) {
		this.assignedAt = assignedAt;
	}

	public List<Object> getBarcodes() {
		return this.barcodes;
	}

	public void setBarcodes(List<Object> barcodes) {
		this.barcodes = barcodes;
	}

	public Timestamp getClosedAt() {
		return this.closedAt;
	}

	public void setClosedAt(Timestamp closedAt) {
		this.closedAt = closedAt;
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

	public Boolean getIsClosed() {
		return this.isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Integer getRiderId() {
		return this.riderId;
	}

	public void setRiderId(Integer riderId) {
		this.riderId = riderId;
	}

	public Integer getShiftId() {
		return this.shiftId;
	}

	public void setShiftId(Integer shiftId) {
		this.shiftId = shiftId;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getTaskCount() {
		return this.taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public String getTaskType() {
		return this.taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
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