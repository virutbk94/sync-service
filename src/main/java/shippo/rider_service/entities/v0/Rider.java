package shippo.rider_service.entities.v0;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "riders")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rider extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    private String avatar;

    public Integer getTeamId() {
        return team_id;
    }

    public void setTeamId(Integer team_id) {
        this.team_id = team_id;
    }

    @Column(name = "team_id")
    private Integer team_id;

    @Column(name = "created_at")
    @SerializedName("created_at")
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @Column(name = "username")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    @SerializedName("first_name")
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "last_name")
    @SerializedName("last_name")
    @JsonProperty("last_name")
    private String lastName;

    private String mobile;

    private String state;

    @Column(name = "tag_line")
    @SerializedName("tag_line")
    @JsonProperty("tag_line")
    private String tagLine;

    @Column(name = "updated_at")
    @SerializedName("updated_at")
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    @Column(name = "user_id")
    @SerializedName("user_id")
    @JsonProperty("user_id")
    private Integer userId;

    private Integer version;

    public Rider() {
    }

    @Override
    public String toString() {
        return "Rider{" +
                "id=" + id +
                ", avatar='" + avatar + '\'' +
                ", team_id=" + team_id +
                ", createdAt=" + createdAt +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", state='" + state + '\'' +
                ", tagLine='" + tagLine + '\'' +
                ", updatedAt=" + updatedAt +
                ", userId=" + userId +
                ", version=" + version +
                '}';
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTagLine() {
        return this.tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        if (!(o instanceof Rider)) return false;
        Rider rider = (Rider) o;
        return Objects.equals(getAvatar(), rider.getAvatar()) &&
                Objects.equals(getCreatedAt(), rider.getCreatedAt()) &&
                Objects.equals(getUsername(), rider.getUsername()) &&
                Objects.equals(getEmail(), rider.getEmail()) &&
                Objects.equals(getFirstName(), rider.getFirstName()) &&
                Objects.equals(getLastName(), rider.getLastName()) &&
                Objects.equals(getMobile(), rider.getMobile()) &&
                Objects.equals(getState(), rider.getState()) &&
                Objects.equals(getTagLine(), rider.getTagLine()) &&
                Objects.equals(getUpdatedAt(), rider.getUpdatedAt()) &&
                Objects.equals(getUserId(), rider.getUserId()) &&
                Objects.equals(getVersion(), rider.getVersion());
    }

    @Override
    public int hashCode() {
        return id;
    }
}