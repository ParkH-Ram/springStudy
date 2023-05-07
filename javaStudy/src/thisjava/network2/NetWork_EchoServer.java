package thisjava.network2;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class NetWork_EchoServer {
    private static ServerSocket serverSocket =null;

    public static void main(String[] args) {

        System.out.println("-----------------------------------------------------------");
        System.out.println("서버를 종료하려면 q 또는 Q를 입력하고 Enter 키를 입력하세요");
        System.out.println("-----------------------------------------------------------");


        //TCP 서버 시작
        startServer2();

        //키보드 입력
        Scanner sc = new Scanner(System.in);
        while (true){
            String key = sc.nextLine();
            if(key.toLowerCase().equals("q")){
                break;
            }
        }
        sc.close();

        //TCP 서버 종료
        stopServer2();

    }



    public static void startServer2() {
        // 작업 스레드 정의
        Thread thread = new Thread() {

            @Override
            public void run() {
                //ServerSocket 생성 및 Port 바인딩
                try {
                    serverSocket = new ServerSocket(50001);
                    System.out.println("서버 시작 됨");
                    System.out.println(serverSocket.getLocalPort());


                    // 연결 수락 및 데이터 통신
                    while (true) {
                        System.out.println("서버 연결 요청 대기");
                        //연결 수락
                        Socket socket = serverSocket.accept();

                        // 연결된 클라이언트 정보 얻기
                        InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                        String clientIP = isa.getHostString();
                        System.out.println(" 서버" + clientIP + "의 요청 수락");

                        // 데이터 받기
                        DataInputStream dis = new DataInputStream(socket.getInputStream());
                        String message = dis.readUTF();

                        //데이터 보내기
                        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                        dos.writeUTF(message);
                        dos.flush();
                        System.out.println("서버 받은 데이터 다시 : " + message) ;

                        //연결 끊기
                        socket.close();
                        System.out.println(" 서버" + clientIP + "의 연결 끊음");
                    }
                } catch (IOException e) {

                }
            }
        };
        //스레드 시작
        thread.start();
    }

    public static void stopServer2() {
        try {   //q 를 누르면 서버 종료
            serverSocket.close();
            System.out.println("서버 종료");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
