package com.osama.bait.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import com.osama.bait.editDelegate;
import com.osama.bait.showDelegates;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "comession";

    // Table Names
    private static final String TABLE_DELEGATES = "delegates";
    private static final String TABLE_AREA = "areas";
    private static final String TABLE_SALES = "sales";
    private static final String TABLE_DELEGATES_SALES = "delegatesSales";
    private static final String TABLE_DELEGATES_COMESSION = "delegatesComession";



    // DELEGATES Table - column nmaes
    private static final String KEY_DELEGATE_ID= "delegateId";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMAGE_URL = "imageUrl";
    private static final String KEY_MAIN_AREA = "mainArea";

    // AREA Table - column nmaes
    private static final String KEY_AREA_ID= "areaId";
    private static final String KEY_AREA_NAME = "areaName";



    // SALES Table - column nmaes
    private static final String KEY_SALES_ID  = "salesId";
    private static final String KEY_DATE      = "date";
    private static final String KEY_AMOUNT    = "amount";
    private static final String KEY_DAY       = "day";
    private static final String KEY_MONTH     = "month";
    private static final String KEY_YEAR      = "year";






    // DELEGATES_COMESSION Table - column nmaes
    private static final String KEY_DELEGETS_COMESSION_ID= "delegateComessionId";

    // DELEGATES_SALES Table - column nmaes
    private static final String KEY_DELEGETS_SALES_ID= "delegateSalesId";



    // Table Create Statements
    // Delegates table create statement
    private static final String CREATE_TABLE_DELEGATES = "CREATE TABLE " +
                    TABLE_DELEGATES + "("                     +
                    KEY_DELEGATE_ID + " INTEGER PRIMARY KEY," +
                    KEY_NUMBER      + " INTEGER,"             +
                    KEY_NAME        + " TEXT,"                +
                    KEY_IMAGE_URL   + " TEXT,"                 +
                    KEY_MAIN_AREA   + " INTEGER"              +
                                        ")";

    // AREAS table create statement
    private static final String CREATE_TABLE_AREAS = "CREATE TABLE " +
            TABLE_AREA      + "("                     +
            KEY_AREA_ID     + " INTEGER PRIMARY KEY," +
            KEY_AREA_NAME   + " INTEGER"             +
                              ")";


    // SALES table create statement
    private static final String CREATE_TABLE_SALES= "CREATE TABLE " +
              TABLE_SALES   + "("                     +
              KEY_SALES_ID  + " INTEGER PRIMARY KEY," +
              KEY_AREA_ID   + " INTEGER,"             +
              KEY_DAY       + " INTEGER,"             +
              KEY_MONTH     + " INTEGER,"             +
              KEY_YEAR      + " INTEGER,"             +
              KEY_AMOUNT    + " INTEGER"
                            + ")";


    // DELEGETS SALES table create statement
    private static final String CREATE_TABLE_DELEGETES_SALES= "CREATE TABLE " +
            TABLE_DELEGATES_SALES   + "("                     +
            KEY_DELEGETS_SALES_ID   + " INTEGER PRIMARY KEY," +
            KEY_AREA_ID             + " INTEGER,"             +
            KEY_DELEGATE_ID         + " INTEGER,"             +
            KEY_DAY                 + " INTEGER,"             +
            KEY_MONTH               + " INTEGER,"             +
            KEY_YEAR                + " INTEGER,"             +
            KEY_DATE                + " DATETIME,"            +
            KEY_AMOUNT              + " INTEGER"              +
                                     ")";

    // DELEGETS COMESSION table create statement
    private static final String CREATE_TABLE_DELEGETES_COMESSION= "CREATE TABLE " +
            TABLE_DELEGATES_COMESSION   + "("                     +
            KEY_DELEGETS_COMESSION_ID   + " INTEGER PRIMARY KEY," +
            KEY_DELEGATE_ID             + " INTEGER,"             +
            KEY_MONTH                   + " INTEGER,"             +
            KEY_YEAR                    + " INTEGER,"             +
            KEY_DATE                    + " DATETIME,"            +
            KEY_AMOUNT                  + " REAL"              +
                                          ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_DELEGATES);
        db.execSQL(CREATE_TABLE_AREAS);
        db.execSQL(CREATE_TABLE_SALES);
        db.execSQL(CREATE_TABLE_DELEGETES_SALES);
        db.execSQL(CREATE_TABLE_DELEGETES_COMESSION);


    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DELEGATES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AREA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SALES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DELEGATES_SALES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DELEGATES_COMESSION);



        // create new tables
        onCreate(db);
    }

    /////////////////////////////////////////
    ///ADD NEW USER
    ////////////////////////////////////////

    public Long insertDeleget(delegate de) {





        // insert row
        long todo_id = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(KEY_NUMBER, de.getNumber());
            values.put(KEY_NAME, de.getName());
            values.put(KEY_IMAGE_URL, de.getImageUrl());
            values.put(KEY_MAIN_AREA, de.getMainArea());

            long result = db.insert(TABLE_DELEGATES , null, values);
            if(result==-1)
                Log.i("False ","Error");
            else
                Log.i("Correct ","Correctly - "+todo_id);


        } catch (SQLException e)
        {
            Log.i("The sqlit Error",e.getMessage());

        }

        return todo_id;


    }

    /////////////////////////////////////////
    ///ADD NEW Area
    ////////////////////////////////////////

    public void insertArea(area ar) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AREA_NAME,ar.getAreaName());






        // insert row
        long todo_id = db.insert(TABLE_AREA, null, values);

    }

    /////////////////////////////////////////
    ///ADD NEW SALES
    ////////////////////////////////////////

    public void insertSales(sales sa) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put(KEY_SALES_ID,sa.getSalesId() );
        values.put(KEY_AREA_ID,sa.getAreaId() );
        values.put(KEY_DAY,sa.getDay() );
        values.put(KEY_MONTH,sa.getMonth() );
        values.put(KEY_YEAR,sa.getYear() );
        values.put(KEY_AMOUNT,sa.getAmount());

        // insert row
        long todo_id = db.insert(TABLE_SALES, null, values);

    }

    /////////////////////////////////////////
    ///ADD NEW DELEGATION SALES
    ////////////////////////////////////////

    public void insertDelegationSales(delegateSales deSale) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DELEGATE_ID,deSale.getDelegateId());
        values.put(KEY_AREA_ID,deSale.getAreaId());
        values.put(KEY_DATE,deSale.getDate());
        values.put(KEY_AMOUNT,deSale.getAmount());








        // insert row
        long todo_id = db.insert(TABLE_DELEGATES_SALES, null, values);

    }

    /////////////////////////////////////////
    ///ADD NEW DELEGATION COMESSION
    ////////////////////////////////////////

    public void insertDelegationComession(delegateComession deComession) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DELEGATE_ID,deComession.getDelegateId());
        values.put(KEY_AREA_ID,deComession.getAreaId());
        values.put(KEY_DATE,deComession.getDate());
        values.put(KEY_AMOUNT,deComession.getAmount());








        // insert row
        long todo_id = db.insert(TABLE_DELEGATES_COMESSION, null, values);

    }


    ///////////////////
    ///GET ALL AREA
    //////////////////
    public List<area> getAllToArea() {
        List<area> todos = new ArrayList<area>();
        String selectQuery = "SELECT  * FROM " + TABLE_AREA;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                area td = new area();
                td.setAreaId(c.getInt((c.getColumnIndex(KEY_AREA_ID))));
                td.setAreaName((c.getString(c.getColumnIndex(KEY_AREA_NAME))));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }
