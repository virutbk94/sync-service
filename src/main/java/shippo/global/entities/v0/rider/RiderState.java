package shippo.global.entities.v0.rider;

public enum RiderState {
    ACTIVE("ACTIVE"), INACTIVE("INACTIVE"), PENDING("PENDING");

    String code;

    RiderState(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}