package shop.mtcoding.dao;

import shop.mtcoding._core.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StadiumDAO {
    private static StadiumDAO instance;

    private StadiumDAO() {}

    public static StadiumDAO getInstance() {
        if (instance == null) {
            instance = new StadiumDAO();
        }
        return instance;
    }

    public void insert(String name) {
        Connection connection = null;
        PreparedStatement statement;
        try {
            connection = DBConnection.getConnection();
        } catch (Exception e) {
            System.out.println("DB 접근에 실패하였습니다.");
            e.printStackTrace();
        }
        try {
            String query = "INSERT INTO stadium (name) VALUES (?)";

            statement = connection.prepareStatement(query);
            statement.setString(1, name);

            int result = statement.executeUpdate();
            if (result == 1) {
                System.out.println("성공");
            } else {
                System.out.println("DB에 데이터를 삽입하는데 실패하였습니다.");
            }
        } catch (Exception e) {
            System.out.println("쿼리문을 실행하는데 실패하였습니다.");
            e.printStackTrace();
        }
    }

}
