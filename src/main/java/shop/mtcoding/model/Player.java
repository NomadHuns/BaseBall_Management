package shop.mtcoding.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter @NoArgsConstructor
public class Player {
    private Integer id;
    private Integer teamId;
    private String name;
    private String position;
    private Timestamp createdAt;

    public Integer getId() {
        return id;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Builder
    public Player(Integer id, Integer teamId, String name, String position, Timestamp createdAt) {
        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.position = position;
        this.createdAt = createdAt;
    }
}
