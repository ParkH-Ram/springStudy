package com.first.todolist2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;
    private static final String DB_NAME = "ramDB";


    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null , DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // 데이터 베이스가 생성될 때 호출

        // 데이터베이스 -> 테이블 -> 컬럼 -> 값
        db.execSQL("CREATE TABLE IF NOT EXISTS TodoList(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "title TEXT NOT NULL, content TEXT NOT NULL, writeDate TEXT NOT NULL)");  //db가 존재하면 또 만들 필요 없음

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersions, int newVersions) {
        onCreate(db);
    }

    // select ( 조회 )
    public ArrayList<TodoItem> getTodoList(){
        ArrayList<TodoItem> todoItems = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TodoList ORDER BY writeDate DESC", null);

        // 조회 했을 때 데이터가 있을 때 내부 수행
        if(cursor.getCount() != 0){
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String writeDate = cursor.getString(cursor.getColumnIndex("writeDate"));

                TodoItem todoItem = new TodoItem();
                todoItem.setId(id);
                todoItem.setTitle(title);
                todoItem.setContent(content);
                todoItem.setWriteDate(writeDate);
                todoItems.add(todoItem) ;
            }
        }
        cursor.close();
        return todoItems;

    }


    // insert 문 ( 삽입 )
    public void insertTodo(String title, String content, String writeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO TodoList (title, content, writeDate) " +
            "VALUES('" + title +"', '" + content +"', '" + writeDate +"'); ");
    }

    // update 문 ( 수정 )
    public void updateTodo(String title, String content, String writeDate, String beforeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("UPDATE TodoList SET title='" + title +"', content='" + content +"', writeDate='" + writeDate +"' WHERE writeDate='"+ beforeDate +"'");
    }

    // delete 문 ( 삭제 )
    public void deleteTodo(String beforeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM TodoList WHERE writeDate = '" + beforeDate + "'");
    }
}
