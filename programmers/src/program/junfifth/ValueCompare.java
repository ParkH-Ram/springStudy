package program.junfifth;

public class ValueCompare {

    public int solution(int a, int b){
        String num1 = Integer.toString(a);
        String num2 = Integer.toString(b);
        String sum1 = num1 + num2;
        int value1 = Integer.parseInt(sum1);
        int value2 = 2 * a * b;

        if( value1 > value2 || value1 == value2){
            System.out.println(value1);
            return  value1;
        }else{
            System.out.println(value2);
            return value2;
        }
    }

    public static void main(String[] args) {
        ValueCompare cm = new ValueCompare();

        System.out.println(cm.solution(91,2));
    }

}
