package program;

import java.util.Scanner;

public class UpperCase {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        UpperCase upperCase = new UpperCase();
        String h =  upperCase.solution(s);

        System.out.println(h);

    }
    public  String solution(String str) {
        String values = "";

        //for each 문 char 배열로 하나씩 비교
        for (char c : str.toCharArray()) {
            //  소문자라면  Character.isLowCase("char 타입")
            if (Character.isLowerCase(c)) {
                values += Character.toUpperCase(c);  // 스트링에 대문자로 바뀐 값을 입력
            } else {
                values += Character.toLowerCase(c); // 대문자를 소문자로 변환}
            }
        }
        return values;
    }
}
