package shippo.global.entities.v0.rider;

import com.avaje.ebean.Model;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the tookan_agents database table.
 * 
 */
@Entity
@Table(name="tookan_agents")
@NamedQuery(name="TookanAgent.findAll", query="SELECT t FROM TookanAgent t")
public class TookanAgent extends Model implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String agent;

	@Column(name="agent_id")
	@SerializedName("agent_id")
	private Integer agentId;

	@Column(name="rider_id")
	@SerializedName("rider_id")
	private Integer riderId;

	@Column(name="updated_at")
	@SerializedName("updated_at")
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

}