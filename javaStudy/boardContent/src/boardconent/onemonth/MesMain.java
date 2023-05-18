package boardconent.onemonth;

import boardconent.onemonth.leadtime.CabbageExtractionLeadTime;

public class MesMain {
    public static void main(String[] args) {
       //양배추 추출 리드타임
        CabbageExtractionLeadTime cabbageExtractionLeadTime = new CabbageExtractionLeadTime();
        int cabbageExtraction = cabbageExtractionLeadTime.cabbageExtractionLeadTime();
        System.out.println("양배추 추출 리드타임 : " + cabbageExtraction + "분");



    }
}
