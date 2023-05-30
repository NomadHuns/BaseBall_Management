package shop.mtcoding.dao;

import shop.mtcoding._core.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
            int result = statement.executeUpdate();
            // 성공 = 1, 실패 = 0 / -1
            return result;
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
        return -1;
    }

}
