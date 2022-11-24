package com.latutslab_00000053580.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.latutslab_00000053580.foodro.User;

public class DbUser {

    private DatabaseHelper db;
    private Context context;
    private SQLiteDatabase database;

    public DbUser(Context context) {
        this.context = context;
    }

    public DbUser open() throws SQLException {
        db = new DatabaseHelper(context);
        database = db.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }

    //using this method we can add users to user table
    public void addUser(User user) {

        //create content values to insert
        ContentValues values = new ContentValues();

        values.put(db.USER_ID, user.getUser_id());
        values.put(db.USER_NAME, user.getFirstname());
        database.insert(db.TABLE_USERS, null, values);
    }

    //Check whether account existed or not
    public User Authenticate() {

        String sql = "SELECT EXISTS (SELECT * FROM '" + db.TABLE_USERS + "' LIMIT 1)";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();

        // cursor.getInt(0) is 1 if column with value exists
        if (cursor.getInt(0) == 1) {
            cursor.close();
//            User user = new User(cursor.getString(0), cursor.getInt(1));
            return null;
        } else {
            cursor.close();
            return null;
        }
    }

    public Cursor getUser(){
        Cursor cursor = this.database.query(db.TABLE_USERS, new String[]{db.USER_ID, db.USER_NAME}, null, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public void logout(){
        String sql = "DELETE FROM " + db.TABLE_USERS;
        database.execSQL(sql);
    }
}
