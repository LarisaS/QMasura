package com.proiect.qmasura.sqlite;


import java.io.File;
import java.util.ArrayList;

import com.proiect.qmasura.obiecte.Ingredient;

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
	private static final String FRIGIDER="frigider";
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
	private static final String INGREDIENTE_POZA="poza";
	private static final String INGREDIENTE_CATEGORY="category_id";
	
	private static final String FRIGIDER_LOCAL_ID="_id";
	private static final String FRIGIDER_ID="ingredient_id";
	private static final String FRIGIDER_NAME="name";
	private static final String FRIGIDER_GENERAL_NAME="general_name";
	private static final String FRIGIDER_CANTITATE="cantitate";
	private static final String FRIGIDER_POZA="poza";
	private static final String FRIGIDER_CATEGORY="category_id";
	private static final String FRIGIDER_UM="um_frigider";
	private static final String FRIGIDER_UM_ID="um_id_frigider";
	
	
	private static final int SCHEMA_VERSION=1;

	
	private static final String createTableIngrediente="CREATE TABLE "+INGREDIENTE+" (" +
			INGREDIENTE_LOCAL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
			INGREDIENTE_ID+" INTEGER,"+
			INGREDIENTE_NAME+" varchar2(100),"+
			INGREDIENTE_GENERAL_NAME+" varchar2(100)," +
			INGREDIENTE_POZA+" varchar2(255)," +
			INGREDIENTE_CATEGORY+" integer );";
	
	private static final String createTableFrigider="CREATE TABLE "+FRIGIDER+" (" +
			FRIGIDER_LOCAL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
			FRIGIDER_ID+" INTEGER,"+
			FRIGIDER_NAME+" varchar2(100),"+
			FRIGIDER_GENERAL_NAME+" varchar2(100)," +
			FRIGIDER_CANTITATE+" real," +
			FRIGIDER_POZA+" varchar2(255)," +
			FRIGIDER_UM+" varchar2(255)," +
			FRIGIDER_UM_ID+" integer," +
			FRIGIDER_CATEGORY+" integer );";
	
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
		db.execSQL(createTableFrigider);
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
		 
	    String selectQuery = "SELECT * FROM " + SETTINGS+" WHERE "+SETTINGS_OPTION+" like '"+SETTINGS_OPT_DATA_ACTUALIZARE_UM+"'";
	 
	    Cursor c = db.rawQuery(selectQuery, null);
	    if(c.moveToFirst())
	    return c.getString(c.getColumnIndex(SETTINGS_VALUE)).toString();
	    else return "";
	}
	
	public boolean insereazaIngredient(Ingredient ingr)
	{
		boolean done=true;
		SQLiteDatabase db = this.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(INGREDIENTE_ID, ingr.getId());
		values.put(INGREDIENTE_CATEGORY, ingr.getCategory_id());
		values.put(INGREDIENTE_GENERAL_NAME, ingr.getGeneral_name());
		values.put(INGREDIENTE_POZA, ingr.getPoza());
		values.put(INGREDIENTE_NAME, ingr.getName());
		long local_id = db.insert(INGREDIENTE, null, values);
		
		if(local_id>0)
			done=true;
		else
			done=false;
		
		return done;
	}
	
	public boolean insereazaIngredientInFrigider(Ingredient ingr)
	{
		boolean done=true;
		SQLiteDatabase db = this.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(FRIGIDER_ID, ingr.getId());
		values.put(FRIGIDER_CATEGORY, ingr.getCategory_id());
		values.put(FRIGIDER_GENERAL_NAME, ingr.getGeneral_name());
		values.put(FRIGIDER_POZA, ingr.getPoza());
		values.put(FRIGIDER_NAME, ingr.getName());
		values.put(FRIGIDER_CANTITATE, ingr.getCantitate());
		values.put(FRIGIDER_UM, ingr.getUm());
		values.put(FRIGIDER_UM_ID, ingr.getUm_id());
		
		long local_id = db.insert(FRIGIDER, null, values);
		
		if(local_id>0)
			done=true;
		else
			done=false;
		
		return done;
	}
	
	public ArrayList<Ingredient> ingredienteDinFrigider()
	{
		ArrayList<Ingredient> ingrediente= new ArrayList<Ingredient>();
		SQLiteDatabase db = this.getReadableDatabase();
		
		//long local_id = db.insert(FRIGIDER, null, values);
		 String selectQuery = "SELECT * FROM " + FRIGIDER;
		 
		 Cursor c = db.rawQuery(selectQuery, null);
		 while(c.moveToNext())
		 {
			 Ingredient i= new Ingredient();
			 i.setCantitate(c.getFloat(c.getColumnIndex(FRIGIDER_CANTITATE)));
			 i.setId(c.getInt(c.getColumnIndex(FRIGIDER_ID)));
			 i.setName(c.getString(c.getColumnIndex(FRIGIDER_NAME)));
			 i.setGeneral_name(c.getString(c.getColumnIndex(FRIGIDER_GENERAL_NAME)));
			 i.setPoza(c.getString(c.getColumnIndex(FRIGIDER_POZA)));
			 i.setUm(c.getString(c.getColumnIndex(FRIGIDER_UM)));
			 i.setUm_id(c.getInt(c.getColumnIndex(FRIGIDER_UM_ID)));
			 
			 ingrediente.add(i);
		 }
		 return ingrediente;
	}
	
	
	/********db handler opps************/
	public void closeDB() {
	        SQLiteDatabase db = this.getReadableDatabase();
	        if (db != null && db.isOpen())
	            db.close();
	    }
	 
	
}
