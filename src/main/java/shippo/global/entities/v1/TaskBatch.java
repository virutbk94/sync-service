/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippo.global.entities.v1;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.DbJsonB;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author hothucdong
 */
@Entity
@Table(name = "\"TaskBatch\"", catalog = "shippo_vn_1905", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TaskBatch.findAll", query = "SELECT t FROM TaskBatch t")
    , @NamedQuery(name = "TaskBatch.findById", query = "SELECT t FROM TaskBatch t WHERE t.id = :id")
    , @NamedQuery(name = "TaskBatch.findByCreatedBy", query = "SELECT t FROM TaskBatch t WHERE t.createdBy = :createdBy")
    , @NamedQuery(name = "TaskBatch.findByCreatedTime", query = "SELECT t FROM TaskBatch t WHERE t.createdTime = :createdTime")
    , @NamedQuery(name = "TaskBatch.findByTaskCount", query = "SELECT t FROM TaskBatch t WHERE t.taskCount = :taskCount")
    , @NamedQuery(name = "TaskBatch.findBySuccessCount", query = "SELECT t FROM TaskBatch t WHERE t.successCount = :successCount")
    , @NamedQuery(name = "TaskBatch.findByFailCount", query = "SELECT t FROM TaskBatch t WHERE t.failCount = :failCount")
    , @NamedQuery(name = "TaskBatch.findByHubId", query = "SELECT t FROM TaskBatch t WHERE t.hubId = :hubId")
    , @NamedQuery(name = "TaskBatch.findByShipperId", query = "SELECT t FROM TaskBatch t WHERE t.shipperId = :shipperId")
    , @NamedQuery(name = "TaskBatch.findByTaskType", query = "SELECT t FROM TaskBatch t WHERE t.taskType = :taskType")
    , @NamedQuery(name = "TaskBatch.findByVersion", query = "SELECT t FROM TaskBatch t WHERE t.version = :version")})
public class TaskBatch extends Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "\"id\"")
    private Integer id;
    @Column(name = "\"createdBy\"")
    private Integer createdBy;
    @Column(name = "\"createdTime\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp createdTime;
    @Column(name = "\"taskCount\"")
    private Integer taskCount;
    @Column(name = "\"successCount\"")
    private Integer successCount;
    @Column(name = "\"failCount\"")
    private Integer failCount;
    @DbJsonB
    @Column(name = "\"batchData\"")
    private List<Object> batchData;
    @Column(name = "\"hubId\"")
    private Integer hubId;
    @Column(name = "\"shipperId\"")
    private Integer shipperId;
    @Column(name = "\"taskType\"")
    private String taskType;
    @Column(name = "\"version\"")
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

    public TaskBatch() {
    }

    public TaskBatch(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public List<Object> getBatchData() {
        return batchData;
    }

    public void setBatchData(List<Object> batchData) {
        this.batchData = batchData;
    }

    public Integer getHubId() {
        return hubId;
    }

    public void setHubId(Integer hubId) {
        this.hubId = hubId;
    }

    public Integer getShipperId() {
        return shipperId;
    }

    public void setShipperId(Integer shipperId) {
        this.shipperId = shipperId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
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
        if (!(object instanceof TaskBatch)) {
            return false;
        }
        TaskBatch other = (TaskBatch) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shippo.sync.entities.v0.TaskBatch[ id=" + id + " ]";
    }
    
}
