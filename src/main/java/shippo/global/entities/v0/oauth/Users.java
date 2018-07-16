/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippo.global.entities.v0.oauth;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author hothucdong
 */
@Entity
@Table(name = "users")
public class Users implements Serializable {

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
    private Date changePassAt;
    @Column(name = "verified_email_at")
    @SerializedName("verified_email_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date verifiedEmailAt;
    @Basic(optional = false)
    @Column(name = "created_at")
    @SerializedName("created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @SerializedName("updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "version")
    private Integer version;
    @Column(name = "level")
    private String level;


    public Users() {
    }

    public Users(Integer id) {
        this.id = id;
    }

    public Users(Integer id, String password, Date createdAt, Date updatedAt) {
        this.id = id;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public Boolean getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public Boolean getIsRequiredChangePass() {
        return isRequiredChangePass;
    }

    public void setIsRequiredChangePass(Boolean isRequiredChangePass) {
        this.isRequiredChangePass = isRequiredChangePass;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getChangePassAt() {
        return changePassAt;
    }

    public void setChangePassAt(Date changePassAt) {
        this.changePassAt = changePassAt;
    }

    public Date getVerifiedEmailAt() {
        return verifiedEmailAt;
    }

    public void setVerifiedEmailAt(Date verifiedEmailAt) {
        this.verifiedEmailAt = verifiedEmailAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shippo.oauth.sync.entities.v1.Users[ id= " + id + " ]";
    }
    
}
