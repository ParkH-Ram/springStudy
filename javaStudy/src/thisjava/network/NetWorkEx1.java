package thisjava.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetWorkEx1 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ia = InetAddress.getLocalHost();  // 기본이 IPv4 주소 뜨는듯
        System.out.println(ia.getHostAddress()); //  ia 만 출력하면 컴퓨터 이름 하고 주소 이렇게 호출하면 주소만

        InetAddress[] iaArr = InetAddress.getAllByName("www.naver.com");
        for(InetAddress inetAddress : iaArr){
            System.out.println("inetAddress = " + inetAddress.getHostAddress());
        }
    }
}
