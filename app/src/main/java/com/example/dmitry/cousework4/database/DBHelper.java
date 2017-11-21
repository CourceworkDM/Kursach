package com.example.dmitry.cousework4.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mary on 03.05.2017.
 */

public class DBHelper  extends SQLiteOpenHelper {

    private static final String LOG_TAG = DBHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "database.db";
// private static final int DATABASE_VERSION = 1;


    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_TABLE = "CREATE TABLE "+ Contract.Entry.TABLE_NAME+ " ("
                + Contract.Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.Entry.COLUMN_NAME + " TEXT NOT NULL); ";
                //+ Contract.doing.TIME + " TEXT NOT NULL, "
                //+ Contract.doing.DOING + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_TABLE);

        String SQL_CREATE_TA = "CREATE TABLE "+ Contract.Note.TABLE_NAME+ " ("
                + Contract.Note._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.Note.COLUMN_NAME + " TEXT NOT NULL); ";
        db.execSQL(SQL_CREATE_TA);

        String SQL_CREATE_T = "CREATE TABLE "+ Contract.Waste.TABLE_NAME+ " ("
                + Contract.Waste._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.Waste.COLUMN_NAME + " TEXT NOT NULL,"
                + Contract.Waste.COLUMN_NAME_1 + " TEXT NOT NULL,"
                + Contract.Waste.COLUMN_NAME_2 + " TEXT NOT NULL); ";
        db.execSQL(SQL_CREATE_T);
        String SQL_CREATE_P = "CREATE TABLE "+ Contract.Price.TABLE_NAME+ " ("
                + Contract.Price._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.Price.COLUMN_NAME + " TEXT NOT NULL,"
                + Contract.Price.COLUMN_NAME_1 + " TEXT NOT NULL); ";
        db.execSQL(SQL_CREATE_P);
        String SQL_CREATE_l = "CREATE TABLE "+ Contract.List.TABLE_NAME+ " ("
                + Contract.List._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.List.COLUMN_NAME + " TEXT NOT NULL); ";
        db.execSQL(SQL_CREATE_l);
        String SQL_CREATE_s = "CREATE TABLE "+ Contract.Space.TABLE_NAME+ " ("
                + Contract.Space._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.Space.COLUMN_NAME + " TEXT NOT NULL); ";
        db.execSQL(SQL_CREATE_s);
        String SQL_CREATE_prod = "CREATE TABLE "+ Contract.Prod.TABLE_NAME+ " ("
                + Contract.Space._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.Space.COLUMN_NAME + " TEXT NOT NULL); ";
        db.execSQL(SQL_CREATE_prod);
        String SQL_CREATE_pro = "CREATE TABLE "+ Contract.Pro.TABLE_NAME+ " ("
                + Contract.Space._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.Space.COLUMN_NAME + " TEXT NOT NULL); ";
        db.execSQL(SQL_CREATE_pro);
        String SQL_CREATE_pr = "CREATE TABLE "+ Contract.Pr.TABLE_NAME+ " ("
                + Contract.Space._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Contract.Space.COLUMN_NAME + " TEXT NOT NULL); ";
        db.execSQL(SQL_CREATE_pr);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+ Contract.Entry.TABLE_NAME);
        db.execSQL("DROP TABLE "+ Contract.Note.TABLE_NAME);
        db.execSQL("DROP TABLE "+ Contract.Waste.TABLE_NAME);
        db.execSQL("DROP TABLE "+ Contract.Price.TABLE_NAME);
        db.execSQL("DROP TABLE "+ Contract.List.TABLE_NAME);
        db.execSQL("DROP TABLE "+ Contract.Space.TABLE_NAME);
        db.execSQL("DROP TABLE "+ Contract.Prod.TABLE_NAME);
        db.execSQL("DROP TABLE "+ Contract.Pro.TABLE_NAME);
        db.execSQL("DROP TABLE "+ Contract.Pr.TABLE_NAME);
        onCreate(db);
    }

    public String[] getTimeDoing(String conditionOfChoose)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM "+ Contract.Entry.TABLE_NAME;
        if(conditionOfChoose != "") query+= " WHERE "+ conditionOfChoose+";";


        Cursor cursor = db.rawQuery(query, null);
        String[] rows = new String[cursor.getCount()];

        int indexTime = cursor.getColumnIndex(Contract.Entry.COLUMN_NAME);
     //   int indexDoing = cursor.getColumnIndex(Contract.Entry.DOING);

        int indexRows = 0;

        try
        {
            while (cursor.moveToNext())
            {
                rows[indexRows] = cursor.getString(indexTime);
                indexRows++;
            }
        }
        catch (Exception ex)
        {
        }
        finally {
            cursor.close();
        }

        return rows;
    }
    public String[] getNote(String conditionOfChoose)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM "+ Contract.Note.TABLE_NAME;
        if(conditionOfChoose != "") query+= " WHERE "+ conditionOfChoose+";";


        Cursor cursor = db.rawQuery(query, null);
        String[] rows = new String[cursor.getCount()];

        int indexTime = cursor.getColumnIndex(Contract.Note.COLUMN_NAME);
        int indexRows = 0;
        try
        {
            while (cursor.moveToNext())
            {
                rows[indexRows] = cursor.getString(indexTime);
                indexRows++;
            }
        }
        catch (Exception ex)
        {
        }
        finally {
            cursor.close();
        }
        return rows;
    }
    public String[] getCost(String conditionOfChoose) //вывод даты??
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ Contract.Waste.TABLE_NAME;
        if(conditionOfChoose != "") query+= " WHERE "+ conditionOfChoose+";";
        Cursor cursor = db.rawQuery(query, null);
        String[] rows = new String[cursor.getCount()];
        int indexTime = cursor.getColumnIndex(Contract.Waste.COLUMN_NAME);
        int indexDoing = cursor.getColumnIndex(Contract.Waste.COLUMN_NAME_1);
        int indexDo = cursor.getColumnIndex(Contract.Waste.COLUMN_NAME_2);
        int indexRows = 0;
        try
        {
            while (cursor.moveToNext())
            {
                rows[indexRows] = cursor.getString(indexTime) + " " + cursor.getString(indexDoing)
                        + " " + cursor.getString(indexDo);
                indexRows++;
            }
        }
        catch (Exception ex)
        {
        }
        finally {
            cursor.close();
        }
        return rows;
    }

    public String getPrice(String conditionOfChoose)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ Contract.Price.TABLE_NAME;
        if(conditionOfChoose != "") query+= " WHERE "+ conditionOfChoose+";";
        Cursor cursor = db.rawQuery(query, null);

        String[] rows = new String[cursor.getCount()];
        int indexDoing = cursor.getColumnIndex(Contract.Price.COLUMN_NAME_1);
       int indexRows = 0;
