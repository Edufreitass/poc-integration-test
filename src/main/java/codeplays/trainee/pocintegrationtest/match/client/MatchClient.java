package codeplays.trainee.pocintegrationtest.match.client;

import codeplays.trainee.pocintegrationtest.match.model.Match;
import codeplays.trainee.pocintegrationtest.match.model.Ticket;
import codeplays.trainee.pocintegrationtest.team.model.Team;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "MatchClient", url = "${match-service.url}")
@RequestMapping("matches")
public interface MatchClient {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Match> findMatchesOfATeam(@RequestParam("teamId") Long teamId);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Ticket subscribeToChampionship(@RequestBody Team team);

}
