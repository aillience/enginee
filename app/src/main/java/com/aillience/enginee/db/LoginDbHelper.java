package com.aillience.enginee.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Happy every day.
 * Created by yfl on 2017/9/11 0011
 * explain: 登录用户的数据库
 */

public class LoginDbHelper extends MyDbHelper {
    private String tableName;

    public LoginDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.tableName = name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        createTable();
    }

    private void createTable() {
        String sql = "create table " + tableName + "(" +
                "Id int ," +
                "Name varchar(20)"
                + ")";
        execSql(sql);
    }

    public Cursor select() {
        String sql = "select * from " + tableName;
        return runSql(sql);
    }

    public boolean insert(String cId, String cName) {
        String sql = "INSERT INTO " + tableName + " VALUES (" + cId + "," + cName + ")";
        return execSql(sql);
    }
}
