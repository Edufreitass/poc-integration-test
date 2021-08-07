package codeplays.trainee.pocintegrationtest.team.repository;

import codeplays.trainee.pocintegrationtest.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT team FROM Team team " +
            "WHERE LOWER(team.name) LIKE LOWER(CONCAT('%', :teamName, '%'))")
    List<Team> findTeamsByName(String teamName);

}
