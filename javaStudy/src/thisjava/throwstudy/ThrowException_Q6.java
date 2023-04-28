package thisjava.throwstudy;

public class ThrowException_Q6 {
    public static void main(String[] args) {
        String[] Array = {"10", "2a"};
        int value = 0;
        for(int i=0; i<=2; i++){
            try {
                value = Integer.parseInt(Array[i]);
            }catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            } finally{
                System.out.println(value);
            }
        }
    }
}
