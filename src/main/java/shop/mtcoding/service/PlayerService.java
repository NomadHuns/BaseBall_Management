package shop.mtcoding.service;

import shop.mtcoding._core.DBConnection;
import shop.mtcoding.dao.PlayerDAO;
import shop.mtcoding.dao.StadiumDAO;
import shop.mtcoding.dao.TeamDAO;
import shop.mtcoding.dto.TeamRespDTO;
import shop.mtcoding.model.Team;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerService {

    private static PlayerService instance;
    private static PlayerDAO playerDAO = PlayerDAO.getInstance();

    // 생성자 호출 불가
    private PlayerService() {}

    // 싱글톤
    public static PlayerService getInstance() {
        if (instance == null) {
            instance = new PlayerService();
        }
        return instance;
    }

    // 경기장 등록 메소드
    public String 선수등록(String queryString) {
        String[] fields = queryString.split("&");

        // 쿼리스트링 제대로 입력되지 않았을 경우
        if (!fields[0].split("=")[0].equals("teamId") || !fields[1].split("=")[0].equals("name") || !fields[2].split("=")[0].equals("position")) {
            return "올바른 쿼리스트링으로 요청 바랍니다. : 선수등록?teamId=팀ID&name=선수명&position=포지션";
        }

        String teamIdValue = fields[0].split("=")[1];
        String nameValue = fields[1].split("=")[1];
        String positionValue = fields[2].split("=")[1];

        if (playerDAO.selectByTeamIdAndPosition(Integer.parseInt(teamIdValue), positionValue) != null) {
            return "해당 팀에 이미 동일한 포지션에 선수가 존재합니다.";
        }

        int result = playerDAO.insert(Integer.parseInt(teamIdValue), nameValue, positionValue);
        if (result == 1) {
            return "성공";
        }

        // 그 외 오류 발생 시
        return "서버에 문제가 발생하였습니다.";
    }

}
