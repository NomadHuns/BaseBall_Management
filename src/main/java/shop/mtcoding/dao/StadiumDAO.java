package shop.mtcoding.dao;

import shop.mtcoding._core.DBConnection;
import shop.mtcoding.model.Stadium;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StadiumDAO {
    private static StadiumDAO instance;

    // 생성자 호출 불가능
    private StadiumDAO() {
    }

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

    public List<Stadium> selectAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        // DBConnection 클래스로부터 커넥션 받아오기
        try {
            connection = DBConnection.getConnection();
        } catch (Exception e) {
            System.out.println("DB 접근에 실패하였습니다.");
            e.printStackTrace();
        }

        try {
            // 쿼리문 작성
            String query = "SELECT * FROM stadium";
            statement = connection.prepareStatement(query);

            // 쿼리 실행
            resultSet = statement.executeQuery();

            // 실행 결과 Model로 파싱
            List<Stadium> stadiumListPS = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Stadium stadiumPS = new Stadium(id, name, createdAt);
                stadiumListPS.add(stadiumPS);
            }

            // 모델에 담아서 리턴
            return stadiumListPS;
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
        return null;
    }
}
