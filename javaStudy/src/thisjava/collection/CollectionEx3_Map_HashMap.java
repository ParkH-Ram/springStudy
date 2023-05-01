package thisjava.collection;

import java.security.Key;
import java.util.*;

public class CollectionEx3_Map_HashMap {
    public static void main(String[] args) {
        //Map 컬렉션 생성
        Map<String, Integer> map = new HashMap<>();     // String이 Key Integer가 Value,  제네릭에서 타입 파라미터 값은 무조건 참조형 타입 기본 타입은 들어갈 수 없다.

        //객체 저장
        map.put("홍지로", 15);
        map.put("홍금보", 45);
        map.put("호라이", 35);
        map.put("호라이", 55);     // Key 가 같기 때문에 55만 저장
        map.put("홍지민", 33);
        System.out.println("총 Entry 수 : " + map.size());
        System.out.println();

        //Key 로 Value 얻기
        String key = "호라이";
        int value = map.get(key); // 키를 매개값으로 주면 값을 리턴
        System.out.println(key + " : " + map.get("호라이"));
        System.out.println(value);
        System.out.println();

        //키 Set 컬렉션을 얻고, 반복해서 키 값을 얻기
        Set<String> keySet = map.keySet(); // 키를 반복하기 위해 반복자를 얻음
        Iterator<String> keyIterator = keySet.iterator();
        while(keyIterator.hasNext()){  // 다음 키가  있느냐?
            String k = keyIterator.next(); // 다음 값을 불러오고
            Integer v = map.get(key);  // key 의 값들을 v에 저장
            System.out.println(k + " : " + v);
        }
        System.out.println();

        //엔트리 Set 컬렉션을 얻고, 반복해서 키와 값을 얻기
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, Integer>>entryIterator = entrySet.iterator(); //엔트리를 반복하기 위해 반복자를 얻음


        System.out.println("Entry 실습");
        while (entryIterator.hasNext()){
            Map.Entry<String, Integer> entry = entryIterator.next();
            String k = entry.getKey();
            Integer v = entry.getValue();

            System.out.println(k + " : " + v);

        }
        System.out.println();

        //key로 엔트리 삭제
        map.remove("홍금보");
        Set<Map.Entry<String, Integer>> entrySe1  = map.entrySet();
        Iterator<Map.Entry<String, Integer>>entryIterator1 = entrySe1.iterator(); //엔트리를 반복하기 위해 반복자를 얻음
        System.out.println("홍금보 삭제 후 ");
        while(entryIterator1.hasNext()){
            Map.Entry<String, Integer> entry = entryIterator1.next(); // 하나씩 가져온 값들을 entry 타입 객체에 하나씩 대입하겠다.
            String k = entry.getKey();
            Integer v= entry.getValue();
            System.out.println("k = " + k + " : v = " + v);

        }

        System.out.println("엔트리 수 : " + map.size());
    }
}
