package com.latutslab_00000053580.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.latutslab_00000053580.foodro.Cart;

public class DbCart {

    private DatabaseHelper db;
    private Context context;
    private SQLiteDatabase database;

    public DbCart(Context context) {
        this.context = context;
    }

    public DbCart open() throws SQLException {
        db = new DatabaseHelper(context);
        database = db.getWritableDatabase();
        return this;
    }

    public void close(){
        db.close();
    }


    //using this method we can add users to user table
    public void addCart(Cart cart) {

        //create content values to insert
        ContentValues values = new ContentValues();

        values.put(db.CART_ITEM_ID, cart.getItemID());
        values.put(db.CART_QUANTITY, cart.getQuantity());

        database.insert(db.TABLE_CART, null, values);
    }

    public void updateCart (Cart cart){
        ContentValues values = new ContentValues();

        int itemID = cart.getItemID();

        values.put(db.CART_ITEM_ID, itemID);
        values.put(db.CART_QUANTITY, cart.getQuantity());

        database.update(db.TABLE_CART, values, db.CART_ITEM_ID + "=" + itemID , null);
    }

    public void deleteCart (int itemID){
        String query = String.format("DELETE FROM %s WHERE %s = %d", db.TABLE_CART, db.CART_ITEM_ID, itemID);
        database.execSQL(query);
    }

}
