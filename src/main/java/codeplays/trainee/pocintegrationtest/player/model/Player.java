package codeplays.trainee.pocintegrationtest.player.model;

import javax.persistence.*;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Position position;

    public Player() {
    }

    public Player(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public enum Position {
        ATTACKER,
        DEFENDER;
    }

}
