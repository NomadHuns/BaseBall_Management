package shop.mtcoding.service;

import shop.mtcoding.dao.StadiumDAO;

public class StadiumService {

    private static StadiumService instance;
    private final StadiumDAO stadiumDAO = StadiumDAO.getInstance();

    // 생성자 호출 불가
    private StadiumService() {}

    // 싱글톤
    public static StadiumService getInstance() {
        if (instance == null) {
            instance = new StadiumService();
        }
        return instance;
    }

    // 경기장 등록 메소드
    public String 경기장등록(String queryString) {
        String[] fields = queryString.split("=");

        // 쿼리스트링 제대로 입력되었을 경우
        if (fields[0].equals("name")) {
            int result = stadiumDAO.insert(fields[1]);
            if (result == 1) {
                return "성공";
            }

        // 올바르지 않은 쿼리스트링 입력 시
        } else {
            System.out.println("올바른 쿼리스트링으로 요청 바랍니다. : 야구장등록?name=야구장이름");
        }

        // 그 외 오류 발생 시
        return "서버에 문제가 발생하였습니다.";
    }
}
