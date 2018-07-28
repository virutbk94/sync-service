/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippo.auth_service.entities.v0;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author hothucdong
 */
@Entity
@Table(name = "users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersAuth extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "email")
    private String email;
    @Column(name = "section")
    private String section;
    @Column(name = "state")
    private String state;
    @Column(name = "is_email_verified")
    @SerializedName("is_email_verified")
    private Boolean isEmailVerified;
    @Column(name = "is_required_change_pass")
    @SerializedName("is_required_change_pass")
    private Boolean isRequiredChangePass;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;
    @Column(name = "change_pass_at")
    @SerializedName("change_pass_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp changePassAt;
    @Basic(optional = false)
    @Column(name = "created_at")
    @SerializedName("created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @SerializedName("updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp updatedAt;
    @Column(name = "version")
    private Integer version;
    @Column(name = "level")
    private String level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public Boolean getRequiredChangePass() {
        return isRequiredChangePass;
    }

    public void setRequiredChangePass(Boolean requiredChangePass) {
        isRequiredChangePass = requiredChangePass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getChangePassAt() {
        return changePassAt;
    }

    public void setChangePassAt(Timestamp changePassAt) {
        this.changePassAt = changePassAt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersAuth)) return false;
        UsersAuth usersAuth = (UsersAuth) o;
        return Objects.equals(getId(), usersAuth.getId()) &&
                Objects.equals(getUsername(), usersAuth.getUsername()) &&
                Objects.equals(getEmail(), usersAuth.getEmail()) &&
                Objects.equals(getSection(), usersAuth.getSection()) &&
                Objects.equals(getState(), usersAuth.getState()) &&
                Objects.equals(isEmailVerified, usersAuth.isEmailVerified) &&
                Objects.equals(isRequiredChangePass, usersAuth.isRequiredChangePass) &&
                Objects.equals(getPassword(), usersAuth.getPassword()) &&
                Objects.equals(getChangePassAt(), usersAuth.getChangePassAt()) &&
                Objects.equals(getCreatedAt(), usersAuth.getCreatedAt()) &&
                Objects.equals(getUpdatedAt(), usersAuth.getUpdatedAt()) &&
                Objects.equals(getVersion(), usersAuth.getVersion()) &&
                Objects.equals(getLevel(), usersAuth.getLevel());
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "UsersAuth{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", section='" + section + '\'' +
                ", state='" + state + '\'' +
                ", isEmailVerified=" + isEmailVerified +
                ", isRequiredChangePass=" + isRequiredChangePass +
                ", password='" + password + '\'' +
                ", changePassAt=" + changePassAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", version=" + version +
                ", level='" + level + '\'' +
                '}';
    }

}
