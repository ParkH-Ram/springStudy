package bakcjun;
import java.util.Scanner;
public class Main_child {
   public static void main(String[]args){
      Scanner hi = new Scanner(System.in);
     int a = hi.nextInt();
     int b = hi.nextInt();
     int c= hi.nextInt();

     int sum = (a+b+c);
     System.out.println(sum);
      hi.close();

   }
}

