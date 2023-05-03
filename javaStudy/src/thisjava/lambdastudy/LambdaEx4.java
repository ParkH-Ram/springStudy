package thisjava.lambdastudy;

public class LambdaEx4 {
    public static void main(String[] args) {

    }

    int max(int a, int b) {
        return a > b ? a : b;
    }
    // --> 람다



    int roll() {
        return (int) (Math.random() * 6);
    }
    // -> 람다

    int sumArr(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

    int[] emptyArr(){
        return new int[]{};
    }
}

