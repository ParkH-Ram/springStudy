package thisjava.network2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetWork_EchoClient {
    public static void main(String[] args) {
        try{
            //Socket 생성과 동시에 localhost 5001 Port 로 연결 요청;
            Socket socket = new Socket("localhost", 50001);

            System.out.println("클라 연결 성공");

            //클라이언트가 보낸걸 서버에서 받게.. 보낼 때는 OutputStream
            //데이터 보내기
            String sendMessage = "나는 자바가 좋다";
            OutputStream os = socket.getOutputStream();
            byte[] bytes = sendMessage.getBytes("UTF-8");
            os.write(bytes);
            os.flush();
            System.out.println("클라 데이터 보냄 :" + sendMessage);

            //데이터 받기
            InputStream is = socket.getInputStream();
            bytes = new byte[1024];
            int readByteCount = is.read(bytes);

            // 0인덱스에서 readByteCount 까지 .. 디코딩 할 때 문자 셋 UTF-8
            String receiveMessage = new String(bytes, 0, readByteCount, "UTF-8"); // UTF-8로 해석해서 디코딩
            System.out.println("클라 데이터 받음 : "  + receiveMessage);

            socket.close();
            System.out.println("클라 연결 끊음");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
