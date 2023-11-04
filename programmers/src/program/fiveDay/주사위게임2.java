package program.fiveDay;

public class 주사위게임2 {
    public int solution(int a, int b, int c){
        int sum = a+b+c;
        int square = (a*a) + (b*b) + (c*c);
        int cube = (a*a*a) + (b*b*b) + (c*c*c);

        if( a == b && b == c)  return sum * square * cube;
        else if( a== b || b == c || a == c) return square * sum;
        else return sum;

    }
}
