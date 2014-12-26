package com.proiect.qmasura.sqlite;


import java.io.File;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME="qmasura.db";
	private static final String INGREDIENTE="ingrediente";
	private static final String UNITATI="unitati";
	
	//tabela care retine data ultimei actualizari a unitatilor de masura si alte setari de care mai avem nevoie
	private static final String SETTINGS="settings";
	
	private static final String SETTINGS_ID="_id";
	private static final String SETTINGS_OPTION="option";
	private static final String SETTINGS_VALUE="value";
	
	private static final String SETTINGS_OPT_DATA_ACTUALIZARE_UM="um_update_time";
	private static final String SETTINGS_OPT_DATA_ACTUALIZARE_ING="ingrediente_update_time";
	
	private static final String UNITATI_LOCAL_ID="_id";
	private static final String UNITATI_ID="um_id";
	private static final String UNITATI_NAME="name";
	
	
	
	private static final String INGREDIENTE_LOCAL_ID="_id";
	private static final String INGREDIENTE_ID="ingredient_id";
	private static final String INGREDIENTE_NAME="name";
	private static final String INGREDIENTE_GENERAL_NAME="general_name";
	private static final String INGREDIENTE_CANTITATE="cantitate";
	private static final String INGREDIENTE_POZA="poza";
	private static final String INGREDIENTE_CATEGORY="category_id";
	
	private static final int SCHEMA_VERSION=1;

	
	private static final String createTableIngrediente="CREATE TABLE "+INGREDIENTE+" (" +
			INGREDIENTE_LOCAL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
			INGREDIENTE_ID+" INTEGER,"+
			INGREDIENTE_NAME+" varchar2(100),"+
			INGREDIENTE_GENERAL_NAME+" varchar2(100)," +
			INGREDIENTE_CANTITATE+" real," +
			INGREDIENTE_POZA+" varchar2(255)," +
			INGREDIENTE_CATEGORY+" integer );";
	
	private static final String createTableUnitati="CREATE TABLE "+UNITATI+" (" +
			UNITATI_LOCAL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
			UNITATI_ID+" INTEGER,"+
			UNITATI_NAME+" varchar2(100));";

	private static final String createTableSettings="CREATE TABLE "+SETTINGS+" (" +
			SETTINGS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
			SETTINGS_OPTION+" varchar2(100),"+
			SETTINGS_VALUE+" varchar2(100));";

	
	public DbHelper(Context context) {
		super(context,DATABASE_NAME, null, SCHEMA_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(createTableIngrediente);
		db.execSQL(createTableUnitati);
		db.execSQL(createTableSettings);
	
		ContentValues values = new ContentValues();
		values.put(SETTINGS_OPTION, SETTINGS_OPT_DATA_ACTUALIZARE_UM);
		values.put(SETTINGS_VALUE, "0");
		long um_id = db.insert(SETTINGS, null, values);
		
		values.clear();
		values.put(SETTINGS_OPTION, SETTINGS_OPT_DATA_ACTUALIZARE_ING);
		values.put(SETTINGS_VALUE, "0");
		long ing_id = db.insert(SETTINGS, null, values);
	
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		  
		}
	
	public String dataUpdateUnitatiMasura()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		 
	    String selectQuery = "SELECT "+SETTINGS_OPT_DATA_ACTUALIZARE_UM+" FROM " + SETTINGS+" WHERE "+SETTINGS_ID+"=1";
	 
	    Cursor c = db.rawQuery(selectQuery, null);
	    return c.getString(c.getColumnIndex(SETTINGS_OPT_DATA_ACTUALIZARE_UM)).toString();
	}
	

	/********db handler opps************/
	public void closeDB() {
	        SQLiteDatabase db = this.getReadableDatabase();
	        if (db != null && db.isOpen())
	            db.close();
	    }
	 
	
}
