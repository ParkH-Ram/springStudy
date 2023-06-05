package program.junfifth;

public class ShuffleString {
    public String solution(String str1, String str2) {
        char[] a = str1.toCharArray();
        char[] b = str2.toCharArray();
        String str = "";

        for (int i = 0; i < a.length; i++) {
            str += a[i];
            str += b[i];
            System.out.println(a[i] + " " + b[i]);
        }
        return str;
    }

    public static void main(String[] args) {
        ShuffleString jun = new ShuffleString();
        String str = jun.solution("asdf","qwer");

        System.out.println(str);



    }
}
