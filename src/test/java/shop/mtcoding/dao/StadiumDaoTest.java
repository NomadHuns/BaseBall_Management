package shop.mtcoding.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StadiumDaoTest {
    private StadiumDAO stadiumDAO = StadiumDAO.getInstance();

    @Test
    public void insert_test() {
        // given
        String name = "강남경기장";

        // when
        stadiumDAO.insert(name);
    }
}
