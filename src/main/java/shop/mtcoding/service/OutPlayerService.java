package shop.mtcoding.service;

import shop.mtcoding.dao.OutPlayerDAO;
import shop.mtcoding.dao.PlayerDAO;
import shop.mtcoding.dao.TeamDAO;
import shop.mtcoding.dto.OutPlayerRespDTO;
import shop.mtcoding.dto.PlayerRespDTO;
import shop.mtcoding.model.Player;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OutPlayerService {

    private static OutPlayerService instance;
    private static PlayerDAO playerDAO = PlayerDAO.getInstance();
    private static OutPlayerDAO outPlayerDAO = OutPlayerDAO.getInstance();

    // 생성자 호출 불가
    private OutPlayerService() {}

    // 싱글톤
    public static OutPlayerService getInstance() {
        if (instance == null) {
            instance = new OutPlayerService();
        }
        return instance;
    }

    // 방출 선수 등록 메소드
    public String 방출선수등록(String queryString) {
        String[] fields = queryString.split("&");

        // 쿼리스트링 제대로 입력되지 않았을 경우
        if (!fields[0].split("=")[0].equals("playerId") || !fields[1].split("=")[0].equals("reason")) {
            return "올바른 쿼리스트링으로 요청 바랍니다. : 퇴출등록?playerId=선수고유번호&reason=방출사유";
        }

        int playerIdValue = Integer.parseInt(fields[0].split("=")[1]);
        String reasonValue = fields[1].split("=")[1];

        // 선수조회하기
        Player playerPS = playerDAO.findById(playerIdValue);
        // 선수 조회 실패 실패시 실패 메시지 리턴
        if (playerPS == null) {
            return "존재하지 않는 선수고유번호 입니다. 선수 고유번호를 확인해주세요.";
        }

        // 선수 팀 고유번호 null로 변경
        int result = playerDAO.updateById(playerPS.getId(), null, playerPS.getName(), playerPS.getPosition());
        if (result != 1) {
            return "서버 오류 : 선수정보를 변경하는 중 오류 발생";
        }

        // 방출 선수 등록
        result = outPlayerDAO.insert(playerIdValue, reasonValue);
        if (result != 1) {
            return "서버 오류 : 방출 선수를 등록하는 중 오류 발생";
        }

        // 성공시
        return "방출 선수 등록 성공";
    }

    // 전체 선수 목록 불러오기
    public List<OutPlayerRespDTO> 방출선수목록조회() {

        // DAO 메소드 호출
        List<OutPlayerRespDTO> outPlayerRespDTOList = outPlayerDAO.findAll();
        return outPlayerRespDTOList;
    }
}
