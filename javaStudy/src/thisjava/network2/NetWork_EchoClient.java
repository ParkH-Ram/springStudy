package thisjava.network2;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetWork_EchoClient {
    public static void main(String[] args) {
        try{
            //Socket 생성과 동시에 localhost 5001 Port 로 연결 요청;
            Socket socket = new Socket("localhost", 50001);

            System.out.println("클라 연결 성공");

            // 데이터 보내기 ( 서버로 보낸 다는 뜻 )
            String sendMessage = "나는 자바가 좋데";
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(sendMessage);
            dos.flush();
            System.out.println("클라이언트의 데이터 보냄" + sendMessage);

            //데이터 받기
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String receiveMessage = dis.readUTF();
            System.out.println("클라의 데데이터 받음 : " + receiveMessage);




            // 연결 끊기
            socket.close();
            System.out.println("클라 연결 끊음");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
