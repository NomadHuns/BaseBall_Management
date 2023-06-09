package shop.mtcoding;

import shop.mtcoding.dto.OutPlayerRespDTO;
import shop.mtcoding.dto.PlayerRespDTO;
import shop.mtcoding.dto.PositionRespDTO;
import shop.mtcoding.dto.TeamRespDTO;
import shop.mtcoding.model.Player;
import shop.mtcoding.model.Team;
import shop.mtcoding.service.OutPlayerService;
import shop.mtcoding.service.PlayerService;
import shop.mtcoding.service.StadiumService;
import shop.mtcoding.service.TeamService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 입력받을 스캐너 인스턴스
        Scanner scanner = new Scanner(System.in);

        while (true) {
            StadiumService stadiumService = StadiumService.getInstance();
            TeamService teamService = TeamService.getInstance();
            PlayerService playerService = PlayerService.getInstance();
            OutPlayerService outPlayerService = OutPlayerService.getInstance();

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

            // 경기장 조회 메소드
            } else if (requestToken[0].equals("야구장목록")) {
                System.out.println("야구장 목록 메소드 실행");

                // 경기장 조회 service 메소드 호출
                stadiumService.경기장조회();

            // 팀 등록 메소드
            } else if (requestToken[0].equals("팀등록")) {
                System.out.println("팀 등록 메소드 실행");

                // 팀 등록 service 메소드 호출
                try {
                    String result = teamService.팀등록(requestToken[1]);

                    // 결과 출력 (성공/실패)
                    System.out.println(result);

                    // 쿼리스트링 미입력 시
                } catch (ArrayIndexOutOfBoundsException ae) {
                    System.out.println("올바른 쿼리스트링으로 요청 바랍니다. : 팀등록?stadiumId=경기장ID&name=팀이름");
                }

            // 팀 조회 메소드
            } else if (requestToken[0].equals("팀목록")) {
                System.out.println("팀 목록 메소드 실행");

                // 팀 조회 service 메소드 호출
                List<TeamRespDTO> teamRespDTOList = teamService.팀조회();

                // 값이 존재할 경우 실행
                if (!teamRespDTOList.isEmpty() | teamRespDTOList != null) {

                    // 헤더 로우 출력
                    String headRow = "ID    | 팀          | 경기장            | 등록일";
                    System.out.println(headRow);

                    // 순환하면서 출력
                    teamRespDTOList.stream().forEach(
                            teamRespDTO -> {
                                String row = teamRespDTO.getId()
                                        + "     | "
                                        + teamRespDTO.getTeamName()
                                        + "         | "
                                        + teamRespDTO.getStadiumName()
                                        + "          | "
                                        + teamRespDTO.getCreatedAt();
                                System.out.println(row);
                            }
                    );
                }

            // 선수 등록 메소드
            } else if (requestToken[0].equals("선수등록")) {
                System.out.println("선수 등록 메소드 실행");

                // 선수 등록 service 메소드 호출
                try {
                    String result = playerService.선수등록(requestToken[1]);

                    // 결과 출력 (성공/실패)
                    System.out.println(result);

                    // 쿼리스트링 미입력 시
                } catch (ArrayIndexOutOfBoundsException ae) {
                    System.out.println("올바른 쿼리스트링으로 요청 바랍니다. : 선수등록?teamId=팀ID&name=선수명&position=포지션");
                }

            // 선수 목록 조화 메소드
            } else if (requestToken[0].equals("선수목록")) {
                System.out.println("선수 목록 메소드 실행");

                // 선수 조회 service 메소드 호출
                try {
                    List<PlayerRespDTO> playerRespDTOList = playerService.선수조회(requestToken[1]);

                    // 결과 출력
                    // 값이 존재할 경우 실행
                    if (!playerRespDTOList.isEmpty() | playerRespDTOList != null) {

                        // 헤더 로우 출력
                        String headRow = "ID    | 팀          | 선수명        | 포지션      | 등록일";
                        System.out.println(headRow);

                        // 순환하면서 출력
                        playerRespDTOList.stream().forEach(
                                playerRespDTO -> {
                                    String row = playerRespDTO.getId()
                                            + "     | "
                                            + playerRespDTO.getTeamName()
                                            + "         | "
                                            + playerRespDTO.getPlayerName()
                                            + "         | "
                                            + playerRespDTO.getPosition()
                                            + "      | "
                                            + playerRespDTO.getCreatedAt();
                                    System.out.println(row);
                                }
                        );
                    }

                    // 쿼리스트링 미입력 시
                } catch (ArrayIndexOutOfBoundsException ae) {
                    System.out.println("올바른 쿼리스트링으로 요청 바랍니다. : 선수목록?teamId=팀ID");
                }

            } else if (requestToken[0].equals("퇴출등록")) {
                System.out.println("퇴출 등록 메소드 실행");

                // 선수 방출 service 메소드 호출
                try {
                    String result = outPlayerService.방출선수등록(requestToken[1]);
                    System.out.println(result);

                // 쿼리스트링 미입력 시
                } catch (ArrayIndexOutOfBoundsException ae) {
                    System.out.println("올바른 쿼리스트링으로 요청 바랍니다. : 퇴출등록?playerId=선수고유번호&reason=방출사유");
                }

            } else if (requestToken[0].equals("퇴출목록")) {
                System.out.println("퇴출 목록 메소드 실행");

                System.out.println("선수 목록 메소드 실행");

                // 퇴출 선수 조회 service 메소드 호출
                List<OutPlayerRespDTO> outPlayerRespDTOList = outPlayerService.방출선수목록조회();

                    // 결과 출력
                    // 값이 존재할 경우 실행
                    if (!outPlayerRespDTOList.isEmpty() | outPlayerRespDTOList != null) {

                        // 헤더 로우 출력
                        String headRow = "ID    | 선수명          | 포지션        | 퇴출사유      | 퇴출일";
                        System.out.println(headRow);

                        // 순환하면서 출력
                        outPlayerRespDTOList.stream().forEach(
                                outPlayerRespDTO -> {
                                    String row = outPlayerRespDTO.getId()
                                            + "     | "
                                            + outPlayerRespDTO.getName()
                                            + "         | "
                                            + outPlayerRespDTO.getPosition()
                                            + "         | "
                                            + outPlayerRespDTO.getReason()
                                            + "      | "
                                            + outPlayerRespDTO.getCreatedAt();
                                    System.out.println(row);
                                }
                        );
                    }
                } else if (requestToken[0].equals("포지션별목록")) {
                System.out.println("포지션별 목록 메소드 실행");

                // 팀 및 선수 목록 불러오기
                PositionRespDTO positionRespDTO = playerService.포지션별선수조회();

                // 조회 실패할 경우 오류 메시지
                if (positionRespDTO == null) {
                    System.out.println("서버 문제 : 선수 리스트 불러올수 없음");
                }

                // 팀 리스트 출력
                List<Team> teamList = positionRespDTO.getTeamList();
                System.out.print("포지션    |");
                teamList.stream().forEach(
                        team -> {
                            System.out.print(team.getName() + "   | ");
                        }
                );
                System.out.println();

                포지션별출력("1루수", positionRespDTO.getFirstBaseManList(), positionRespDTO.getTeamList().size());
                포지션별출력("2루수", positionRespDTO.getSecondBaseManList(), positionRespDTO.getTeamList().size());
                포지션별출력("3루수", positionRespDTO.getThirdBaseManList(), positionRespDTO.getTeamList().size());
                포지션별출력("포수", positionRespDTO.getCatcherList(), positionRespDTO.getTeamList().size());

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

    public static void 포지션별출력(String position, List<Player> playerList, int teamSize) {
        System.out.print(position + "    | ");
        if (playerList == null || playerList.isEmpty()) {
            System.out.println("");
            return;
        }
        for (int i = 0; i < teamSize; i++) {
            System.out.print(playerList.get(i).getName() + "  | ");
        }
        System.out.println();
    }
}