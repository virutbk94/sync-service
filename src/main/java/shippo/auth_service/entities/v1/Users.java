/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippo.auth_service.entities.v1;

import com.avaje.ebean.Model;
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
@Table(name = "\"UsersAuth\"")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Users extends Model implements Serializable {

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
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "\"isEmailVerified\"")
    private Short isEmailVerified;
    @Basic(optional = false)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @Column(name = "\"registeredDate\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp registeredDate;
    @Column(name = "\"needChangePass\"")
    private Short needChangePass;
    @Column(name = "\"lastChangedPass\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp lastChangedPass;
    @Column(name = "level")
    private String level;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public short getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(short isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Timestamp registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Short getNeedChangePass() {
        return needChangePass;
    }

    public void setNeedChangePass(Short needChangePass) {
        this.needChangePass = needChangePass;
    }

    public Timestamp getLastChangedPass() {
        return lastChangedPass;
    }

    public void setLastChangedPass(Timestamp lastChangedPass) {
        this.lastChangedPass = lastChangedPass;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
        return Objects.equals(getId(), users.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                '}';
    }
}
