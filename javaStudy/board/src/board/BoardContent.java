package board;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public  class BoardContent extends  Boards {
    private Scanner sc;
    private List<String[]> boardList;
    private int seq;

    public BoardContent() {  // 생성자
        this.sc = new Scanner(System.in);
        this.boardList = new ArrayList<>();
        this.seq = 1;   // 게시물 번호
    }

    public void createBoard() {
        System.out.println("게시물 등록");

        String[] createBoard = new String[3];
        createBoard[0] = String.valueOf(seq);   //  게시물 번호 추가

        System.out.println(createBoard[0] + "의 제목 입력");
        sc.nextLine();  // 버퍼에 남아 있는 엔터키 소모  //  "\n" 이 버퍼에 남아 있어 소모하기 위해 작성
        createBoard[1] = sc.nextLine();  // 게시물 제목 입력

        System.out.println(createBoard[0] + " 의 내용 입력");
        createBoard[2] = sc.nextLine(); // 게시물 내용 입력

        this.boardList.add(createBoard);   // ArrayList에 입력된 값들을 저장

        System.out.println("게시물 등록 완료");
        for (String[] boardarr : boardList) {
            System.out.println(boardList.toString());
        }
        System.out.println("게시판 목록 수" + boardList.size());

        seq++;    // 게시물 등록 후 게시물 번호 증가
    }

    public List<String[]> getBoardList() {
        return boardList;
    }

    public boolean boardBoolean() {  // 게시물 존재 여부 확인
        if (this.boardList.isEmpty()) {  // 게시물  존재 하지 않으면 ( 비어 있으면 )
            System.out.println("게시물이 없습니다.");
            return false;   // 게시물이 없으면 false 리턴

        }
        return true;      // 게시물이 존재하면 true 리턴
    }

    public void boardSearch() {   // 게시물 조회
        if (boardBoolean()) {  // 게시글이 존재하면
            System.out.println("게시글 번호 입력");

            String searchInt = sc.next();  // 조회할 번호 입력
            boolean searchBoolean = false; // 조회 여부

            for (String[] boardArr : this.boardList) {
                if (boardArr[0].equals(searchInt)) { // 입력 값과 같은 게시글 번호 일 때
                    System.out.println(boardArr[0] + " 번의  값은 ");
                    System.out.println(" 게시글 제목 : " + boardArr[1] + " \n 게시글 내용 : " + boardArr[2]);
                    searchBoolean = true;    // 조회 성공
                    break;
                }
            }
        }
    }



    public void deleteBoard() {
        if (boardBoolean()) {
            System.out.println("삭제할 게시물을 입력해 주세요");

            String searchBoard = sc.next();  // 삭제할 번호 조회
            boolean searchBoolean = false;  // 조회 여부

            for (String[] boardArr : this.boardList) {

                if (boardArr[0].equals(searchBoard)) {  //삭제할 번호랑 같으면
                    System.out.println(boardArr[0] + " 번 게시글을 삭제합니다. 삭제 된 게시글의 값은 다음과 같습니다.");
                    System.out.println(" 게시글 제목 : " + boardArr[1] + "게시글 내용 : " + boardArr[2]);
                    this.boardList.remove(boardArr); // 삭제 실행
                    searchBoolean = true; // 조회 성공
                    break;
                }
            }
        }
    }

    public void boardUpdate() {
        if (boardBoolean()) {
            System.out.println(" 업데이트할 게시판 번호를 입력 하시오.");
            String searchBoard = sc.next();  // 업데이트 할 번호 조회
            boolean searchBoolean = false;  // 조회 여부

            for (String[] boardArr : this.boardList) {
                if (boardArr[0].equals(searchBoard)) {  //삭제할 번호랑 같으면
                    System.out.println(boardArr[0] + "번" + " 게시글 제목 : " + boardArr[1] + " \n 게시글 내용 : " + boardArr[2]);

                    System.out.println("수정 시작");

                    System.out.println(boardArr[0] + "번 게시판의  수정할 제목을 입력 하세요 ");
                    sc.nextLine();
                    boardArr[1] = sc.nextLine();
                    System.out.println(boardArr[0] + "번 게시판의  수정할 내용을 입력 하세요 ");
                    boardArr[2] = sc.nextLine();

                    System.out.println("게시글이 수정 되었습니다.");

                    searchBoolean = true;
                    break;

                }


            }
        }
    }



    // 게시판 전체 조회
    public void boardListSearch(){
        System.out.println("게시판 전체 조회");
        if(boardBoolean()){  // 게시물 존재 true
            for(String[] searchArray : boardList){
                System.out.println(searchArray[0] + " 번에 등록된 게시물은 ");
                System.out.println("제목 : " + searchArray[1] + " \t내용 : " + searchArray[2]);
            }
            System.out.println();


        }

    }


    public String inputNum() {
        return this.sc.next().toLowerCase();
    }


}










