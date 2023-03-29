package bakcjun;

import java.util.Scanner;

public class Main_codingIsHealth {
    public static  void main(String[]args){
        Scanner hi = new Scanner(System.in);

        String str ="";
        int n = hi.nextInt();
        int s = n/4;

        for(int i=0; i<s; i++){
            str += "long ";
        }
        System.out.println(str + "int");
        hi.close();
    }
}
