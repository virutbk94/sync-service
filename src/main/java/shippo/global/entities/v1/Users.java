/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippo.global.entities.v1;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.DbJson;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 *
 * @author hothucdong
 */
@Entity
@Table(name = "\"Users\"", catalog = "shippo_vn_1905", schema = "public")
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
    @Column(name = "\"firstName\"")
    private String firstName;
    @Column(name = "\"lastName\"")
    private String lastName;
    @Basic(optional = false)
    @Column(name = "gender")
    private Short gender;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Basic(optional = false)
    @Column(name = "\"isEmailVerified\"")
    private short isEmailVerified;
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
    private Date registeredDate;
    @Column(name = "\"needChangePass\"")
    private Short needChangePass;
    @Column(name = "\"lastLogin\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @Column(name = "\"lastChangedPass\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChangedPass;
    @Column(name = "\"verificationToken\"")
    private String verificationToken;
    @Column(name = "\"fullName\"")
    private String fullName;
    @Column(name = "\"oldPasswords\"")
    @DbJson
    private List<Object> oldPasswords;
    @Column(name = "code")
    private String code;
    @Column(name = "\"lastUsingService\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUsingService;
    @Column(name = "mobile")
    private String mobile;
    @Basic(optional = false)
    @Column(name = "\"projectedBalance\"")
    private Double projectedBalance;
    @Basic(optional = false)
    @Column(name = "\"realBalance\"")
    private Double realBalance;
    @Column(name = "\"bankAccountName\"")
    private String bankAccountName;
    @Column(name = "\"bankAccountNo\"")
    private String bankAccountNo;
    @Column(name = "\"bankName\"")
    private String bankName;
    @Column(name = "\"bankBranch\"")
    private String bankBranch;
    @Column(name = "\"firstUsingService\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date firstUsingService;
    @Column(name = "\"trialPolicyDate\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trialPolicyDate;
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

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Short getNeedChangePass() {
        return needChangePass;
    }

    public void setNeedChangePass(Short needChangePass) {
        this.needChangePass = needChangePass;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getLastChangedPass() {
        return lastChangedPass;
    }

    public void setLastChangedPass(Date lastChangedPass) {
        this.lastChangedPass = lastChangedPass;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<Object> getOldPasswords() {
        return oldPasswords;
    }

    public void setOldPasswords(List<Object> oldPasswords) {
        this.oldPasswords = oldPasswords;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getLastUsingService() {
        return lastUsingService;
    }

    public void setLastUsingService(Date lastUsingService) {
        this.lastUsingService = lastUsingService;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public double getProjectedBalance() {
        return projectedBalance;
    }

    public void setProjectedBalance(double projectedBalance) {
        this.projectedBalance = projectedBalance;
    }

    public double getRealBalance() {
        return realBalance;
    }

    public void setRealBalance(double realBalance) {
        this.realBalance = realBalance;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public Date getFirstUsingService() {
        return firstUsingService;
    }

    public void setFirstUsingService(Date firstUsingService) {
        this.firstUsingService = firstUsingService;
    }

    public Date getTrialPolicyDate() {
        return trialPolicyDate;
    }

    public void setTrialPolicyDate(Date trialPolicyDate) {
        this.trialPolicyDate = trialPolicyDate;
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
        return "shippo.sync.entities.v0.Users[ id=" + id + " ]";
    }

    public enum Realm {
        CUSTOMER("customer"), STAFF("staff");

        String code;

        Realm(String code) {
            this.code = code;
        }

        public String getCode() {
            return this.code;
        }
    }
}
