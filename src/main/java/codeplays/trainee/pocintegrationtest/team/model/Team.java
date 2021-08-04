package codeplays.trainee.pocintegrationtest.team.model;

import codeplays.trainee.pocintegrationtest.player.model.Player;

import javax.persistence.*;
import java.util.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "team_id")
    private List<Player> players = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Team setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getPlayerById(Long playerId) throws Exception {
        return players
                .stream()
                .filter((player -> player.getId().equals(playerId)))
                .findFirst()
                .orElseThrow(() -> new Exception("Player não encontrado"));
    }

    public static class Builder {
        Team team = new Team();

        public Builder withName(String name) {
            team.name = name;

            return this;
        }

        public Builder withPlayer(Player player) {
            team.players.add(player);

            return this;
        }

        public Team build() {
            return team;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
