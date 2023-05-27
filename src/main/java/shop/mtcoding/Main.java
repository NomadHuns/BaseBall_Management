package shop.mtcoding;

import shop.mtcoding.service.StadiumService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 입력받을 스캐너 인스턴스
        Scanner scanner = new Scanner(System.in);

        while (true) {
            StadiumService stadiumService = StadiumService.getInstance();

            // 기능 요청
            System.out.println("어떤 기능을 요청하시겠습니까?");
            String request = scanner.nextLine().trim();

            // 요청, 쿼리스트링 분리
            String[] requestToken = request.split("\\?");

            // 야구장 등록 메소드
            if (requestToken[0].equals("야구장등록")) {
                System.out.println("야구장 등록 메소드 실행");

                // 경기장 등록 service 메소드 호출
                try {
                    String result = stadiumService.경기장등록(requestToken[1]);

                    // 결과 출력 (성공/실패)
                    System.out.println(result);

                // 쿼리스트링 미입력 시
                } catch (ArrayIndexOutOfBoundsException ae) {
                    System.out.println("올바른 쿼리스트링으로 요청 바랍니다. : 야구장등록?name=야구장이름");
                }
            } else if (requestToken[0].equals("야구장목록")) {
                System.out.println("야구장 목록 메소드 실행");
                stadiumService.경기장조회();
            } else if (requestToken[0].equals("팀등록")) {
                System.out.println("팀 등록 메소드 실행");
            } else if (requestToken[0].equals("팀목록")) {
                System.out.println("팀 목록 메소드 실행");
            } else if (requestToken[0].equals("선수등록")) {
                System.out.println("선수 등록 메소드 실행");
            } else if (requestToken[0].equals("선수목록")) {
                System.out.println("선수 목록 메소드 실행");
            } else if (requestToken[0].equals("퇴출등록")) {
                System.out.println("퇴출 등록 메소드 실행");
            } else if (requestToken[0].equals("퇴출목록")) {
                System.out.println("퇴출 목록 메소드 실행");
            } else if (requestToken[0].equals("포지션별목록")) {
                System.out.println("포지션별 목록 메소드 실행");
            } else if (requestToken[0].equals("종료")) {
                System.out.println("프로그램을 종료합니다....");
                break;
            } else {
                System.out.println("잘못된 요청입니다.");
                System.out.println("-------- 가능한 요청 리스트 --------");
                System.out.println("0. 종료");
                System.out.println("1. 야구장등록?name={야구장명}");
                System.out.println("2. 야구장목록");
                System.out.println("3. 팀등록?stadiumId={스타디움ID}&name={팀명}");
                System.out.println("4. 팀목록");
                System.out.println("5. 선수등록?teamId={팀ID}&name={선수명}&position={포지션}");
                System.out.println("6. 선수목록");
                System.out.println("7. 퇴출등록?playerId={선수ID}&reason={퇴출사유}");
                System.out.println("8. 퇴출목록");
                System.out.println("9. 포지션별목록");
                System.out.println("-------------------------------");
            }
        }
        System.out.println("프로그램이 종료되었습니다.");
    }
}