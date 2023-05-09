package thisjava.networkchat;

import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class NetWork_SocketClient {
    public String chatName;
    public String clientIp;
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
                    String receiveJson = dis.readUTF();
                    JSONObject jsonObject = new JSONObject(receiveJson);
                    String command = jsonObject.getString("command");

                    switch (command){
                        case "incoming" :
                            this.chatName = jsonObject.getString("data");
                            chatServer.sendToAll(this, "들어 오셨습니다");
                    }




                }
            } catch (IOException e){
                e.printStackTrace();
            }


        });
    }

    public void send(String json) {
    }

    public void close() {
    }
}
