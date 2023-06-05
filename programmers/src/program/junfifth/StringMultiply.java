package program.junfifth;

public class StringMultiply {
    public static void main(String[] args) {
        String s = "hi";
        String k = "";
        int n = 3;

        for (int i=1; i<=n; i++){
            k += s;
        }
        System.out.println(k);

    }
}
