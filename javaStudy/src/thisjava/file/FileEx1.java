package thisjava.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileEx1 {
    public static void main(String[] args) throws FileNotFoundException {
        // 파일 객체
        File dir = new File("C:/Temp/images");
        File file1 = new File("C:/Temp/file1.txt");
        File file2 = new File("C:/Temp/file2.txt");
        File file3 = new File("C:/Temp/file3.txt");
        File file4 = new File("C:/Temp/file4.txt");

        FileOutputStream fos1 = new FileOutputStream("C:/Temp/file1.txt");
        FileOutputStream fos2= new FileOutputStream("C:/Temp/file2.txt");
        FileOutputStream fos3 = new FileOutputStream("C:/Temp/file3.txt");
        FileOutputStream fos4 = new FileOutputStream("C:/Temp/file4.txt");


        File temp = new File("C:/Temp"); // 템프 디렉토리로 파일 객체를 만듬
        File[] contents = temp.listFiles();  // 템프 폴더에 있는 모든 파일을 객체로 만들어 저장하고 있는 배열을 리턴한다

        for (File file : contents){  //향상된 ㅍ문 사용
            System.out.println(file);
        }
        System.out.println(contents.length);


    }
}
