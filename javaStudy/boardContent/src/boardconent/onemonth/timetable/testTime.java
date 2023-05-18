package boardconent.onemonth.timetable;


import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class testTime {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        List<Task> taskList = new ArrayList<>();

        Task task = new Task(60); //  작업 시간 60분 설정

        // 총 작업시간
        int totalWorkTime =0;

        // 예상 작업일 수
        int estimatedWorkdays  =0;
        
        //시작 시간
        LocalDateTime startTime = LocalDateTime.now();
        
        // 현재 시간 맞추기
        LocalDateTime currentTime = startTime;

        System.out.printf("작업 개수 (개당 %d분): ", task.getRequiredWorkMinutes());

        // 작업 몇개 들어갔는지...  // 작업이 1종류 들어간다.
        int taskCount = sc.nextInt();

        for (int i=0; i<taskCount; i++){

            totalWorkTime += task.getRequiredWorkMinutes();
        }
        System.out.println("총 작업시간 " + totalWorkTime);

        while(totalWorkTime > 0 ){

            // 모든 분을 더한 뒤 나온 시간에서  시간을 추출
            int h = currentTime.plusMinutes(task.getRequiredWorkMinutes()).getHour();
            int m = currentTime.plusMinutes(task.getRequiredWorkMinutes()).getMinute();

            System.out.println(h+ " : " + m);

            if(h == 12){ // 12:00 ~ 12:59
                // 13:00 분에 작업 시작 하기 위해
                currentTime = currentTime.withHour(13).withMinute(0);
                continue;
            } else if(h >= 18|| h<9){  // 18:00 ~ 09:00
                // 하루가 지났으니 작업 일 수 ++
                estimatedWorkdays++;
                // 날짜  다음날 9시부터 시작
                currentTime = currentTime.plusDays(1).withHour(9).withMinute(0);
                continue;
            } else {
                currentTime = currentTime.plusMinutes(task.getRequiredWorkMinutes());
                // 남은 작업 시간 빼서 계산
                totalWorkTime -= task.getRequiredWorkMinutes();
                System.out.println("h = " + h);
                System.out.println("totalWorkTime = " + totalWorkTime );
            }

            System.out.println("작업 종료 시간 : "  + currentTime.getHour()+ ":" +currentTime.getMinute());
        }

        // %d < d 에 estimatedWorkdays 넣겟다.
        System.out.printf("총 작업 소요일 : %d일 \n" , estimatedWorkdays);
        sc.close();
    }

}