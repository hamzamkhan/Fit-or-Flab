package com.example.hp.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.google.android.gms.fitness.data.DataPoint;
import com.jjoe64.graphview.series.DataPoint;

import java.math.BigInteger;
import java.nio.Buffer;


public class databasehelper_ extends SQLiteOpenHelper {


    public static final String dbName = "Fit Or Flab.db";
    public static final String tableName = "diary_table";
    public static final String foodTableName = "food_table";
    public static final String userTable = "user_table";
    public static final String goalTableName = "Goal";
    public static final String goal_Table = "goalTable";

    public static final String COL1 = "ID";
    public static final String COL2 = "SPECIALIST";
    public static final String COL3 = "AGE";
    public static final String COL4 = "FOODNAME";

    public String KEY_ID = "_id";
    public String KEY_USER = "USER";
    public String KEY_NAME = "NAME";
    public String KEY_CALORIES = "CALORIES";





    public databasehelper_(Context context) {
        super(context, dbName, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + tableName + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, USER TEXT, DATE DATE, FOOD INTEGER, FOREIGN KEY (FOOD) REFERENCES food_table(_id))");
        db.execSQL("create table if not exists " + foodTableName + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, USER TEXT, NAME TEXT, FOODID TEXT, CALORIES TEXT, PROTEIN TEXT, CARBOHYDRATE TEXT, SUGARS TEXT, FAT TEXT, SATURATES TEXT, FIBRE TEXT, SALT TEXT)");
        db.execSQL("create table if not exists " + userTable + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)");
        db.execSQL("create table if not exists " + goal_Table + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,WEIGHT INTEGER,CAL_DAY INTEGER,CAL_WEEK INTEGER)");
        db.execSQL("create table if not exists " + goalTableName + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, Goal TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.execSQL("DROP TABLE IF EXISTS " + foodTableName);
        db.execSQL("DROP TABLE IF EXISTS " + goal_Table);
        db.execSQL("create table " + tableName + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, USER TEXT, DATE DATE, FOOD INTEGER, FOREIGN KEY (FOOD) REFERENCES food_table(_id))");
        db.execSQL("create table " + foodTableName + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, USER TEXT, NAME TEXT, FOODID TEXT, CALORIES TEXT, PROTEIN TEXT, CARBOHYDRATE TEXT, SUGARS TEXT, FAT TEXT, SATURATES TEXT, FIBRE TEXT, SALT TEXT)");
        db.execSQL("create table " + userTable + " (ID INTEGER AUTOINCREMENT PRIMARY KEY,USERNAME TEXT,PASSWORD TEXT)");

    }

    public boolean addFood(String user, String name, String foodID, String calories, String protein, String carbohydrate, String sugars, String fat, String saturates, String fibre, String salt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER", user);
        contentValues.put("NAME", name);
        contentValues.put("FOODID", foodID);
        contentValues.put("CALORIES", calories);
        contentValues.put("PROTEIN", protein);
        contentValues.put("CARBOHYDRATE", carbohydrate);
        contentValues.put("SUGARS", sugars);
        contentValues.put("FAT", fat);
        contentValues.put("SATURATES", saturates);
        contentValues.put("FIBRE", fibre);
        contentValues.put("SALT", salt);
        long result = db.insert(foodTableName, null, contentValues);
        return result != -1;
    }

    public boolean addItem(String user, String date, int food){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USER", user);
        contentValues.put("DATE", date);
        contentValues.put("FOOD", food);
        long result = db.insert (tableName, null, contentValues);
        return result != -1;
    }



    public boolean AddGoal(String goal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("GOAL", goal);
        long result = db.insert (goalTableName, null, contentValues);
        return result != -1;
    }


    public void insertEntry(String userName,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues newValues = new ContentValues();
        newValues.put("USERNAME", userName);
        newValues.put("PASSWORD",password);
        db.insert(userTable, null, newValues);
    }

    public Cursor getRecent(){
        String[] colNames = new String[]{KEY_ID, KEY_USER, KEY_NAME, KEY_CALORIES};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.query(true, foodTableName, colNames, null, null, null, null, KEY_ID + " Desc", "10");
        if (result != null){
            result.moveToFirst();
        }
        return result;
    }
    public Cursor getEmail()
    {
        //  String sql = "SELECT Username FROM " + Login+"");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT USERNAME FROM " + userTable + "", null);
        //Cursor result = db.query(true, tableName, colNames, null, null, null, null, KEY_ID + " Desc", null);
        if (result != null){
            result.moveToFirst();
        }
        return result;
    }
    public databasehelper_ open() throws SQLException
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return this;
    }

