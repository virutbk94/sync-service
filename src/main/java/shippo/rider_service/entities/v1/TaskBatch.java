/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shippo.rider_service.entities.v1;

import com.avaje.ebean.annotation.DbJsonB;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author hothucdong
 */
@Entity
@Table(name = "\"TaskBatch\"")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskBatch implements Serializable {

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

    public List<Object> getBatchData() {
        return batchData;
    }

    public void setBatchData(List<Object> batchData) {
        this.batchData = batchData;
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

    public Integer getHubId() {
        return hubId;
    }

    public void setHubId(Integer hubId) {
        this.hubId = hubId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskBatch)) return false;
        TaskBatch taskBatch = (TaskBatch) o;
        return Objects.equals(getId(), taskBatch.getId()) &&
                Objects.equals(getCreatedBy(), taskBatch.getCreatedBy()) &&
                Objects.equals(getCreatedTime(), taskBatch.getCreatedTime()) &&
                Objects.equals(getTaskCount(), taskBatch.getTaskCount()) &&
                Objects.equals(getBatchData(), taskBatch.getBatchData()) &&
                Objects.equals(getHubId(), taskBatch.getHubId()) &&
                Objects.equals(getShipperId(), taskBatch.getShipperId()) &&
                Objects.equals(getTaskType(), taskBatch.getTaskType()) &&
                Objects.equals(getVersion(), taskBatch.getVersion()) &&
                Objects.equals(getModifiedTime(), taskBatch.getModifiedTime());
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "TaskBatch{" +
                "id=" + id +
                ", createdBy=" + createdBy +
                ", createdTime=" + createdTime +
                ", taskCount=" + taskCount +
                ", batchData=" + batchData +
                ", hubId=" + hubId +
                ", shipperId=" + shipperId +
                ", taskType='" + taskType + '\'' +
                ", version=" + version +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
