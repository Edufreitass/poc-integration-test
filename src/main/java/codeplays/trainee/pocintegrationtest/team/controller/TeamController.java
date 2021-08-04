package codeplays.trainee.pocintegrationtest.team.controller;

import codeplays.trainee.pocintegrationtest.team.model.Team;
import codeplays.trainee.pocintegrationtest.team.service.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class TeamController {

    private TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @PostMapping("teams")
    public ResponseEntity<Void> create(@RequestBody Team team) {

        Team createdTeam = teamService.create(team);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTeam.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("teams/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Team findById(@PathVariable Long id) throws Exception {
        return teamService.findById(id);
    }
}
