package thisjava.networkchat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.json.JSONObject;

public class NetWork_ChatClient {
    //필드 선언
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    String chatName;

    //메소드 : 서버 연결
    public void connect() throws IOException{
        socket = new Socket("localhost", 50001);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        System.out.println("클라이언트 서버에 연결됨 ");
    }


    //메소드 : JSON 받기
    public void receive(){   // 항상 받을 준비 해야 한다 = 스레드

        Thread thread = new Thread(() -> {  // 받기만 하는 스레드
            try{
                while(true){  // 계속 해서 받아야 하니까 
                    String json = dis.readUTF(); // 데이터 안오면 블락, 오면 리턴
                    JSONObject root = new JSONObject(json);
                    String clientIp = root.getString("clientIp"); // 어디서 보냈는지 확인
                    String chatName = root.getString("chatName"); // 누가 보냈는지
                    String message = root.getString("message");     // 뭘 보냈는지
                    System.out.println("<" + chatName + "@" + clientIp + ">" + message);
                    dis.readUTF();
                }

            } catch(Exception e){
                e.printStackTrace();
                System.out.println("클라이언트 서버 연결 끊김 ");
                System.exit(0);  // 프로세스 종료 // 서버에서 연결을 끊었을 경우
            }
        });
        thread.start();
    }

    public void send(String json) throws IOException{  // 보내는 메서드
        dos.writeUTF(json);
        dos.flush();
    }

    public void unConnect()throws IOException{
        socket.close();
    }

    // 연결하기 위해 채팅방에 들어가기 위해 대화명 입력 받고,
    public static void main(String[] args) {
        try{
            NetWork_ChatClient chatClient = new NetWork_ChatClient();
            chatClient.connect();

            Scanner sc = new Scanner(System.in);
            System.out.print("대화명 입력 : ");
            chatClient.chatName = sc.nextLine();  // 채팅방에서 사용할 이름 입력

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("command", "incoming");  //채팅방에 가입 시켜달라
            jsonObject.put("data", chatClient.chatName);
            String json = jsonObject.toString();
            chatClient.send(json);

            // 항상 받을 준비
            chatClient.receive();

                System.out.println("-----------------------------------------------------------");
                System.out.println("보낼 메시지를 입력하고 Enter");
                System.out.println("채팅을 종료하려면 q 또는 Q를 입력하고 Enter 키를 입력하세요");
                System.out.println("-----------------------------------------------------------");
            while (true){

                String message = sc.nextLine();
                if(message.toLowerCase().equals("q")){
                    break;
                } else {

                    jsonObject = new JSONObject();
                    jsonObject.put("command", "message");
                    jsonObject.put("data", message);
                    json = jsonObject.toString();
                    chatClient.send(json);
                }

            }
            sc.close();
            chatClient.unConnect(); // 채팅 끊기


        } catch (IOException e){
            System.out.println("서버 연결 안 됨 ");
        }
        System.out.println("클라이언트 종료");
    }


}
