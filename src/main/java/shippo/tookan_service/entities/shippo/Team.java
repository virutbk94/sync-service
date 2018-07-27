package shippo.tookan_service.entities.shippo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;


/**
 * The persistent class for the teams database table.
 * 
 */
@Entity
@Table(name="teams")
@NamedQuery(name="Team.findAll", query="SELECT t FROM Team t")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name="created_at")
	@SerializedName("create_at")
	private Timestamp createdAt;

	private String description;

	private String name;

	@Column(name="parent_path")
	@SerializedName("parent_path")
	@JsonProperty("parent_path")
	private String parentPath;

	@Column(name="updated_at")
	@SerializedName("update_at")
	@JsonProperty("update_at")
	private Timestamp updatedAt;

	@Column(name="tookan_id")
	@SerializedName("tookan_id")
	@JsonProperty("tookan_id")
	private Integer tookanId;

	public Team() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getName() {
		return this.name;
	}

	public Integer getTookanId() {
		return tookanId;
	}

	public void setTookanId(Integer tookanId) {
		this.tookanId = tookanId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentPath() {
		return this.parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

	public Timestamp getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof Team)) return false;
		Team other = (Team) obj;
		return (other.tookanId.equals(tookanId)
				|| other.name.equals(name));
	}

	@Override
	public String toString() {
		return id + "_" + tookanId + "_" + name;
	}
}