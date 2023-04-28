package thisjava.throwstudy;

import java.io.IOException;

public class ThrowException_Q8_AutoCloseable implements AutoCloseable{
    public ThrowException_Q8_AutoCloseable(String filePath) throws IOException{
        System.out.println(filePath + "파일을 엽니다.");
    }
    public void write(String data) throws  IOException{
        System.out.println(data + "를 파일에 저장합니다");
    }
    @Override
    public void close() throws IOException{
        System.out.println("파일 닫는다");
    }
}
