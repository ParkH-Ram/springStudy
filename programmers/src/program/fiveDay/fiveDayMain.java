package program.fiveDay;

public class fiveDayMain {
    public static void main(String[]args){
        주사위게임2 dice = new 주사위게임2();

        System.out.println(dice.solution(3,3,5));
        System.out.println(dice.solution(5,3,3));
        System.out.println(dice.solution(3,5,3));

        원소들의곱과합 multiSum = new 원소들의곱과합();
        int [] mu = {1,2,3,4,5,6,7,8,9};

        System.out.println(multiSum.solution(mu));


    }
}