    public void close()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.close();
    }

    public int count(String table)
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

    public void updateEntry(String userName,String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put("USERNAME", userName);
        updatedValues.put("PASSWORD",password);
        String where="USERNAME = ?";
        db.update(userTable,updatedValues, where, new String[]{userName});
    }

    public void UpdateDB(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.execSQL("DROP TABLE IF EXISTS " + foodTableName);
        db.execSQL("create table " + tableName + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, USER TEXT, DATE DATE, FOOD INTEGER, FOREIGN KEY (FOOD) REFERENCES food_table(_id))");
        db.execSQL("create table " + foodTableName + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, USER TEXT, NAME TEXT, FOODID TEXT, CALORIES TEXT, PROTEIN TEXT, CARBOHYDRATE TEXT, SUGARS TEXT, FAT TEXT, SATURATES TEXT, FIBRE TEXT, SALT TEXT)");

    }

    public Cursor getDiary(String date, String email){
        String sql = "SELECT " + tableName +  "._id, NAME, CALORIES FROM " + tableName + " INNER JOIN " + foodTableName + " ON " + tableName + ".FOOD = " + foodTableName + "._id where DATE = '" + date + "' AND " + tableName + ".USER = '" + email + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(sql,null);
    //    Cursor result = db.query(true, tableName, colNames, null, null, null, null, KEY_ID + " Desc", null);
        if (result != null){
            result.moveToFirst();
        }
        return result;
    }

public DataPoint[] getCalories()
{

    String columns[]= {"CALORIES", "FOODID"};
    SQLiteDatabase db = this.getWritableDatabase();

    Cursor result = db.query(true, foodTableName, columns, null, null, null, null, null, null);
    DataPoint dp[]= new DataPoint[result.getCount()];
    for (int i=0; i< result.getCount(); i++) {
        result.moveToFirst();
        dp[i] = new DataPoint(result.getInt(0), result.getInt(0));

    }
    return dp;

}

    public Cursor getChildFood(String date, String email){
        String sql = "SELECT " + tableName +  "._id, NAME, CALORIES FROM " + tableName + " INNER JOIN " + foodTableName + " ON " + tableName + ".FOOD = " + foodTableName + "._id where DATE = '" + date + "' AND " + tableName + ".USER = '" + email + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery(sql,null);
        //Cursor result = db.query(true, tableName, colNames, null, null, null, null, KEY_ID + " Desc", null);
        if (result != null){
            result.moveToFirst();
        }
        return result;
    }

    public int getID(String databaseNumber){
        SQLiteDatabase db = this.getWritableDatabase();
        int foodID = 0;
        Cursor cursor =  db.rawQuery("SELECT _id from " + foodTableName + " WHERE FOODID = '" + databaseNumber + "'", null);
        while (cursor.moveToNext()) {
            foodID = cursor.getInt(0);
        }
        return foodID;
    }

    public float[] getTotals(String date, String user){
        float calTotal = 0;
        float proTotal = 0;
        float carTotal = 0;
        float sugTotal = 0;
        float fatTotal = 0;
        float satTotal = 0;
        float fibTotal = 0;
        float salTotal = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor total = db.rawQuery("SELECT CALORIES, PROTEIN, CARBOHYDRATE, SUGARS, FAT, SATURATES, FIBRE, SALT FROM " + tableName + " INNER JOIN " + foodTableName + " ON " + tableName + ".FOOD = " + foodTableName + "._id where DATE = '" + date + "' AND " + tableName + ".USER = '" + user + "'", null);
        if (total.moveToFirst()){
            do{
                calTotal = calTotal + Float.valueOf(total.getString(total.getColumnIndex("CALORIES")));
                proTotal = proTotal + Float.valueOf(total.getString(total.getColumnIndex("PROTEIN")));
                carTotal = carTotal + Float.valueOf(total.getString(total.getColumnIndex("CARBOHYDRATE")));
                sugTotal = sugTotal + Float.valueOf(total.getString(total.getColumnIndex("SUGARS")));
                fatTotal = fatTotal + Float.valueOf(total.getString(total.getColumnIndex("FAT")));
                satTotal = satTotal + Float.valueOf(total.getString(total.getColumnIndex("SATURATES")));
                fibTotal = fibTotal + Float.valueOf(total.getString(total.getColumnIndex("FIBRE")));
                salTotal = salTotal + Float.valueOf(total.getString(total.getColumnIndex("SALT")));
            }while(total.moveToNext());
        }
        total.close();

        float[] totalList = new float[]{calTotal, proTotal, carTotal, sugTotal, fatTotal, satTotal, fibTotal, salTotal};
        return totalList;
    }


    /*** Specialist Categories Addition ***/


