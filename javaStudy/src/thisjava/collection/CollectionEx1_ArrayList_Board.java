package thisjava.collection;

//ArrayList의 객체 추가, 검색, 삭제 방법

public class CollectionEx1_ArrayList_Board {
    //필드 선언
    private String subject;
    private String content;
    private String writer;



    //생성자
    public CollectionEx1_ArrayList_Board(String subject, String content, String writer) {
        this.subject = subject;
        this.content = content;
        this.writer = writer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "CollectionEx1_ArrayList_Board{" +
            "subject='" + subject + '\'' +
            ", content='" + content + '\'' +
            ", writer='" + writer + '\'' +
            '}';
    }
}
