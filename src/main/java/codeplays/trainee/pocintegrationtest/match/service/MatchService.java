package codeplays.trainee.pocintegrationtest.match.service;

import codeplays.trainee.pocintegrationtest.match.client.MatchClient;
import codeplays.trainee.pocintegrationtest.match.model.Match;
import codeplays.trainee.pocintegrationtest.match.model.Ticket;
import codeplays.trainee.pocintegrationtest.team.model.Team;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private MatchClient matchClient;

    public MatchService(MatchClient matchClient) {
        this.matchClient = matchClient;
    }

    public List<Match> findMatchesOfATeam(Long teamId) {
        return matchClient.findMatchesOfATeam(teamId);
    }

    public Ticket subscribeToChampionship(Team team) {
        return matchClient.subscribeToChampionship(team);
    }
}
