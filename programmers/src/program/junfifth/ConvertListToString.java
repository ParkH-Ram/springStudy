package program.junfifth;

import java.util.ArrayList;
import java.util.List;

public class ConvertListToString {
    public String solution(String[] arr){
        // 리스트를 문자열로 변환 문제
        String s="";

        // 배열을 하나씩  스트링 문자열에 저장
        for (int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
            s += arr[i];
        }
        return s.toString();
    }

    public static void main(String[] args) {
        String arrString[] = {"a","b","c"};
        ConvertListToString con = new ConvertListToString();
        System.out.println(con.solution(arrString));

    }
}
