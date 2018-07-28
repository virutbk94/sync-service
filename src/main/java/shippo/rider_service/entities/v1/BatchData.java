package shippo.rider_service.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BatchData {
    private String objectType;
    private Integer objectId;
    private String objectCode;
    private Integer assignedTo;
    private String type;
    private String status;
    private Object errors;

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

    public String getObjectCode() {
        return objectCode;
    }

    public void setObjectCode(String objectCode) {
        this.objectCode = objectCode;
    }

    public Integer getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Integer assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BatchData)) return false;
        BatchData batchData = (BatchData) o;
        return Objects.equals(getObjectType(), batchData.getObjectType()) &&
                Objects.equals(getObjectId(), batchData.getObjectId()) &&
                Objects.equals(getObjectCode(), batchData.getObjectCode()) &&
                Objects.equals(getAssignedTo(), batchData.getAssignedTo()) &&
                Objects.equals(getType(), batchData.getType()) &&
                Objects.equals(getStatus(), batchData.getStatus()) &&
                Objects.equals(getErrors(), batchData.getErrors());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getObjectType(), getObjectId(), getObjectCode(), getAssignedTo(), getType(), getStatus(), getErrors());
    }

    @Override
    public String toString() {
        return "BatchData{" +
                "objectType='" + objectType + '\'' +
                ", objectId=" + objectId +
                ", objectCode='" + objectCode + '\'' +
                ", assignedTo=" + assignedTo +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", errors=" + errors +
                '}';
    }
}
