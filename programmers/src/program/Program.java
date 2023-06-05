package program;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Program program = new Program();
        String s = program.solution("He11oWor1d", "lloWorl", 2);
        System.out.println(s);

    }

    public String solution(String my_string, String overwrite_string, int s){
        StringBuilder sb = new StringBuilder(my_string);
        sb.replace(s, s + overwrite_string.length(),overwrite_string); // s의 다음 위치 부터 s +  overwrite의 길이 만큼 overwrite_string  문자열을 교체;
        String sum = sb.toString();

        return sum;
    }
}
