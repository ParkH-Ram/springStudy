package program.junfifth;

public class SomethingBigger {


    // 스트링 형태라 실패 ;
   /* public int solution(String a, String b){
        int num1 = Integer.parseInt(a);
        int num2 = Integer.parseInt(b);
        int value = 0;
        if(num1 > num2 || num1 == num2){
            System.out.println(a + b);
            String ab = a+b;
            value = Integer.parseInt(ab);
        } else {
            System.out.println(b + a);
            String ba = b+a;
            value = Integer.parseInt(ba);
        }

        return value;
    }*/

    //숫자만 비교하는게 아니고 계속 합을 비교해야 함
/*    public int solution(int a, int b){
        String num1="";
        String num2= "";
        String sum =" ";
        int value = 0;
        if (a > b || a ==b){
            num1 = Integer.toString(a);
            num2 = Integer.toString(b);
            sum = num1 + num2;
            value = Integer.parseInt(sum);
        } else{
            num1 = Integer.toString(a);
            num2 = Integer.toString(b);
            sum = num2 + num1;
            value = Integer.parseInt(sum);
        }


        return value;
    }*/

    public int solution(int a, int b){
        String num1 = Integer.toString(a);
        String num2 = Integer.toString(b);
        String sum1 = num1 + num2;
        String sum2 = num2 + num1;
        int value1 = Integer.parseInt(sum1);
        int value2 = Integer.parseInt(sum2);

        if( value1 > value2 || value1 == value2){
            System.out.println(value1);
            return  value1;
        }else{
            System.out.println(value2);
            return value2;
        }

    }

    public static void main(String[] args) {

        SomethingBigger some = new SomethingBigger();
        System.out.println(some.solution(88, 88));

    }
}
