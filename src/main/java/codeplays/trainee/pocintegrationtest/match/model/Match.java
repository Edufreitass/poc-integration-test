package codeplays.trainee.pocintegrationtest.match.model;

import java.util.Objects;

public class Match {

    private Long id;
    private Long teamHostId;
    private Long teamGuestId;
    private Integer durationInMinutes;

    public Match() {
    }

    public Match(Long id, Long teamHostId, Long teamGuestId, Integer durationInMinutes) {
        this.id = id;
        this.teamHostId = teamHostId;
        this.teamGuestId = teamGuestId;
        this.durationInMinutes = durationInMinutes;
    }

    public Long getId() {
        return id;
    }

    public Long getTeamHostId() {
        return teamHostId;
    }

    public Long getTeamGuestId() {
        return teamGuestId;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
