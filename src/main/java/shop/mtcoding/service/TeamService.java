package shop.mtcoding.service;

import shop.mtcoding.dao.StadiumDAO;
import shop.mtcoding.dao.TeamDAO;
import shop.mtcoding.model.Stadium;

import java.util.List;

public class TeamService {

    private static TeamService instance;
    private final TeamDAO teamDAO = TeamDAO.getInstance();

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
}