//        rows[indexRows] = cursor.getString(indexDoing);
      //  String row = cursor.getString(indexDoing);

       // cursor.close();
        try
       {
            while (cursor.moveToNext())
           {
                rows[indexRows] = cursor.getString(indexDoing);
                indexRows++;
            }
        }
        catch (Exception ex)
        {
        }
        finally {
            cursor.close();
        }
        String row = rows[0].toString();
        return row;
    }

    public String get_mon(String conditionOfChoose){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT Cost_mon FROM "+ Contract.Waste.TABLE_NAME;
        if(conditionOfChoose != "") query+= " WHERE "+ conditionOfChoose+";";
        Cursor cursor = db.rawQuery(query, null);
        //String[] rows = new String[cursor.getCount()];
        int mon =0;
        int indexDoing = cursor.getColumnIndex(Contract.Waste.COLUMN_NAME_1);
        int indexRows = 0;
        try
        {
            while (cursor.moveToNext())
            {
                //rows[indexRows] =  cursor.getString(indexDoing);
                mon = mon + Integer.parseInt(cursor.getString(indexDoing));
                indexRows++;
            }
        }
        catch (Exception ex)
        {
        }
        finally {
            cursor.close();
        }
        return String.valueOf(mon);
    }

    public String[] getList(String conditionOfChoose)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM "+ Contract.List.TABLE_NAME;
        if(conditionOfChoose != "") query+= " WHERE "+ conditionOfChoose+";";
        Cursor cursor = db.rawQuery(query, null);
        String[] rows = new String[cursor.getCount()];
        int indexTime = cursor.getColumnIndex(Contract.List.COLUMN_NAME);
        int indexRows = 0;

        try
        {
            while (cursor.moveToNext())
            {
                rows[indexRows] = cursor.getString(indexTime);
                indexRows++;
            }
        }
        catch (Exception ex)
        {
        }
        finally {
            cursor.close();
        }

        return rows;
    }
    public String[] getSpace(String conditionOfChoose)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM "+ Contract.Space.TABLE_NAME;
        if(conditionOfChoose != "") query+= " WHERE "+ conditionOfChoose+";";
        Cursor cursor = db.rawQuery(query, null);
        String[] rows = new String[cursor.getCount()];
        int indexTime = cursor.getColumnIndex(Contract.Space.COLUMN_NAME);
        int indexRows = 0;

        try
        {
            while (cursor.moveToNext())
            {
                rows[indexRows] = cursor.getString(indexTime);
                indexRows++;
            }
        }
        catch (Exception ex)
        {
        }
        finally {
            cursor.close();
        }

        return rows;
    }

    public String insertInDB(String date) // продукты
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Contract.Entry.TABLE_NAME +
                    " (" + Contract.Entry.COLUMN_NAME + ")" +
                    " VALUES('" + date + "');";
            db.execSQL(query);
            return "Создание завершено успешно!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }

    }

    public String insert(String date) //заметки
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Contract.Note.TABLE_NAME +
                    " (" + Contract.Note.COLUMN_NAME + ")" +
                    " VALUES('" + date + "');";
            db.execSQL(query);
            return "Создание завершено успешно!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }

    }

    public String insert_cost(String date, String money, String dat)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Contract.Waste.TABLE_NAME +
                    " (" + Contract.Waste.COLUMN_NAME + "," + Contract.Waste.COLUMN_NAME_1 + "," + Contract.Waste.COLUMN_NAME_2 +")" +
                    " VALUES('" + date + "','" + money  + "','" + dat + "');";
            db.execSQL(query);
            return "Создание завершено успешно!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }

    }
    public String insert_cost_test()
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Contract.Waste.TABLE_NAME +
                    " (" + Contract.Waste.COLUMN_NAME + "," + Contract.Waste.COLUMN_NAME_1 + "," + Contract.Waste.COLUMN_NAME_2 +")" +
                    " VALUES('" + "test" + "','" + "300"  + "','" + "2017/5/10" + "');";
            db.execSQL(query);
            return "Создание завершено успешно!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }

    }

    public String insert_price(String date, String money)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Contract.Price.TABLE_NAME +
                    " (" + Contract.Price.COLUMN_NAME + ","  + Contract.Price.COLUMN_NAME_1 +")" +
                    " VALUES('" + date + "','"  + money + "');";
            db.execSQL(query);
            return "Создание завершено успешно!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }

    }
    public String insert_list(String date) // списки
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Contract.List.TABLE_NAME +
                    " (" + Contract.List.COLUMN_NAME + ")" +
                    " VALUES('" + date + "');";
            db.execSQL(query);
            return "Создание завершено успешно!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }

    }
    public String insert_space(String date) // списки---
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "INSERT INTO " + Contract.Space.TABLE_NAME +
                    " (" + Contract.Space.COLUMN_NAME + ")" +
                    " VALUES('" + date + "');";
            db.execSQL(query);
            return "Создание завершено успешно!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }

    }


    public String deleteFromDb(String date)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Contract.Entry.TABLE_NAME +
                    " WHERE " + Contract.Entry.COLUMN_NAME + " = '" + date + "';";
            db.execSQL(query);
            return "Успешно удалено!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }

    public String delete(String date)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Contract.Note.TABLE_NAME +
                    " WHERE " + Contract.Note.COLUMN_NAME + " = '" + date + "';";
            db.execSQL(query);
            return "Успешно удалено!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }

    public String delete_cost(String date)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Contract.Waste.TABLE_NAME +
                    " WHERE " + Contract.Waste.COLUMN_NAME_2 + " = '" + date + "';";
            db.execSQL(query);
            return "Успешно удалено!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }
    public String delete_all()
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Contract.Waste.TABLE_NAME +
                    ";";
            db.execSQL(query);
            return "Успешно удалено!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }
    public String delete_list(String date)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Contract.List.TABLE_NAME +
                    " WHERE " + Contract.List.COLUMN_NAME + " = '" + date + "';";
            db.execSQL(query);
            return "Успешно удалено!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }
    public String delete_space(String date)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "DELETE FROM " + Contract.Space.TABLE_NAME +
                    " WHERE " + Contract.Space.COLUMN_NAME + " = '" + date + "';";
            db.execSQL(query);
            return "Успешно удалено!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }
    }

    public String updateInDB(String oldTime, String newTime)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            String query = "UPDATE " + Contract.Entry.TABLE_NAME + " SET " +
                    Contract.Entry.COLUMN_NAME + " = " + newTime + ", " +
                    "WHERE " + Contract.Entry.COLUMN_NAME + " = " + oldTime;
            db.execSQL(query);
            return "Успешно изменено!";
        }
        catch (Exception ex)
        {
            return ex.getMessage();
        }

    }
}



