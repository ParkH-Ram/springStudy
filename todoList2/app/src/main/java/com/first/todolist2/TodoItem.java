package com.first.todolist2;


public class TodoItem {

    private int id;             // 고유 아이디

    private String title;       // 제목

    private String content;      // 할일 내용

    private String writeDate;   // 작성 날짜

    public TodoItem(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }
}
