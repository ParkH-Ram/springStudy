package program.sixday;

public class 수조작하기1 {

    public static int solution(int n, String control) {
        int answer = 0;

        System.out.println(control);

        for (int i = 0; i < control.length(); i++) {
            if (control.charAt(i) ==  'w') answer += 1;
        }
        return answer;
    }
}
