package shippo.global.entities.v0.rider;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.DbJsonB;
import com.google.gson.annotations.SerializedName;
import shippo.global.Utils;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the task_histories database table.
 */
@Entity
@Table(name = "task_histories")
@NamedQuery(name = "TaskHistory.findAll", query = "SELECT t FROM TaskHistory t")
public class TaskHistory extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private Integer assignee;

    private String color;

    @Column(name = "created_at")
    @SerializedName("created_at")
    private Timestamp createdAt;

    private String description;

    @DbJsonB
    @Column(name = "extra_fields")
    @SerializedName("extra_fields")
    private List<Object> extraFields;

    @Column(name = "label_description")
    @SerializedName("label_description")
    private String labelDescription;

    private String latitude;

    private String longitude;

    private String scope;

    @Column(name = "task_id")
    @SerializedName("task_id")
    private Integer taskId;

    private String type;

    @Column(name = "updated_at")
    @SerializedName("updated_at")
    private Timestamp updatedAt;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "version")
    private int version;

    public TaskHistory() {
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

    public void setAssignee(Integer assignee) {
        this.assignee = assignee;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Object> getExtraFields() {
        return this.extraFields;
    }

    public void setExtraFields(List<Object> extraFields) {
        this.extraFields = extraFields;
    }

    public String getLabelDescription() {
        return this.labelDescription;
    }

    public void setLabelDescription(String labelDescription) {
        this.labelDescription = labelDescription;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getTaskId() {
        return this.taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof TaskHistory)) return false;
        TaskHistory taskHistory = (TaskHistory) obj;
        return (Utils.eq(taskHistory.taskId, taskId)
                && Utils.eq(taskHistory.createdAt, createdAt)
                && Utils.eq(taskHistory.labelDescription, labelDescription));
    }
}