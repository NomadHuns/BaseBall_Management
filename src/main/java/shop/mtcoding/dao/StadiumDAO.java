package shop.mtcoding.dao;

import shop.mtcoding._core.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StadiumDAO {
    private static StadiumDAO instance;

    // 생성자 호출 불가능
    private StadiumDAO() {}

    // 싱글톤
    public static StadiumDAO getInstance() {
        if (instance == null) {
            instance = new StadiumDAO();
        }
        return instance;
    }

    // 경기장 등록 메소드
    public int insert(String name) {
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
            String query = "INSERT INTO stadium (name) VALUES (?)";

            statement = connection.prepareStatement(query);
            statement.setString(1, name);

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
