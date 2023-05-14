package boardconent;

public class BoardWriter {
    public static void main(String[] args) {
        BoardContent boardContent = new BoardContent(1, "게시판 제목", "게시판 내용");
        System.out.println(boardContent.toString());

        BoardContent board1 = new BoardContent();

        boardContent.setBoardNum(5);
        boardContent.setTitle("이게 제목 ");
        boardContent.setContent("이게 제목");
        System.out.println(boardContent.toString());



    }
}
