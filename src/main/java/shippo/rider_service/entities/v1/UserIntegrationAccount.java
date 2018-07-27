/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippo.rider_service.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "\"UserIntegrationAccount\"")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserIntegrationAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "\"userId\"")
    @SerializedName("userId")
    private Integer userId;
    @Column(name = "\"integrationId\"")
    @SerializedName("integrationId")
    private Integer integrationId;
    @Column(name = "account")
    private String account;
    @Column(name = "\"accountId\"")
    @SerializedName("accountId")
    private String accountId;
    @Column(name = "version")
    private Integer version;
    @Column(name = "\"modifiedTime\"")
    @SerializedName("modifiedTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp modifiedTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIntegrationId() {
        return integrationId;
    }

    public void setIntegrationId(Integer integrationId) {
        this.integrationId = integrationId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserIntegrationAccount)) return false;
        UserIntegrationAccount that = (UserIntegrationAccount) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getIntegrationId(), that.getIntegrationId()) &&
                Objects.equals(getAccount(), that.getAccount()) &&
                Objects.equals(getAccountId(), that.getAccountId()) &&
                Objects.equals(getVersion(), that.getVersion()) &&
                Objects.equals(getModifiedTime(), that.getModifiedTime());
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "shippo.sync.entities.v0.UserIntegrationAccount[ id=" + id + " ]";
    }
    
}
