package shippo.global.entities.v1;

import com.avaje.ebean.Model;
import shippo.global.Utils;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "\"MetaFields\"", catalog = "shippo_vn_1905", schema = "public")
@XmlRootElement
public class MetaFields extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int id;

    @Column(name = "\"objectType\"")
    private String objectType;

    @Column(name = "\"objectId\"")
    private int objectId;

    private String key;

    private String label;

    private String value;

    private int version;

    @Column(name = "\"modifiedTime\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp modifiedTime;

    public Timestamp getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Timestamp modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public int getObjectId() {
        return objectId;
    }

    public void setObjectId(int objectId) {
        this.objectId = objectId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MetaFields)) {
            return false;
        }
        MetaFields other = (MetaFields) obj;
        return Utils.eq(objectType, other.objectType)
                && Utils.eq(modifiedTime, other.modifiedTime);
    }
}
