package thisjava.collection;

import java.util.ArrayList;
import java.util.List;

public class CollectionEx1_ArrayList {
    public static void main(String[] args) {
        // ArrayList 컬렉션 생성
         List<CollectionEx1_ArrayList_Board> boardList = new ArrayList<>();

         //객체 추가
        //객체를 추가 할 때는 생성자에 대한 모든 값을 넣어 줘야 한다.
        for(int i=1; i<=5; i++) {       // 인덱스 다섯개에 하나씩 값을 넣어줌
            boardList.add(new CollectionEx1_ArrayList_Board("제목" + i, "내용" + i, "글쓴이" + i));
        }
        System.out.println(boardList.size());

        //List 담은 후 값을 하나씩 보기 위해선 다시  포문으로 값 하나씩 확인한다.
        // 여기서 index 오류가 났던 이유는 위에는 인덱스 0번 부터 값을 넣었는데  밑에서 1부터 검사 해서 오류남 
        for (int i=1; i<=5; i++){       // 0부터  5보다 작을 때 까지로 설정하면 -1 안해줘도 됨
            CollectionEx1_ArrayList_Board board = boardList.get(i-1);
            System.out.println(board);
        }
        // foreach 로 확인 할 때는  제네릭 값으로 설정된 클래스를 불러와서 비교해야 된다
        for(CollectionEx1_ArrayList_Board s : boardList){
            System.out.println(s);
        }







    }
}
