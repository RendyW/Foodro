package com.latutslab_00000053580.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.latutslab_00000053580.foodro.Cart;

import java.util.ArrayList;

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

    public Cursor getCart(int itemID){
        Cursor cursor = null;
        String selectQuery = String.format("SELECT * FROM %S WHERE %s = %s ", db.TABLE_CART, db.CART_ITEM_ID, itemID);
        cursor = database.rawQuery(selectQuery, null);

        return cursor;
    }

    public ArrayList<Cart> getCartList(){
        ArrayList<Cart> cartArrayList = new ArrayList<Cart>();
        String query = "SELECT * FROM " + db.TABLE_CART;
        Cursor c = database.rawQuery(query, null);

        try{

            if(!c.moveToFirst()) return null;

            do{
                cartArrayList.add(new Cart(c.getInt(0), c.getInt(1), c.getString(2), c.getInt(3), c.getString(4)));
            } while(c.moveToNext());
        } finally{
            c.close();
        }

        return cartArrayList;
    }

    public int getTotal(){
        String sumQuery = String.format("SELECT SUM(%s * %s) AS Total FROM %s", db.CART_QUANTITY, db.CART_ITEM_PRICE);
        Cursor cursor = database.rawQuery(sumQuery, null);
        return cursor.getInt(0);
    }

    //using this method we can add users to user table
    public void addCart(Cart cart) {

        //create content values to insert
        ContentValues values = new ContentValues();
        int itemID = cart.getItemID();

        values.put(db.CART_ITEM_ID, itemID);
        values.put(db.CART_QUANTITY, cart.getQuantity());
        values.put(db.CART_ITEM_PRICE, cart.getPrice());
        values.put(db.CART_ITEM_NAME, cart.getName());
        values.put(db.CART_IMAGE, cart.getImage());

        Cursor cursor = getCart(itemID);

        if(cursor != null && cursor.getCount() > 0){
            String updateQuery = String.format("UPDATE %s SET " +
                    "%s = %s + %d " +
                    "WHERE %s = %s",
                    db.TABLE_CART, db.CART_QUANTITY, db.CART_QUANTITY, cart.getQuantity(), db.CART_ITEM_ID, itemID);

            database.execSQL(updateQuery);
            //database.update(db.TABLE_CART, values, String.format("%S = %s", db.CART_ITEM_ID, itemID) , null);
            return;
        }

        database.insert(db.TABLE_CART, null, values);
    }

//    public void updateCart (Cart cart){
//        ContentValues values = new ContentValues();
//
//
//        values.put(db.CART_ITEM_ID, itemID);
//        values.put(db.CART_QUANTITY, cart.getQuantity());
//        values.put(db.CART_ITEM_NAME, cart.getName());
//        values.put(db.CART_ITEM_PRICE, cart.getPrice());
//
//
//    }

    public void deleteCart (int itemID){
        String query = String.format("DELETE FROM %s WHERE %s = %d", db.TABLE_CART, db.CART_ITEM_ID, itemID);
        database.execSQL(query);
    }

}
