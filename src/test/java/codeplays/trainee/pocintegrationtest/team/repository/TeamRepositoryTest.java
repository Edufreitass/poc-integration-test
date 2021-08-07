package codeplays.trainee.pocintegrationtest.team.repository;

import codeplays.trainee.pocintegrationtest.player.model.Player;
import codeplays.trainee.pocintegrationtest.team.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

@DataJpaTest
class TeamRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void shouldFindTeamsByNameContaining() {
        // Arrange
        Team team1 = entityManager
                .persist(new Team.Builder()
                        .withName("São Paulo")
                        .withPlayer(new Player("Ronaldo", Player.Position.ATTACKER)).build());

        Team team2 = entityManager
                .persist(new Team.Builder()
                        .withName("São Caetano")
                        .withPlayer(new Player("Paulo", Player.Position.DEFENDER)).build());

        // Act
        List<Team> teams = teamRepository.findTeamsByName("São");

        Team teamFound1 = teams.stream().filter((team -> team.equals(team1))).findFirst().orElse(new Team.Builder().build());
        Team teamFound2 = teams.stream().filter((team -> team.equals(team2))).findFirst().orElse(new Team.Builder().build());

        // Assert
        Assertions.assertEquals(2, teams.size());

        Assertions.assertEquals(team1.getId(), teamFound1.getId());
        Assertions.assertEquals(team1.getName(), teamFound1.getName());
        Assertions.assertEquals(team1.getPlayers().size(), teamFound1.getPlayers().size());
        Assertions.assertEquals(team1.getPlayers().get(0).getId(), teamFound1.getPlayers().get(0).getId());
        Assertions.assertEquals(team1.getPlayers().get(0).getPosition(), teamFound1.getPlayers().get(0).getPosition());
        Assertions.assertEquals(team1.getPlayers().get(0).getName(), teamFound1.getPlayers().get(0).getName());

        Assertions.assertEquals(team2.getName(), teamFound2.getName());
        Assertions.assertEquals(team2.getPlayers().size(), teamFound2.getPlayers().size());
        Assertions.assertEquals(team2.getPlayers().get(0).getId(), teamFound2.getPlayers().get(0).getId());
        Assertions.assertEquals(team2.getPlayers().get(0).getPosition(), teamFound2.getPlayers().get(0).getPosition());
        Assertions.assertEquals(team2.getPlayers().get(0).getName(), teamFound2.getPlayers().get(0).getName());
    }

}