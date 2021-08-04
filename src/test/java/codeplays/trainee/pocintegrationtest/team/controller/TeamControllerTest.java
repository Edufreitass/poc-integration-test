package codeplays.trainee.pocintegrationtest.team.controller;

import codeplays.trainee.pocintegrationtest.player.model.Player;
import codeplays.trainee.pocintegrationtest.team.model.Team;
import codeplays.trainee.pocintegrationtest.team.service.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static codeplays.trainee.pocintegrationtest.player.model.Player.Position.ATTACKER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TeamControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TeamService teamService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldCreateTeam() throws Exception {
        // Arrange
        Team team = createTeam();

        String jsonTeam = mapper.writeValueAsString(team);
        Long id = 1L;

        when(teamService.create(any())).thenReturn(team.setId(id));

        // Act
        String location = mvc.perform(
                post("/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonTeam)
        )
                //Assert
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn().getResponse().getHeader("Location");

        assertTrue(location.contains("/teams/" + id));

        ArgumentCaptor<Team> captor = ArgumentCaptor.forClass(Team.class);
        verify(teamService).create(captor.capture());

        Team capturedTeam = captor.getValue();
        assertEquals(team.getName(), capturedTeam.getName());
        assertNull(capturedTeam.getId());
        assertEquals(team.getPlayers().size(), capturedTeam.getPlayers().size());
        assertEquals(team.getPlayers().get(0).getPosition(), capturedTeam.getPlayers().get(0).getPosition());
        assertEquals(team.getPlayers().get(0).getName(), capturedTeam.getPlayers().get(0).getName());
        assertEquals(team.getPlayers().get(0).getId(), capturedTeam.getPlayers().get(0).getId());
    }

    @Test
    public void shouldFindATeamById() throws Exception {
        // Arrange
        Team team = createTeam();
        Long id = 1L;
        team.setId(id);

        when(teamService.findById(eq(id))).thenReturn(team);

        // Act
        mvc.perform(
                get("/teams/" + team.getId())
                        .accept(MediaType.APPLICATION_JSON)
        )
                // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(team.getName())))
                .andExpect(jsonPath("$.players[0].name", Matchers.is(team.getPlayers().get(0).getName())))
                .andExpect(jsonPath("$.players[0].position", Matchers.is(team.getPlayers().get(0).getPosition().name())));
    }

    private Team createTeam() {
        return new Team.Builder()
                .withName("São Paulo")
                .withPlayer(new Player("Trainee", ATTACKER)).build();
    }

}