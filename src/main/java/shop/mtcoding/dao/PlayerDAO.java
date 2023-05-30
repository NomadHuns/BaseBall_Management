package shop.mtcoding.dao;

import shop.mtcoding._core.DBConnection;
import shop.mtcoding.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private static PlayerDAO instance;

    // 생성자 호출 불가능
    private PlayerDAO() {
    }

    // 싱글톤
    public static PlayerDAO getInstance() {
        if (instance == null) {
            instance = new PlayerDAO();
        }
        return instance;
    }

    // 팀 별 포지션 선수 조회
    public Player selectByTeamIdAndPosition(int teamIdReq, String positionReq) {
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
            String query = "SELECT * FROM player WHERE team_id = ? AND position = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, teamIdReq);
            statement.setString(2, positionReq);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int teamId = resultSet.getInt("team_id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                return new Player(id, teamId, name, position, createdAt);
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
        return null;
    }


    // 선수 등록 메소드
    public int insert(int teamId, String name, String position) {
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
            String query = "INSERT INTO player (team_id, name, position) VALUES (?, ?, ?)";

            statement = connection.prepareStatement(query);
            statement.setInt(1, teamId);
            statement.setString(2, name);
            statement.setString(3, position);

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

    // 선수 목록 조회 메소드
    public List<Player> selectAll(int teamIdReq) {
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
            String query = "SELECT * FROM player WHERE team_id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, teamIdReq);

            // 쿼리 실행
            resultSet = statement.executeQuery();

            // 실행 결과 Model로 파싱
            List<Player> playerListPS = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int stadiumId = resultSet.getInt("team_id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Player playerPS = new Player(id, stadiumId, name, position, createdAt);
                playerListPS.add(playerPS);
            }

            // 모델에 담아서 리턴
            return playerListPS;
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

    // ID로 조회하기 메소드
    public Player selectById(int stadiumId) {
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
            String query = "SELECT * FROM player WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, stadiumId);

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int teamId = resultSet.getInt("team_id");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                return new Player(id, teamId, name, position, createdAt);
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
        return null;
    }

    // 선수 정보 수정 메소드
    public int updateById(int id, Integer teamId, String name, String position) {
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
            String query = "UPDATE player SET team_id = ?, name = ?, position = ? WHERE id = ?";

            statement = connection.prepareStatement(query);
            if (teamId != null) {
                statement.setInt(1, teamId);
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            statement.setString(2, name);
            statement.setString(3, position);
            statement.setInt(4, id);

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
