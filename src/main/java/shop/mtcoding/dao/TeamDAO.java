package shop.mtcoding.dao;

import shop.mtcoding._core.DBConnection;
import shop.mtcoding.model.Stadium;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO {
    private static TeamDAO instance;

    // 생성자 호출 불가능
    private TeamDAO() {
    }

    // 싱글톤
    public static TeamDAO getInstance() {
        if (instance == null) {
            instance = new TeamDAO();
        }
        return instance;
    }

    // 경기장 등록 메소드
    public int insert(int stadiumId, String name) {
        Connection connection = null;
        PreparedStatement statement;

        // DBConnection 클래스로부터 커넥션 받아오기
        try {
            connection = DBConnection.getConnection();
        } catch (Exception e) {
            System.out.println("DB 접근에 실패하였습니다.");
            e.printStackTrace();
        }

        try {
            // 쿼리문 작성
            String query = "INSERT INTO team (stadium_id, name) VALUES (?, ?)";

            statement = connection.prepareStatement(query);
            statement.setInt(1, stadiumId);
            statement.setString(2, name);

            // 쿼리 실행
            int result = statement.executeUpdate();
            // 성공 = 1, 실패 = 0 / -1
            return result;
        } catch (Exception e) {
            System.out.println("쿼리문을 실행하는데 실패하였습니다.");
            e.printStackTrace();
        }
        return -1;
    }
}
