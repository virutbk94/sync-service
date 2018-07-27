/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippo.rider_service.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author hothucdong
 */
@Entity
@Table(name = "\"Users\"")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "realm")
    private String realm;
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "\"firstName\"")
    private String firstName;
    @Column(name = "\"lastName\"")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @Column(name = "tagline")
    private String tagline;
    @Basic(optional = false)
    @Column(name = "avatar")
    private String avatar;
    @Basic(optional = false)
    @Column(name = "\"registeredDate\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp registeredDate;
    @Column(name = "mobile")
    private String mobile;
    @Column(name = "\"fullName\"")
    private String fullName;
    @Column(name = "version")
    private Integer version;
    @Column(name = "\"modifiedTime\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp modifiedTime;



    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Users() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Timestamp getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Timestamp registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return Objects.equals(getId(), users.getId()) &&
                Objects.equals(getRealm(), users.getRealm()) &&
                Objects.equals(getUsername(), users.getUsername()) &&
                Objects.equals(getEmail(), users.getEmail()) &&
                Objects.equals(getFirstName(), users.getFirstName()) &&
                Objects.equals(getLastName(), users.getLastName()) &&
                Objects.equals(getState(), users.getState()) &&
                Objects.equals(getTagline(), users.getTagline()) &&
                Objects.equals(getAvatar(), users.getAvatar()) &&
                Objects.equals(getRegisteredDate(), users.getRegisteredDate()) &&
                Objects.equals(getMobile(), users.getMobile()) &&
                Objects.equals(getVersion(), users.getVersion()) &&
                Objects.equals(getModifiedTime(), users.getModifiedTime());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getRealm(), getUsername(), getEmail(), getFirstName(), getLastName(), getState(), getTagline(), getAvatar(), getRegisteredDate(), getMobile(), getVersion(), getModifiedTime());
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                '}';
    }

    public class Realm {
        public static final String STAFF = "staff";
    }
}
