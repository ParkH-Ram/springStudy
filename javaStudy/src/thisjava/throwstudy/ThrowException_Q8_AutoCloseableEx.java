package thisjava.throwstudy;

import java.io.IOException;

public class ThrowException_Q8_AutoCloseableEx {
    public static void main(String[] args) {
//        ThrowException_Q8_AutoCloseable autoCloseable = null;

         /*try{
             autoCloseable = new ThrowException_Q8_AutoCloseable("file.txt");
             autoCloseable.write("Java");
         } catch (IOException e){
             e.printStackTrace();
         } finally {
             try{ autoCloseable.close();} catch (IOException e){}
         }*/
        // --------- AutoCloseabe 활용

        try(ThrowException_Q8_AutoCloseable autoCloseable = new ThrowException_Q8_AutoCloseable("filePath.txt")) {
            autoCloseable.write("Java");


        }catch (IOException e){
            e.printStackTrace();


        }
    }
}
