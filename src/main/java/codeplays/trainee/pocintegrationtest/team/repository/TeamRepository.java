package codeplays.trainee.pocintegrationtest.team.repository;

import codeplays.trainee.pocintegrationtest.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
