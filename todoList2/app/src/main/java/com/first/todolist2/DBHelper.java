package com.first.todolist2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

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

    // INSERT 문
    public void insertTodo(String title, String content, String writeDate){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO TodoList (title, content, writeDate) VALUES()");
    }
}
