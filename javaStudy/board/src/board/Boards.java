package board;

public class Boards{


    public void boardStart() {
        BoardContent boardContent = new BoardContent();
        while (true) {
            System.out.println("----------------------------------------------------------------------");
            System.out.println("1.게시물 등록  2. 게시물 조회  3. 게시물 삭제 4. 게시물 수정 5. 게시물 전체 조회  Q. 종료");
            System.out.println("----------------------------------------------------------------------");

            String choice = boardContent.inputNum();

            if (choice.equals("1")) { //등록
                boardContent.createBoard();

            } else if (choice.equals("2")) {
                boardContent.boardSearch();

            } else if (choice.equals("3")) {
                boardContent.deleteBoard();

            } else if (choice.equals("4")) {
                boardContent.boardUpdate();

            }else if(choice.equals("5")){
                boardContent.boardListSearch();

            } else if (choice.equals("q")) {
                System.out.println("시스템 종료");
                break;

            } else {
                continue;
            }
        }
    }


}
