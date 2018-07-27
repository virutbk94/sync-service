package shippo.rider_service.entities.v0;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;


/**
 * The persistent class for the task_histories database table.
 */
@Entity
@Table(name = "task_histories")
@NamedQuery(name = "TaskHistory.findAll", query = "SELECT t FROM TaskHistory t")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskHistory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String color;

    @Column(name = "created_at")
    @SerializedName("created_at")
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @Column(name = "label_description")
    @SerializedName("label_description")
    @JsonProperty("label_description")
    private String labelDescription;

    private String scope;

    @Column(name = "task_id")
    @SerializedName("task_id")
    @JsonProperty("task_id")
    private Integer taskId;

    private String type;

    @Column(name = "updated_at")
    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Column(name = "version")
    private Integer version;

    public TaskHistory() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLabelDescription() {
        return this.labelDescription;
    }

    public void setLabelDescription(String labelDescription) {
        this.labelDescription = labelDescription;
    }

    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskId() {
        return taskId;
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
    public String toString() {
        return "TaskHistory{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", createdAt=" + createdAt +
                ", labelDescription='" + labelDescription + '\'' +
                ", scope='" + scope + '\'' +
                ", taskId=" + taskId +
                ", type='" + type + '\'' +
                ", updatedAt=" + updatedAt +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskHistory)) return false;
        TaskHistory that = (TaskHistory) o;
        return Objects.equals(getColor(), that.getColor()) &&
                Objects.equals(getCreatedAt(), that.getCreatedAt()) &&
                Objects.equals(getLabelDescription(), that.getLabelDescription()) &&
                Objects.equals(getScope(), that.getScope()) &&
                Objects.equals(taskId, that.taskId) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getUpdatedAt(), that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return id;
    }
}