//////////////////////////////////
    ////////////////Get all delegates
    //////////////////////////////////
    public List<delegate> getAllToDelegate() {
        List<delegate> todos = new ArrayList<delegate>();
        String selectQuery = "SELECT  * FROM " + TABLE_DELEGATES;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int count = c.getCount();

        Log.i("number of Row ",count+" ");


        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                delegate td = new delegate();
                td.setDelegateId(c.getInt((c.getColumnIndex(KEY_DELEGATE_ID))));
                td.setNumber(c.getInt((c.getColumnIndex(KEY_NUMBER))));
                td.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                td.setMainArea((c.getInt(c.getColumnIndex(KEY_MAIN_AREA))));
                td.setImageUrl((c.getString(c.getColumnIndex(KEY_IMAGE_URL))));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    //////////////////////////////////
    ////////////////Get all delegates ACCORDING TO DELEGATEiD
    //////////////////////////////////
    public delegate getAllToDelegateAcordingToId(int delegateId ) {
        List<delegate> todos = new ArrayList<delegate>();
        String selectQuery = "SELECT  * FROM " + TABLE_DELEGATES+ " WHERE "
                + KEY_DELEGATE_ID + " = " + delegateId ;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int count = c.getCount();

        Log.i("number of Row ",count+" ");


        // looping through all rows and adding to list
        delegate td = new delegate();

        if (c.moveToFirst()) {
            do {
                td.setDelegateId(c.getInt((c.getColumnIndex(KEY_DELEGATE_ID))));
                td.setNumber(c.getInt((c.getColumnIndex(KEY_NUMBER))));
                td.setName((c.getString(c.getColumnIndex(KEY_NAME))));
                td.setMainArea((c.getInt(c.getColumnIndex(KEY_MAIN_AREA))));

                // adding to todo list
                todos.add(td);
            } while (c.moveToNext());
        }

        return td;
    }


    ///////////////////////
    /////// get Area Name according to id
    ////////////////////

    /*
     * get single todo
     */
    public  area getAreaName(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_AREA + " WHERE "
                + KEY_AREA_ID + " = " + id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        area td = new area();
        try{
            td.setAreaName((c.getString(c.getColumnIndex(KEY_AREA_NAME))));

        }
        catch (Exception e)
        {
            Log.i("The Exception",e.getMessage());
        }

        return td;
    }
    ///////////////////////////////////////
    ///// UPDATE DELEGATE INFO USING ID
    /////////////////////////////////////
    public void updateDelegate(int id , delegate delegate) {
        SQLiteDatabase db = this.getReadableDatabase();

        String strFilter = KEY_DELEGATE_ID+"=" + id;
        ContentValues args = new ContentValues();
        args.put(KEY_DELEGATE_ID,delegate.getDelegateId());
        args.put(KEY_NUMBER, delegate.getNumber());
        args.put(KEY_NAME, delegate.getName());
        args.put(KEY_IMAGE_URL, delegate.getImageUrl());
        args.put(KEY_MAIN_AREA, delegate.getMainArea());

        try {
            db.update(TABLE_DELEGATES, args, strFilter, null);

        }
catch (Exception e)
{}


    }

public    void deleteAllArea()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ TABLE_AREA);

    }
    public    void deleteAllDelegateSale()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ TABLE_DELEGATES_SALES);

    }
    public    void deleteAllDelegate()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ TABLE_DELEGATES);

    }
    public    void deleteAllComession()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ TABLE_DELEGATES_COMESSION);

    }

    public void deleteAllSales()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        db.execSQL("delete from "+ TABLE_SALES);
    }



    public void addColumnT()
    {
        SQLiteDatabase db = this.getReadableDatabase();


       String DATABASE_ALTER_TEAM_TO_V2 = "ALTER TABLE "
             + TABLE_DELEGATES_SALES + " ADD COLUMN " + KEY_MONTH+ " INTEGER;";

        String DATABASE_ALTER_TEAM_TO_V3 = "ALTER TABLE "
                + TABLE_DELEGATES_SALES + " ADD COLUMN " + KEY_YEAR+ " INTEGER;";

        db.execSQL(DATABASE_ALTER_TEAM_TO_V2);
        db.execSQL(DATABASE_ALTER_TEAM_TO_V3);




    }

    //////////////////////////////////////////////////////////////////
    //////////// INSERT SALES DELEGATES
    ////////////////////////////////////////////////////////////////
    public void insertSalesDelegate(sales sa,int delegateId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_AREA_ID,sa.getAreaId() );
        values.put(KEY_DELEGATE_ID,delegateId);
        values.put(KEY_DATE,sa.getDate());
        values.put(KEY_AMOUNT,sa.getAmount());
        values.put(KEY_MONTH,sa.getMonth() );
        values.put(KEY_YEAR,sa.getYear() );
        // insert row
        try {
            long todo_id = db.insert(TABLE_DELEGATES_SALES, null, values);
            Log.i("queryNoError","NO ERROR");

        } catch (SQLException e) {
            Log.i("queryError", e.getMessage());
            }


    }

    //////////////////////////////////////////////
    //////////////GET DELEGATEsALLES
    //////////////////////////////////////////////
    public List<delegateSales> getDelegateSales (int delegateId)
    {
        List<delegateSales> todos = new ArrayList<delegateSales>();
        String selectQuery = "SELECT  * FROM " + TABLE_DELEGATES_SALES+ " WHERE "
                + KEY_DELEGATE_ID + " = " + delegateId ;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int count = c.getCount();

        Log.i("number of Row ",count+" ");


        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {

                delegateSales td = new delegateSales();
                td.setDelegateSalesId(c.getInt((c.getColumnIndex(KEY_DELEGETS_SALES_ID))));
                td.setDelegateId(c.getInt((c.getColumnIndex(KEY_DELEGATE_ID))));
                td.setDate(c.getString((c.getColumnIndex(KEY_DATE))));
                td.setAmount(c.getInt((c.getColumnIndex(KEY_AMOUNT))));
                td.setMonth(c.getInt((c.getColumnIndex(KEY_MONTH))));
                td.setYear(c.getInt((c.getColumnIndex(KEY_YEAR))));
                td.setAreaId(c.getInt((c.getColumnIndex(KEY_AREA_ID))));

                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
}
       /////////////////////////////
      //////////////GET DELEGATES SALES ACCORDING ID AND MONTH AND YEAR
     ////////////////////////////////
       public  List<delegateSales> getDelegateSalesAccordingDate (int delegateId,int month , int year)
       {
           List<delegateSales> todos = new ArrayList<delegateSales>();
           String selectQuery = "SELECT  * FROM " + TABLE_DELEGATES_SALES+ " WHERE "
                   + KEY_DELEGATE_ID + " = " + delegateId +" AND "+KEY_MONTH+ " = "+month +" AND "+KEY_YEAR +" = "+year;
           Log.e(LOG, selectQuery);

           SQLiteDatabase db = this.getReadableDatabase();
           Cursor c = db.rawQuery(selectQuery, null);
           int count = c.getCount();

           Log.i("number of Row ",count+" ");


           // looping through all rows and adding to list
           if (c.moveToFirst()) {
               do {
                   delegateSales td = new delegateSales();


                   td.setDelegateSalesId(c.getInt((c.getColumnIndex(KEY_DELEGETS_SALES_ID))));
                   td.setDelegateId(c.getInt((c.getColumnIndex(KEY_DELEGATE_ID))));
                   td.setDate(c.getString((c.getColumnIndex(KEY_DATE))));
                   td.setAmount(c.getInt((c.getColumnIndex(KEY_AMOUNT))));
                   Log.i("FromData",c.getString((c.getColumnIndex(KEY_AMOUNT))));

                   td.setMonth(c.getInt((c.getColumnIndex(KEY_MONTH))));
                   td.setYear(c.getInt((c.getColumnIndex(KEY_YEAR))));
                   td.setAreaId(c.getInt((c.getColumnIndex(KEY_AREA_ID))));


                   todos.add(td);


               } while (c.moveToNext());
           }

           return todos;
       }


    ///////////////////////
    /////// GET DELEGATE INFO ACCORDING TO DELEGATEiD
    ////////////////////

    /*
     * get single todo
     */
    public  delegate getDelegateInfo(int delegateId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_DELEGATES + " WHERE "
                + KEY_DELEGATE_ID + " = " + delegateId;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        delegate td = new delegate();
        try{
            td.setNumber((c.getInt(c.getColumnIndex(KEY_NUMBER))));
            td.setName((c.getString(c.getColumnIndex(KEY_NAME))));


        }
        catch (Exception e)
        {
            Log.i("The Exception",e.getMessage());
        }

        return td;
    }

    public void addDelegateComession (int delegateId , double comession,int month , int year  , String date)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put(KEY_SALES_ID,sa.getSalesId() );
        values.put(KEY_DELEGATE_ID,delegateId );
        values.put(KEY_MONTH,month);
        values.put(KEY_YEAR,year);
        values.put(KEY_DATE,date);

        values.put(KEY_AMOUNT,comession);

        // insert row
        long todo_id = db.insert(TABLE_DELEGATES_COMESSION, null, values);
    }

    //////////////////////////////////////////////
    //////////////GET DELEGATEsALLES
    //////////////////////////////////////////////
    public delegateComession getDelegateComession (int delegateId , int month , int year  )
    {
        delegateComession td = new delegateComession();
        String selectQuery = "SELECT  * FROM " + TABLE_DELEGATES_COMESSION+ " WHERE "
                + KEY_DELEGATE_ID + " = " + delegateId + " AND "+KEY_MONTH + " = " +month + " AND "
                +KEY_YEAR +" = "+ year;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int count = c.getCount();

        Log.i("number of Row ",count+" ");


        // looping through all rows and adding to list
        if (c.moveToFirst())
        {
            do
                {
                td.setDelegateSalesId(c.getInt((c.getColumnIndex(KEY_DELEGETS_COMESSION_ID))));
                td.setDelegateId(c.getInt((c.getColumnIndex(KEY_DELEGATE_ID))));
                td.setDate(c.getString((c.getColumnIndex(KEY_DATE))));
                td.setAmount(c.getInt((c.getColumnIndex(KEY_AMOUNT))));
                td.setMonth(c.getInt((c.getColumnIndex(KEY_MONTH))));
                td.setYear(c.getInt((c.getColumnIndex(KEY_YEAR))));

               }
            while (c.moveToNext());
        }

        return td;
    }

    public int getDelegateComessionCount (int delegateId , int month , int year  )
    {
        delegateComession td = new delegateComession();
        String selectQuery = "SELECT  * FROM " + TABLE_DELEGATES_COMESSION+ " WHERE "
                + KEY_DELEGATE_ID + " = " + delegateId + " AND "+KEY_MONTH + " = " +month + " AND "
                +KEY_YEAR +" = "+ year;
        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        int count = c.getCount();
        return count ;
    }

        ////////////////////
        //////////
        ///////////////////////
        public int getDelegateSalesCount (int delegateId , int month , int year  )
        {
            delegateComession td = new delegateComession();
            String selectQuery = "SELECT  * FROM " + TABLE_DELEGATES_SALES+ " WHERE "
                    + KEY_DELEGATE_ID + " = " + delegateId + " AND "+KEY_MONTH + " = " +month + " AND "
                    +KEY_YEAR +" = "+ year;
            Log.e(LOG, selectQuery);

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery(selectQuery, null);
            int count = c.getCount();
            return count ;
        }
    public void deleteDelegateAccToId(int delegateId )
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("delete from "+ TABLE_DELEGATES +" WHERE "+KEY_DELEGATE_ID+" = "+delegateId);


    }

}
