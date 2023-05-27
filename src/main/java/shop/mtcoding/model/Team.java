package shop.mtcoding.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter @NoArgsConstructor
public class Team {
    private Integer id;
    private Integer stadiumId;
    private String name;
    private Timestamp createdAt;

    public Integer getId() {
        return id;
    }

    public Integer getStadiumId() {
        return stadiumId;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Builder
    public Team(Integer id, Integer stadiumId, String name, Timestamp createdAt) {
        this.id = id;
        this.stadiumId = stadiumId;
        this.name = name;
        this.createdAt = createdAt;
    }
}
