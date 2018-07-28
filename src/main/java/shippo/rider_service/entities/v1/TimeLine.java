package shippo.rider_service.entities.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeLine {
    private String scope;
    private String label;
    private String time;
    private String color;

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeLine)) return false;
        TimeLine timeLine = (TimeLine) o;
        return Objects.equals(getScope(), timeLine.getScope()) &&
                Objects.equals(getLabel(), timeLine.getLabel()) &&
                Objects.equals(getTime(), timeLine.getTime()) &&
                Objects.equals(getColor(), timeLine.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScope(), getLabel(), getTime(), getColor());
    }

    @Override
    public String toString() {
        return "TimeLine{" +
                "scope='" + scope + '\'' +
                ", label='" + label + '\'' +
                ", time='" + time + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
