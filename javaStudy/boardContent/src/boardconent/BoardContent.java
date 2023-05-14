package boardconent;


import java.util.Scanner;

public class BoardContent {
    Scanner sc = new Scanner(System.in);

    int boardNum;
    String title;
    String content;

    public BoardContent(){

    }

    public BoardContent(int boardNum, String title, String content){
        this.boardNum = boardNum;
        this.title = title;
        this.content = content;
    }




    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }











    @Override
    public String toString() {
        return "BoardContent{" +
            "boardNum=" + boardNum +
            ", title='" + title + '\'' +
            ", content='" + content + '\'' +
            '}';
    }



}
