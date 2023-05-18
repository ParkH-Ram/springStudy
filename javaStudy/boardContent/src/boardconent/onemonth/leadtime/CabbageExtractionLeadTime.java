package boardconent.onemonth.leadtime;

import java.util.Scanner;

public class CabbageExtractionLeadTime {
    Scanner sc;

    //양배추 추출 리드타임
    public int cabbageExtractionLeadTime(){
        sc = new Scanner(System.in);
        double pro3=0;
        double s = sc.nextInt();

        s = s*30;   // 양배추 * 30 = 양배추 한박스
        double number = (s/20)*2/2000;

        if(number <= 1) {
            pro3 = 1 * 60;
            return (int)pro3;
        } else{
            //  총량 + 1 의 전처리 시간을 가짐
            pro3 = (int)Math.ceil(number) * 60;
            return (int)pro3;
        }

    }


}
