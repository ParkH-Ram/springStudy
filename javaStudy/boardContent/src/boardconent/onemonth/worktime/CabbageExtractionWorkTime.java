package boardconent.onemonth.worktime;

import java.util.Scanner;

public class CabbageExtractionWorkTime {
        Scanner sc= new Scanner(System.in);;

        // 양배추 추출 작업시간
    public int cabbageExtractionWorkTime(){

        double pro3=0;
        double s = sc.nextInt();

        s = s*30;   // 양배추 * 30 = 양배추 한박스

        // (제품개수 / 20*2) 2000L * 2880 분 ( 48 시간 )
        double number = (s/20*2)/2000*2000;

        // 양배추 추출 작업 시간 추출에 넣으면 자동

        if(number <= 1) {
            pro3 = 1 * 60;
            return (int)pro3;
        } else{
            //  총량 + 1 의 전처리 시간을 가짐
            pro3 = (int)Math.ceil(number) * 60;
            return (int)pro3;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double s = sc.nextInt();
        double number = (s/20*2)/2000*2880;

        System.out.println(number);
    }

}
