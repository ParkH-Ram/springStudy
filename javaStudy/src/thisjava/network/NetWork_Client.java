package thisjava.network;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetWork_Client {
    public static void main(String[] args) {
        try{
            //Socket 생성과 동시에 localhost 5001 Port로 연결 요청;
            Socket socket = new Socket("localhost", 50001);

            System.out.println("클라 연결 성곻ㅇ");

            socket.close();
            System.out.println("클라 연결 끊음");

        }catch(UnknownHostException e){
            //ip 표기가 잘못 되었을 때
            System.out.println("Unknown = " + e.getMessage());
        } catch (IOException e){
            //해당 포트의 서버에 연결 못하는 경우
            System.out.println("IOE = " + e.getMessage());
        }

    }
}
