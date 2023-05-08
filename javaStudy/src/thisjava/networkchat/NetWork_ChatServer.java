package thisjava.networkchat;

import org.json.simple.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



public class NetWork_ChatServer {
    // 필드
    ServerSocket serverSocket;
    ExecutorService threadPool = Executors.newFixedThreadPool(100);  // 100개의 클라이언트가 동시 채팅 가능하게
    Map<String, NetWork_SocketClient> chatRoom = Collections.synchronizedMap(new HashMap<>());
    
    // 매소드 시작 서버 
    public void start() throws IOException{
        serverSocket = new ServerSocket(50001);  // 포트 번호 제공
        
        Thread thread = new Thread(()-> {  // 스레드 객체로 부터 직접 만듬
            try{
                while (true) {
                    // accept는 연결을 수락하고 통신을 수락하고 소켓 클라이언트 내부에서 사용한다..
                    Socket socket = serverSocket.accept();  // 연결 요청이 들어오면 블락이 해체 돼서 통신 객체인 socket을 만들어냄
                    // 소켓클라이언트는 바깥쪽에 있는 서버에 대한 참조. 이 소켓에 대한 참조를 매개값으로 받아서 사용한다.
                    NetWork_SocketClient sc = new NetWork_SocketClient(this, socket); // 람다식에서 디스는 필드 참조
                }
            } catch (Exception e){

            }
        });
        thread.start();
    }
    // 메소드 : 클라이어트 연결시 SocketClient 생성 추가
    public void addSocketClient(NetWork_SocketClient socketClient){
        String key = socketClient.chatName + "@" + socketClient.clientIp;
        chatRoom.put(key, socketClient);
        System.out.println("입장" + key);
        System.out.println("현재 채팅자 수 : " + chatRoom.size() + "\n");
    }

    // 클라이언트 종료 시 삭제 되는 메소드
    public void removeSocketClient(NetWork_SocketClient socketClient){
        String key = socketClient.chatName + "@" + socketClient.clientIp;
        chatRoom.remove(key);
        System.out.println("나감" + key);
        System.out.println("현재 채팅자 수 : " + chatRoom.size() + "\n");
    }

    // 모든 클라이언트에게 메시지 보냄
    public void sendToAll(NetWork_SocketClient sender, String message){
        JSONObject root = new JSONObject();
        root.put("cl")
    }

}
