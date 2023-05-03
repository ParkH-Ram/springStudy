package thisjava.backjun;

import java.util.Scanner;

public class star {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int s = scan.nextInt();

        for(int i=s; i>0; i--){
            for(int j=0; j<i; j++){
                System.out.print("*");
            }
            System.out.println();

        }

        System.out.println("공백 부터");
        for(int i=0; i<s; i++){
            for(int j=0; j< i; j++){   //ex j = 0   i = 0  " " 구현 후 j 값 증가 반복
                System.out.print(" "); // 왼쪽 부터공백 구현
            }
            for(int j =s; j>i; j--){  //ex j = 5  j > 0  j 값 감소    별 계속 찍음
                System.out.print("*"); // 별 찍기 
            }
            System.out.println();  // 칸 넘기기 구현
        }
    }
}
