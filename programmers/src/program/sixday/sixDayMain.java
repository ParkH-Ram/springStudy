package program.sixday;

public class sixDayMain {

    public static void main(String[]args) {
        int[] num = {1,5,5};
        for (int i : 마지막두원소.solution(num)){
            System.out.println(i);
        }

        String hell = "wwwwwwww";
        int an =0;

        for(int i=0; i<hell.length(); i++){
            if(hell.charAt(i) == 'w') an +=1;
        }

        System.out.println(an);
    }

}