//    public boolean addSSpecialist(int _ID,String name,int phone, String hosp)
//    {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues sValues = new ContentValues();
//        sValues.put();
//    }








    //public Cursor getChildFood()
    //{

    //}





    public String displayFood(int age)
    {
        SQLiteDatabase db = this.getWritableDatabase();
//        String[] columns = new String[]{COL4};

        String a = String.valueOf(age);
        String[] whereArgs = new String[] {a};
        String food;
//        String whereclause = "AGE = ?";
//
//        Cursor cursor = db.query(childFoodTable, columns,whereclause,whereArgs,null,null,null);
        String queryString = "SELECT NAME FROM child_Food WHERE AGE = ?";
        Cursor data = db.rawQuery(queryString,whereArgs);
        StringBuffer buffer = new StringBuffer();
        while(data.moveToNext()){
            int index1=data.getColumnIndex("NAME");
            food = data.getString(index1);
            buffer.append(food+"\n\n");
        }


        return buffer.toString();
    }





    public String getSinlgeEntry(String USERNAME)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT PASSWORD FROM "+userTable+"",null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
        cursor.close();
        return password;
    }









    public String get_Username()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT USERNAME FROM "+userTable+"",null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String user_name= cursor.getString(cursor.getColumnIndex("USERNAME"));
        cursor.close();
        return user_name;

    }





    public boolean addGoal(float weight,float cal_day,float cal_week)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues gValues = new ContentValues();
        gValues.put("WEIGHT",weight);
        gValues.put("CAL_DAY",cal_day);
        gValues.put("CAL_WEEK",cal_week);
        long result =  db.insert(goal_Table,null,gValues);
        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }



    public String getGoal()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String goalQuery = "SELECT WEIGHT,CAL_DAY,CAL_WEEK FROM "+goal_Table;
        Cursor data = db.rawQuery(goalQuery,null);
        StringBuffer buffer = new StringBuffer();
        while(data.moveToNext())
        {
            int index1 = data.getColumnIndex("WEIGHT");
            int index2 = data.getColumnIndex("CAL_DAY");
            int index3 = data.getColumnIndex("CAL_WEEK");
            String weight = data.getString(index1);
            String day = data.getString(index2);
            String week = data.getString(index3);
            buffer.append("Target Weight : "+weight+"\n"+
            "Goal Per Day : "+day+"\n"+
            "Goal Per Week :"+week);
        }
        return buffer.toString();
    }

}
