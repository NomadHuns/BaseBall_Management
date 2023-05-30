package shop.mtcoding.dto;

import shop.mtcoding.model.Player;
import shop.mtcoding.model.Team;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OutPlayerRespDTO {
    private int id;
    private String name;
    private String position;
    private String reason;
    private Timestamp createdAt;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getReason() {
        if (this.reason == null) {
            return "      ";
        }
        return reason;
    }

    public String getCreatedAt() {
        if (this.createdAt == null) {
            return "";
        }
        LocalDateTime nowTime = this.createdAt.toLocalDateTime();
        String nowStr = nowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return nowStr;
    }

    public OutPlayerRespDTO(int id, String name, String position, String reason, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.reason = reason;
        this.createdAt = createdAt;
    }
}
