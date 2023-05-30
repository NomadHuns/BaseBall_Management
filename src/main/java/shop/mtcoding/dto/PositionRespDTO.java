package shop.mtcoding.dto;

import lombok.NoArgsConstructor;
import shop.mtcoding.model.Player;
import shop.mtcoding.model.Team;

import java.util.List;

@NoArgsConstructor
public class PositionRespDTO {

    private List<Team> teamList;
    private List<Player> firstBaseManList;
    private List<Player> secondBaseManList;
    private List<Player> thirdBaseManList;
    private List<Player> catcherList;
    private List<Player> pitcherList;
    private List<Player> shortStopList;
    private List<Player> Fielder;

    public List<Team> getTeamList() {
        return teamList;
    }

    public List<Player> getFirstBaseManList() {
        return firstBaseManList;
    }

    public List<Player> getSecondBaseManList() {
        return secondBaseManList;
    }

    public List<Player> getThirdBaseManList() {
        return thirdBaseManList;
    }

    public List<Player> getCatcherList() {
        return catcherList;
    }

    public List<Player> getPitcherList() {
        return pitcherList;
    }

    public List<Player> getShortStopList() {
        return shortStopList;
    }

    public List<Player> getFielder() {
        return Fielder;
    }

    public PositionRespDTO(List<Team> teamList, List<Player> firstBaseManList, List<Player> secondBaseManList,
                           List<Player> thirdBaseManList, List<Player> catcherList, List<Player> pitcherList,
                           List<Player> shortStopList, List<Player> fielder) {
        this.teamList = teamList;
        this.firstBaseManList = firstBaseManList;
        this.secondBaseManList = secondBaseManList;
        this.thirdBaseManList = thirdBaseManList;
        this.catcherList = catcherList;
        this.pitcherList = pitcherList;
        this.shortStopList = shortStopList;
        Fielder = fielder;
    }
}
