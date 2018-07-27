package shippo.tookan_service.entities.tookan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamTookan {
    private Integer team_id;
    private String team_name;

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamTookan)) return false;
        TeamTookan team = (TeamTookan) o;
        return Objects.equals(getTeam_name(), team.getTeam_name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTeam_name());
    }
}
