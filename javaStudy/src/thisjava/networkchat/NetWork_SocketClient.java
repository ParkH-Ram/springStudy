package thisjava.networkchat;

import java.net.Socket;

public class NetWork_SocketClient {
    public String chatName;
    public String clientIp;
    NetWork_ChatServer chatServer;
    Socket socket;

    //생성자
    public NetWork_SocketClient(NetWork_ChatServer chatServer, Socket socket){
        this.chatServer = chatServer;
        this.socket = socket;
    }
}
