package com.aillience.enginee.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

/**
 * Happy every day.
 * Created by yfl on 2017/9/11 0011
 * explain: 自定义数据库类
 * 使用示例
 1、新建库和表 不建议直接使用，可以根据需求自定义一个子类
 MyDbHelper db = new MyDbHelper(this, "login.db", null, 3);
 db.execSql("create table user(id int, name varchar(20))");
 2、读取数据表
 Cursor cursor = db.runSql("select * from user");
 if (cursor == null) return;
 while(cursor.moveToNext()){
 String fieldValue = cursor.getString(cursor.getColumnIndexOrThrow("name"));
 ...
 }
 cursor.close();

 3、增删改
 for(int i=0; i<100; i++){
 String sql = "Insert into user(id, name) values("+i+",'name"+i+"')";
 r = db.execSql(sql);
 }
 r=db.execSql("delete from user where id='1'");
 r=db.execSql("update from user set name='xxx' where id='1'");
 */

@SuppressWarnings({"JavaDoc","unused"})
public class MyDbHelper extends SQLiteOpenHelper{
    private SQLiteDatabase _db;
    private String TAG="DBHelper Log";
    public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    /**
     * 数据库存在则不执行
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,"MyDbHelper onCreate");
    }

    /**
     *
     * @param db
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.i(TAG,"MyDbHelper onOpen"+db);
        _db=db;
    }

    /**
     * 更新，在创建数据库时不会执行，在增大版本号升级的时候会执行
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG,"MyDbHelper onUpgrade");
    }
    protected SQLiteDatabase getConnect(){
        synchronized(this){
            if (_db == null ||!_db.isOpen()){
                _db=this.getWritableDatabase();
            }
            return _db;
        }
    }
    /**
     * 防止数据库对象为null
     */
    private void initDb(){
        synchronized (this){
            if (_db==null ||!_db.isOpen() ||_db.isReadOnly()){
                _db = null;
                _db = this.getWritableDatabase();
            }
        }
    }

    /**
     * 判断表中是否有此columnName
     * @param tableName
     * @param columnName
     * @return
     */
    public boolean checkColumnExists(String tableName, String columnName) {
        boolean result = false ;
        Cursor cursor = null ;
        try{
            initDb();
            cursor = _db.rawQuery( "select * from sqlite_master where name = ? and sql like ?"
                    , new String[]{tableName , "%" + columnName + "%"} );
            result = (null != cursor) && (cursor.moveToFirst()) ;
        }catch (Exception e){
            Log.e(TAG, Log.getStackTraceString(e));
        }finally{
            if(null != cursor && !cursor.isClosed()){
                cursor.close() ;
            }
        }
        return result ;
    }
    /**
     * 执行写入的SQL语句，不会自动关闭数据库连接，调用方需要手动close();
     * */
    public boolean execSql(String sql){
        try{
            if (sql.charAt(sql.length()-1) != ';'){
                sql = sql + ";";
            }
            synchronized (this){
                if (_db==null ||
                        !_db.isOpen() ||
                        _db.isReadOnly()){
                    _db = null;
                    _db = this.getWritableDatabase();
                }

                if (_db == null){
                    return false;
                }
                _db.execSQL(sql);
                //_db.close();
                return true;
            }

        }catch(Exception e){
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return false;
    }

    /**
     * 执行写入的SQL语句，不会自动关闭数据库连接，调用方需要手动close();
     * */
    public boolean execSql(String sql, Object[] bindArgs){
        try{
            if (sql.charAt(sql.length()-1) != ';'){
                sql = sql + ";";
            }
            synchronized (this){
                if (_db==null ||
                        !_db.isOpen() ||
                        _db.isReadOnly()){
                    _db = null;
                    _db = this.getWritableDatabase();
                }

                if (_db == null) {
                    return false;
                }
                _db.execSQL(sql, bindArgs);
                //_db.close();
                return true;
            }

        }catch(Exception e){
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return false;
    }
    /**
     * 执行有返回数据集的SQL语句，不会自动关闭连接，调用方需要手动close();
     * */
    public Cursor runSql(String sql){
        try{
            if (sql.charAt(sql.length()-1) != ';'){
                sql = sql + ";";
            }

            synchronized(this){
                if (_db==null || !_db.isOpen()){
                    _db = null;
                    _db = this.getReadableDatabase();
                }
            }
            Cursor cursor;
            if (_db == null) {
                return null;
            }
            cursor = _db.rawQuery(sql, null);
            return cursor;
        }catch(Exception e){
            Log.e(TAG, Log.getStackTraceString(e));
        }
        return null;
    }
    /**
     * 批量插入多条数据,例子，具体需要看情况
     */
    private boolean insertManyData(String tableName,ArrayList<String> dList){
        String sql = "INSERT INTO "+tableName+" (id, name) VALUES (?, ?)";
        try{
            initDb();
            _db.beginTransaction();
            SQLiteStatement stmt = _db.compileStatement(sql);
            for (int i = 0; i < dList.size(); i++) {
                stmt.bindLong(1, i);
                stmt.bindString(2, dList.get(i));
                stmt.execute();
                stmt.clearBindings();
            }
            _db.setTransactionSuccessful();
            _db.endTransaction();
        }catch (Exception e){
            Log.e(TAG, Log.getStackTraceString(e));
        }finally {
            _db.close();
        }
        return true;
    }
}
