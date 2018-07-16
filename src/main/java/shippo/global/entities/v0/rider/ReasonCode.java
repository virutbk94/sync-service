package shippo.global.entities.v0.rider;

public class ReasonCode {
    private int id;
    private String value;
    private boolean check;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ReasonCode)) return false;
        ReasonCode reasonCode = (ReasonCode) obj;
        return reasonCode.id == id
                && reasonCode.value.equals(value)
                && reasonCode.check == check;
    }
}
