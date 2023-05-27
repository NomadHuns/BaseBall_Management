package shop.mtcoding.service;

import shop.mtcoding._core.DBConnection;
import shop.mtcoding.dao.StadiumDAO;
import shop.mtcoding.dao.TeamDAO;
import shop.mtcoding.dto.TeamRespDTO;
import shop.mtcoding.model.Stadium;
import shop.mtcoding.model.Team;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class TeamService {

    private static TeamService instance;
    private final TeamDAO teamDAO = TeamDAO.getInstance();
    private final StadiumDAO stadiumDAO = StadiumDAO.getInstance();

    // 생성자 호출 불가
    private TeamService() {}

    // 싱글톤
    public static TeamService getInstance() {
        if (instance == null) {
            instance = new TeamService();
        }
        return instance;
    }

    // 경기장 등록 메소드
    public String 팀등록(String queryString) {
        String[] fields = queryString.split("&");

        // 쿼리스트링 제대로 입력되지 않았을 경우
        if (!fields[0].split("=")[0].equals("stadiumId") || !fields[1].split("=")[0].equals("name")) {
            return "올바른 쿼리스트링으로 요청 바랍니다. : 팀등록?stadiumId=경기장ID&name=팀이름";
        }
        String stadiumIdValue = fields[0].split("=")[1];
        String nameValue = fields[1].split("=")[1];

        int result = teamDAO.insert(Integer.parseInt(stadiumIdValue), nameValue);
        if (result == 1) {
            return "성공";
        }

        // 그 외 오류 발생 시
        return "서버에 문제가 발생하였습니다.";
    }

    public List<TeamRespDTO> 팀조회() {

        // 팀 목록 DAO 메소드 호출
        List<Team> teamListPS = teamDAO.selectAll();

        // stadiumDAO.selectById 메소드를 사용하여 조인
        List<TeamRespDTO> teamRespDTOList = teamListPS.stream()
                .map(team -> {
                    Connection connection = null;
                    try {
                        connection = DBConnection.getConnection();
                        return new TeamRespDTO(team, stadiumDAO.selectById(team.getStadiumId()));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    } finally {
                        if (connection != null) {
                            try {
                                connection.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                })
                .collect(Collectors.toList());


        return teamRespDTOList;
    }

}
