package program;

import java.util.Scanner;

public class FlagBoolean {
        public int solution(int a, int b, boolean flag){
            if (flag) return a + b;
            else return a - b;
        }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in)    ;
        FlagBoolean flagBoolean = new FlagBoolean();
        int s = flagBoolean.solution(-4 , 7, false);
        System.out.println(s);
    }

}
