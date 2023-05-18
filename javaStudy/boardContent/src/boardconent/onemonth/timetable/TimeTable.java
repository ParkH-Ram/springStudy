package boardconent.onemonth.timetable;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class TimeTable {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

     /*   System.out.print("총 작업 시간(분)을 입력하세요: ");
        int totalMinutes = sc.nextInt();
        sc.nextLine(); // 개행 문자 제거

        int remainingMinutes = totalMinutes; // 남은 작업 시간 변수
        int daysElapsed = 0; // 경과한 날짜 수

        while (remainingMinutes > 0) {
            LocalTime currentTime = LocalTime.now(); // 현재 시간

            // 18시 이후라면 다음 날로 넘어감
            if (currentTime.isAfter(LocalTime.of(18, 0))) {
                LocalDate nextDay = LocalDate.now().plusDays(1); // 다음 날
                System.out.println("다음 날(" + nextDay + ")로 넘어갑니다.");
                daysElapsed++;
            }

            // 남은 작업 시간과 남은 근무 시간 중 짧은 쪽을 선택
            int availableMinutes = (int) currentTime.until(LocalTime.of(18, 0), ChronoUnit.MINUTES);
            int scheduledMinutes = Math.min(remainingMinutes, availableMinutes);

            // 작업 스케줄 출력
            System.out.println("스케줄: " + scheduledMinutes + "분");

            // 남은 작업 시간 및 경과한 시간 업데이트
            remainingMinutes -= scheduledMinutes;
            currentTime = currentTime.plusMinutes(scheduledMinutes);

            // 18시 이후라면 다음 날로 넘어감
            if (currentTime.isAfter(LocalTime.of(18, 0))) {
                LocalDate nextDay = LocalDate.now().plusDays(1); // 다음 날
                System.out.println("다음 날(" + nextDay + ")로 넘어갑니다.");
                daysElapsed++;
            }
        }

        System.out.println("총 작업이 완료되었습니다.");
        System.out.println("경과한 날짜 수: " + daysElapsed + "일");*/
        LocalTime startTime = LocalTime.of(9, 0);   // 근무 시작 시간
        LocalTime lunchTime = LocalTime.of(12, 0);  // 점심 시간 시작
        LocalTime resumeTime = LocalTime.of(13, 0); // 점심 시간 종료
        LocalTime endTime = LocalTime.of(18, 0);    // 근무 종료 시간

        LocalDateTime localDateTime = LocalDateTime.now();

        LocalTime currentTime = LocalTime.now();
        System.out.println(currentTime.getHour());
        System.out.println(localDateTime);

//        int daysElapsed = 0;   // 작업 경과 일


        // 현재 시간이 근무 시간 내에 있는지 확인


        if (currentTime.getHour() >= 9 && currentTime.getHour() < 12) {
            System.out.println("9~12시 입니다.");


        } else if (currentTime.getHour() >= 12 && currentTime.getHour() < 13) {
            System.out.println(currentTime);
            System.out.println("12~13시 입니다");


        } else if (currentTime.getHour() >= 13 && currentTime.getHour() < 18) {
            System.out.println("13~ 18시");
        }
        // 작업 시간(분)
        int workTime = 1500;



        // 현재 시간을 UTC로 변환
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));

        // 작업 시간을 더합니다.
        ZonedDateTime endDate = zonedDateTime.plusMinutes(workTime);

        // 결과 날짜를 현지 시간으로 변환합니다.
        LocalDateTime endDateLocal = endDate.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime();

        System.out.println("작업은 " + endDateLocal + "에 종료됩니다.");
    }
}











