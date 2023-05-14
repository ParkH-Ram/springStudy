package boardconent;


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardContent {
    int boardNum;
    String title;
    String content;
    Scanner sc;

    public BoardContent(){

    }

    public BoardContent(int boardNum, String title, String content){
        this.boardNum = boardNum;
        this.title = title;
        this.content = content;
    }

    public BoardContent updateBoard(BoardContent boardContent){
        Scanner sc = new Scanner(System.in);
        if(this.boardNum == boardContent.boardNum) {
            System.out.print("새로운 제목을 입력하세요: ");
            String newTitle = sc.nextLine();
            System.out.print("새로운 내용을 입력하세요: ");
            String newContent = sc.nextLine();
            boardContent.title = newTitle;
            boardContent.content = newContent;
            System.out.println("게시물이 수정되었습니다.");
        }
        else{
            System.out.println("게시물 번호가 일치하지 않습니다.");
        }
        return boardContent;
    }

    @Override
    public String toString() {
        return "BoardContent{" +
            "boardNum=" + boardNum +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            '}';
    }

    public static void main(String[] args) {
        BoardContent bc = new BoardContent();
        List<BoardContent> boardList = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        
        //게시물 5개 생성   1~5 번
        for (int i=1; i<=5; i++) {
            boardList.add(new BoardContent(0+i, "제목" + i, "내용" + i));
        }

        // 잘 입력 되었는지 확인
        for (BoardContent boardContent : boardList){
            System.out.println(boardContent.toString());
        }
    
        //게시물 수정 // 번호를 입력해서 제목 내용 바꾸기
        System.out.println("수정할 게시물 번호 입력");
        int num = sc.nextInt();
        for(BoardContent boardContent : boardList){
            if(boardContent.boardNum == num){
                boardContent.updateBoard(boardContent);
            }
        }

        // 수정이 잘 반영 되었는지 확인
        for (BoardContent boardContent : boardList){
            System.out.println(boardContent.toString());
        }




    }





}



