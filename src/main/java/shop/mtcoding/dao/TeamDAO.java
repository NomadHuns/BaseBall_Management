package shop.mtcoding.dao;

import shop.mtcoding._core.DBConnection;
import shop.mtcoding.model.Stadium;
import shop.mtcoding.model.Team;

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

    // 팀 등록 메소드
    public int insert(int stadiumId, String name) {
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
            String query = "INSERT INTO team (stadium_id, name) VALUES (?, ?)";

            statement = connection.prepareStatement(query);
            statement.setInt(1, stadiumId);
            statement.setString(2, name);

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

    // 팀 목록 조회 메소드
    public List<Team> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        List<Team> teamListPS = new ArrayList<>();

        // DBConnection 클래스로부터 커넥션 받아오기
        try {
            connection = DBConnection.getConnection();
        } catch (Exception e) {
            System.out.println("DB 접근에 실패하였습니다.");
            e.printStackTrace();
        }

        try {
            // 쿼리문 작성
            String query = "SELECT * FROM team";
            statement = connection.prepareStatement(query);

            // 쿼리 실행
            resultSet = statement.executeQuery();

            // 실행 결과 Model로 파싱
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int stadiumId = resultSet.getInt("stadium_id");
                String name = resultSet.getString("name");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Team teamPS = new Team(id, stadiumId, name, createdAt);
                teamListPS.add(teamPS);
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
        return teamListPS;
    }

    // ID로 조회
    public Team findById(int stadiumIdReq) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Team teamPS = null;

        // DBConnection 클래스로부터 커넥션 받아오기
        try {
            connection = DBConnection.getConnection();
        } catch (Exception e) {
            System.out.println("DB 접근에 실패하였습니다.");
            e.printStackTrace();
        }

        try {
            String query = "SELECT * FROM team WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, stadiumIdReq);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int stadiumId = resultSet.getInt("stadium_id");
                String name = resultSet.getString("name");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                teamPS = new Team(id, stadiumId, name, createdAt);
            }
        } catch (Exception e) {
            System.out.println("쿼리문을 실행하는데 실패하였습니다.");
            e.printStackTrace();
        }
        return teamPS;
    }
}
