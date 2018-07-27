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
 * The persistent class for the tookan_agents database table.
 */
@Entity
@Table(name = "tookan_agents")
@NamedQuery(name = "TookanAgent.findAll", query = "SELECT t FROM TookanAgent t")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TookanAgent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String agent;

    @Column(name = "agent_id")
    @SerializedName("agent_id")
    @JsonProperty("agent_id")
    private Integer agentId;

    @Column(name = "rider_id")
    @SerializedName("rider_id")
    @JsonProperty("rider_id")
    private Integer riderId;

    @Column(name = "updated_at")
    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    private Integer version;

    public TookanAgent() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgent() {
        return this.agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public Integer getAgentId() {
        return this.agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getRiderId() {
        return this.riderId;
    }

    public void setRiderId(Integer riderId) {
        this.riderId = riderId;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TookanAgent)) return false;
        TookanAgent that = (TookanAgent) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getAgent(), that.getAgent()) &&
                Objects.equals(getAgentId(), that.getAgentId()) &&
                Objects.equals(getRiderId(), that.getRiderId()) &&
                Objects.equals(getUpdatedAt(), that.getUpdatedAt()) &&
                Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public String toString() {
        return "TookanAgent{" +
                "id=" + id +
                ", agent='" + agent + '\'' +
                ", agentId=" + agentId +
                ", riderId=" + riderId +
                ", updatedAt=" + updatedAt +
                ", version=" + version +
                '}';
    }

    @Override
    public int hashCode() {
        return id;
    }
}