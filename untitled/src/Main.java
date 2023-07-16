public class Main {
    public static void main(String[] args) {
        int i = 1;
        int j = 1;

        for(i =1; i<=9; i++){
            System.out.println(i+ " 단 ");
            for(j=1; j<=9; j ++){
//                System.out.println("i" +"="+ i + " j "+ "=" +j);
                System.out.println(i + " * " + j + " = " + i*j);
            }
        }
    }
}
