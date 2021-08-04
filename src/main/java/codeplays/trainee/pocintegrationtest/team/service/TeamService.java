package codeplays.trainee.pocintegrationtest.team.service;

import codeplays.trainee.pocintegrationtest.team.model.Team;
import codeplays.trainee.pocintegrationtest.team.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team create(Team team) {
        return teamRepository.save(team);
    }


    public Team findById(Long id) throws Exception {
        return this.teamRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Team not found"));
    }
}
