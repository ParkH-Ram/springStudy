package program.sixday;

public class A강조하기 {

    public static String solution(String myString) {
        String answer = myString.toLowerCase();
        String value = answer.replace('a', 'A');

        return myString.toLowerCase().replace("a","A");
    }
}


