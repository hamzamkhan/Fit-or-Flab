package com.example.hp.project;

/**
 * Created by hamza on 11/28/2017.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Goal.db";
    public static final String TABLE_NAME = "Goal_Table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "GoalPerDay";
    public static final String COL_3 = "GoalPerWeek";
    public static final String COL_4 = "Weight";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,GoalPerDay TEXT,GoalPerWeek TEXT,Weight TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String goalday,String goalweek,String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,goalday);
        contentValues.put(COL_3,goalweek);
        contentValues.put(COL_4,weight);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

//    public boolean updateData(String id,String name,String surname,String marks) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_1,id);
//        contentValues.put(COL_2,name);
//        contentValues.put(COL_3,surname);
//        contentValues.put(COL_4,marks);
//        db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
//        return true;
//    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
    public String getGoal()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String goalQuery = "SELECT GoalPerDay,GoalPerWeek,Weight FROM "+TABLE_NAME;
        Cursor data = db.rawQuery(goalQuery,null);
        StringBuffer buffer = new StringBuffer();
        while(data.moveToNext())
        {
            int index1 = data.getColumnIndex(COL_2);
            int index2 = data.getColumnIndex(COL_3);
            int index3 = data.getColumnIndex(COL_4);
            String weight = data.getString(index3);
            String day = data.getString(index1);
            String week = data.getString(index2);
            buffer.append("Target Weight : "+weight+"\n"+
                    "Goal Per Day : "+day+"\n"+
                    "Goal Per Week :"+week);
        }
        return buffer.toString();
    }

    public int getCount(String table)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor mCount = db.rawQuery("SELECT * FROM  "+table+"", null);
            mCount.moveToFirst();
            int count = mCount.getCount();
            mCount.close();
            return count;
        }
        catch(SQLException e){
            return -1;
        }

    }

    public void updateGoal(String goalday,String goalweek,String weight)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        ContentValues gValues = new ContentValues();
        gValues.put(COL_2,goalday);
        gValues.put(COL_3,goalweek);
        gValues.put(COL_4,weight);
        long result = db.insert(TABLE_NAME,null ,gValues);
    }
}
