package thisjava.networkchat;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetWork_SocketClient {
    String chatName;
    String clientIp;
    NetWork_ChatServer chatServer;
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;

    //생성자
    public NetWork_SocketClient(NetWork_ChatServer chatServer, Socket socket){

        try {
            this.chatServer = chatServer;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();// 소켓 안에 클라이언트에 대한 정보가 담겨 있다. get 으로 정ㄴ보를 얻음
            this.clientIp = isa.getHostName();  // HostName 컴퓨터 이름 , HostString 은 만약 호스트 이름이 존재하지 않으면 아이피를 문자열로 리턴
            receive();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receive() {
        chatServer.threadPool.execute( ()->{
            try {
                while(true){
                    //{"command": "incoming", "data" : "chatName"}
                    //{"command" : "message", "data" : "xxx"} command 가 메시지면 보낼 메시지라고 가정.
                    String receiveJson = dis.readUTF(); // 연결이 끊기면 예외 발생
                    JSONObject jsonObject = new JSONObject(receiveJson);
                    String command = jsonObject.getString("command");

                    switch (command){
                        case "incoming" :  // 채팅방에 들어 오고 시펑
                            this.chatName = jsonObject.getString("data");
                            chatServer.sendToAll(this, "들어 오셨습니다"); // 람다식이 사용되면 this는 socket Client
                            chatServer.addSocketClient(this);
                            break;
                        case "message": // 내가 보내준 메시지를 모든 클라이언트에게 전송
                            String message = jsonObject.getString("data");
                            chatServer.sendToAll(this, message);
                            break;
                    }

                }
            } catch (Exception e){
                chatServer.sendToAll(this, "나갔슈");
                e.printStackTrace();
                chatServer.removeSocketClient(this);
            }
        });
    }

    public void send(String json) {
        try {
            dos.writeUTF(json);
            dos.flush();
        } catch(IOException e) {

        }
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
