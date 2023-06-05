package program.junfifth;

//n의 배수 구하기
public class MultiplesOfN {
    public int solution(int num, int n){
        if(num%n==0) return 1;
        else return 0;
    }

    public static void main(String[] args) {
        MultiplesOfN mul = new MultiplesOfN();

        System.out.println(mul.solution(6,3));

    }
}
