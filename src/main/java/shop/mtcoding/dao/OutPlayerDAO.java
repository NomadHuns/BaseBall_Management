package shop.mtcoding.dao;

import shop.mtcoding._core.DBConnection;
import shop.mtcoding.dto.OutPlayerRespDTO;
import shop.mtcoding.model.Stadium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OutPlayerDAO {
    private static OutPlayerDAO instance;

    // 생성자 호출 불가능
    private OutPlayerDAO() {
    }

    // 싱글톤
    public static OutPlayerDAO getInstance() {
        if (instance == null) {
            instance = new OutPlayerDAO();
        }
        return instance;
    }

    // 방출 선수 등록 메소드
    public int insert(int playerId, String reason) {
        Connection connection = null;
        PreparedStatement statement = null;

        int result = -1;

        // DBConnection 클래스로부터 커넥션 받아오기
        try {
            connection = DBConnection.getConnection();
        } catch (Exception e) {
            System.out.println("DB 접근에 실패하였습니다.");
            e.printStackTrace();
        }

        try {
            // 쿼리문 작성
            String query = "INSERT INTO out_player (player_id, reason) VALUES (?, ?)";

            statement = connection.prepareStatement(query);
            statement.setInt(1, playerId);
            statement.setString(2, reason);

            // 쿼리 실행
            result = statement.executeUpdate();

        } catch (Exception e) {
            System.out.println("쿼리문을 실행하는데 실패하였습니다.");
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("자원을 해제하는데 실패하였습니다.");
                e.printStackTrace();
            }
        }
        return result;
    }

    // 퇴출 선수 목록 조회 메소드
    public List<OutPlayerRespDTO> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<OutPlayerRespDTO> outPlayerRespDTOList = new ArrayList<>();

        // DBConnection 클래스로부터 커넥션 받아오기
        try {
            connection = DBConnection.getConnection();
        } catch (Exception e) {
            System.out.println("DB 접근에 실패하였습니다.");
            e.printStackTrace();
        }

        try {
            // 쿼리문 작성
            String query = "SELECT p.id, p.name, p.position, o.reason, o.created_at FROM player p LEFT OUTER JOIN out_player o ON p.id = o.player_id";
            statement = connection.prepareStatement(query);

            // 쿼리 실행
            resultSet = statement.executeQuery();

            // 실행 결과 DTO로 파싱
            while (resultSet.next()) {
                int id = resultSet.getInt("p.id");
                String name = resultSet.getString("p.name");
                String position = resultSet.getString("p.position");
                String reason = resultSet.getString("o.reason");
                Timestamp createdAt = resultSet.getTimestamp("o.created_at");
                OutPlayerRespDTO outPlayerRespDTO = new OutPlayerRespDTO(id, name, position, reason, createdAt);
                outPlayerRespDTOList.add(outPlayerRespDTO);
            }

        } catch (Exception e) {
            System.out.println("쿼리문을 실행하는데 실패하였습니다.");
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                System.out.println("자원을 해제하는데 실패하였습니다.");
                e.printStackTrace();
            }
        }

        return outPlayerRespDTOList;
    }

}
