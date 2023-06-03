package program;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
       // 숫자 출력
        Scanner sc = new Scanner(System.in);
        /*int a = sc.nextInt();
        int b = sc.nextInt();

        System.out.println("a = " + a + "\nb = " +  b);*/

        // 문자 반복 출력
        String str = sc.nextLine();
        int a = sc.nextInt();
        for(int i=1; i<=a; i++){
            System.out.print(str);
        }

    }
}