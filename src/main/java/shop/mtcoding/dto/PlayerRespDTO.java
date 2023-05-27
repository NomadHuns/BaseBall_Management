package shop.mtcoding.dto;

import shop.mtcoding.model.Player;
import shop.mtcoding.model.Stadium;
import shop.mtcoding.model.Team;

import java.sql.Timestamp;

public class PlayerRespDTO {
    private int id;
    private String teamName;
    private String playerName;
    private String position;
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPosition() {
        return position;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public PlayerRespDTO(Player player, Team team) {
        this.id = player.getId();
        this.teamName = team.getName();
        this.position = player.getPosition();
        this.playerName = player.getName();
        this.createdAt = player.getCreatedAt();
    }
}
