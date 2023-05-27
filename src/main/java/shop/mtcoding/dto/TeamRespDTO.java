package shop.mtcoding.dto;

import shop.mtcoding.model.Stadium;
import shop.mtcoding.model.Team;

import java.sql.Timestamp;

public class TeamRespDTO {
    private int id;
    private String stadiumName;
    private String teamName;
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public String getTeamName() {
        return teamName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public TeamRespDTO(Team team, Stadium stadium) {
        this.id = team.getId();
        this.stadiumName = stadium.getName();
        this.teamName = team.getName();
        this.createdAt = team.getCreatedAt();
    }
}
