package shippo.global.entities.v0.rider;

public class Metadata {
    private String key;
    private String label;
    private String value;
    private String time;

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Metadata)) return false;
        Metadata metadata = (Metadata) obj;
        return metadata.getKey().equals(key) && metadata.getValue().equals(value);
    }
}
