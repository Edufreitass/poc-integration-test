package codeplays.trainee.pocintegrationtest.match.client;

import codeplays.trainee.pocintegrationtest.match.model.Match;
import codeplays.trainee.pocintegrationtest.match.model.Ticket;
import codeplays.trainee.pocintegrationtest.player.model.Player;
import codeplays.trainee.pocintegrationtest.team.model.Team;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.mockserver.model.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MatchClientTest {

    @Autowired
    private MatchClient matchClient;

    private ClientAndServer matchService;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public void start() {
        matchService = ClientAndServer.startClientAndServer(4000);
    }

    @BeforeEach
    public void reset() {
        matchService.reset();
    }

    @AfterAll
    public void stop() {
        matchService.stop();
    }

    @Test
    public void shouldReturnAllMatchesOfATeam() throws JsonProcessingException {
        // Arrange
        String json = mapper.writeValueAsString(Collections.singletonList(new Match(1L, 1L, 2L, 90)));

        matchService
                .when(HttpRequest
                        .request()
                        .withMethod("GET")
                        .withQueryStringParameter("teamId", "1"))

                .respond(HttpResponse
                        .response()
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withStatusCode(200)
                        .withBody(json));

        // Act
        List<Match> matches = matchClient.findMatchesOfATeam(1L);

        // Assert
        Assertions.assertEquals(1, matches.size());
    }

    @Test
    public void shouldSubscriptionToAChampionship() throws JsonProcessingException {
        // Arrange
        Team team = new Team.Builder()
                .withName("Santos")
                .withPlayer(new Player("Ronaldo", Player.Position.ATTACKER)).build();

        String jsonRequest = mapper.writeValueAsString(team);

        String jsonResponse = mapper.writeValueAsString(new Ticket(1L));

        matchService
                .when(HttpRequest
                        .request()
                        .withMethod("POST")
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(jsonRequest))

                .respond(HttpResponse
                        .response()
                        .withContentType(MediaType.APPLICATION_JSON)
                        .withBody(jsonResponse));

        // Act
        Ticket ticket = matchClient.subscribeToChampionship(team);

        // Assert
        Assertions.assertEquals(1L, ticket.getId());
    }

}