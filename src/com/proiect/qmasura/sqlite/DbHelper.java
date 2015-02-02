package com.proiect.qmasura.sqlite;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.proiect.qmasura.obiecte.Ingredient;
import com.proiect.qmasura.obiecte.Reteta;
import com.proiect.qmasura.obiecte.UnitatiDeMasura;

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
	private static final String RETETE="retete";
	private static final String MENIURI="meniuri";
	private static final String INGREDIENTE_RETETE="ingrediente_retete";
	
	private static final String INGREDIENTE_R_ID="_id";
	private static final String INGREDIENTE_R_NAME="name";
	private static final String INGREDIENTE_R_GENERAL_NAME="general_name";
	private static final String INGREDIENTE_R_CANTITATE="cantitate";
	private static final String INGREDIENTE_R_UM_ID="um_id";
	private static final String INGREDIENTE_R_UM="um";
	private static final String INGREDIENTE_R_RETETA="reteta_id";
	
	//tabela care retine data ultimei actualizari a unitatilor de masura si alte setari de care mai avem nevoie
	private static final String SETTINGS="settings";
	
	private static final String SETTINGS_ID="_id";
	private static final String SETTINGS_OPTION="option";
	private static final String SETTINGS_VALUE="value";
	
	private static final String SETTINGS_OPT_DATA_ACTUALIZARE_UM="um_update_time";
	private static final String SETTINGS_OPT_DATA_ACTUALIZARE_ING="ingrediente_update_time";
	private static final String SETTINGS_OPT_DATA_ACTUALIZARE_FRIGIDER="frigider_update_time";
	
	private static final String UNITATI_LOCAL_ID="_id";
	private static final String UNITATI_ID="um_id";
	private static final String UNITATI_NAME="name";
	
	
	
	private static final String INGREDIENTE_LOCAL_ID="_id";
	private static final String INGREDIENTE_GENERAL_NAME="general_name";
	private static final String INGREDIENTE_POZA="poza";
	
	private static final String FRIGIDER_LOCAL_ID="_id";
	private static final String FRIGIDER_ID="ingredient_id";
	private static final String FRIGIDER_GENERAL_NAME="general_name";
	private static final String FRIGIDER_CANTITATE="cantitate";
	private static final String FRIGIDER_POZA="poza";
	private static final String FRIGIDER_SHOW="show_ingredinet";
	private static final String FRIGIDER_UM="um_frigider";
	private static final String FRIGIDER_UM_ID="um_id_frigider";
	
	private static final String RETETE_ID="_id";
	private static final String RETETE_NAME="name";
	private static final String RETETE_DESC="description";
	private static final String RETETE_DIF="dificultate";
	private static final String RETETE_PERS="nr_persoane";
	private static final String RETETE_TIME="time";
	private static final String RETETE_POZA="poza";
	
	private static final int SCHEMA_VERSION=1;

	
	private static final String createTableIngrediente="CREATE TABLE "+INGREDIENTE+" (" +
			INGREDIENTE_LOCAL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
			INGREDIENTE_GENERAL_NAME+" varchar2(100)," +
			INGREDIENTE_POZA+" varchar2(255));";
	
	private static final String createTableFrigider="CREATE TABLE "+FRIGIDER+" (" +
			FRIGIDER_LOCAL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
			FRIGIDER_GENERAL_NAME+" varchar2(100)," +
			FRIGIDER_ID+" integer," +
			FRIGIDER_CANTITATE+" real," +
			FRIGIDER_POZA+" varchar2(255)," +
			FRIGIDER_UM+" varchar2(255)," +
			FRIGIDER_UM_ID+" integer," +
			FRIGIDER_SHOW+" integer );";
	
	private static final String createTableUnitati="CREATE TABLE "+UNITATI+" (" +
			UNITATI_LOCAL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
			UNITATI_ID+" INTEGER,"+
			UNITATI_NAME+" varchar2(100));";

	private static final String createTableSettings="CREATE TABLE "+SETTINGS+" (" +
			SETTINGS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
			SETTINGS_OPTION+" varchar2(100),"+
			SETTINGS_VALUE+" varchar2(100));";

	private static final String createTableIngredienteReteta="CREATE TABLE "+INGREDIENTE_RETETE+ " ( "+
			INGREDIENTE_R_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			INGREDIENTE_R_NAME+ " varchar2(100)," +
			INGREDIENTE_R_GENERAL_NAME+" varchar2(100)," +
			INGREDIENTE_R_CANTITATE +" real," +
			INGREDIENTE_R_UM+" varchar2(100)," +
			INGREDIENTE_R_UM_ID+" integer," +
			INGREDIENTE_R_RETETA+" integer ) ";
	private static final String createTableReteta="CREATE TABLE "+RETETE+" (" +
			RETETE_ID+" integer primary key autoincrement, " +
			RETETE_NAME+" varchar2(255), " +
			RETETE_DESC+" text, " +
			RETETE_TIME+" varchar2(100)," +
			RETETE_PERS+" varchar2(100)," +
			RETETE_POZA+" varchar2(255)," +
			RETETE_DIF+" varchr2(100) )";
	
	
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
		db.execSQL(createTableIngredienteReteta);
		db.execSQL(createTableReteta);
	
		ContentValues values = new ContentValues();
		values.put(SETTINGS_OPTION, SETTINGS_OPT_DATA_ACTUALIZARE_UM);
		values.put(SETTINGS_VALUE, "0");
		long um_id = db.insert(SETTINGS, null, values);
		
		values.clear();
		values.put(SETTINGS_OPTION, SETTINGS_OPT_DATA_ACTUALIZARE_ING);
		values.put(SETTINGS_VALUE, "0");
		long ing_id = db.insert(SETTINGS, null, values);
		
		values.clear();
		values.put(SETTINGS_OPTION, SETTINGS_OPT_DATA_ACTUALIZARE_FRIGIDER);
		values.put(SETTINGS_VALUE, "0");
		long frig_id = db.insert(SETTINGS, null, values);
	
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
	
	public String dataUpdateFrigider()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		 
	    String selectQuery = "SELECT * FROM " + SETTINGS+" WHERE "+SETTINGS_OPTION+" like '"+SETTINGS_OPT_DATA_ACTUALIZARE_FRIGIDER+"'";
	 
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
		values.put(INGREDIENTE_GENERAL_NAME, ingr.getGeneral_name());
		values.put(INGREDIENTE_POZA, ingr.getPoza());
		long local_id = db.insert(INGREDIENTE, null, values);
		
		if(local_id>0)
			done=true;
		else
			done=false;
		
		return done;
	}
	
	
	private boolean adaugaIngredientInReteta(Ingredient ingr,long reteta_id)
	{
		boolean done=true;
		SQLiteDatabase db = this.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(INGREDIENTE_R_RETETA, reteta_id);
		values.put(INGREDIENTE_R_GENERAL_NAME, ingr.getGeneral_name());
		values.put(INGREDIENTE_R_NAME, ingr.getName());
		values.put(INGREDIENTE_R_CANTITATE, ingr.getCantitate());
		values.put(INGREDIENTE_R_NAME, ingr.getName());
		values.put(INGREDIENTE_R_UM, ingr.getUm());
		values.put(INGREDIENTE_R_UM_ID, ingr.getUm_id());
		
		long local_id = db.insert(INGREDIENTE_RETETE, null, values);
		
		if(local_id>0)
			done=true;
		else
			done=false;
		
		return done;
	}
	
	public boolean salveazaReteta(Reteta reteta)
	{
		boolean done=true;
		SQLiteDatabase db = this.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(RETETE_NAME, reteta.getName());
		values.put(RETETE_DESC, reteta.getDescription());
		values.put(RETETE_TIME, "-");
		values.put(RETETE_PERS, "-");
		values.put(RETETE_DIF, "-");
		values.put(RETETE_POZA, reteta.getPicture());
		
		long local_id = db.insert(RETETE, null, values);
		
		if(local_id>0)
		{
			ArrayList<Ingredient> ingrediente= reteta.getIngrediente();
			for(int i=0;i<ingrediente.size();i++)
			{
				adaugaIngredientInReteta(ingrediente.get(i),local_id);
			}
		}
		else return false;
		
		if(local_id>0)
			done=true;
		else
			done=false;
		return done;
	}
	
	
	public ArrayList<Reteta> listaReteteSalvate()
	{
		ArrayList<Reteta> retete= new ArrayList<Reteta>();
		
		
		return retete;
	}
	
	
	public boolean insereazaIngredientInFrigider(Ingredient ingr)
	{
		Log.i("insereazaIngredientInFrigider","Inserare Ingredient :");
		ingr.display();
		boolean done=true;
		SQLiteDatabase db = this.getReadableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(FRIGIDER_SHOW,1);
		values.put(FRIGIDER_GENERAL_NAME, ingr.getGeneral_name());
		values.put(FRIGIDER_POZA, ingr.getPoza());
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
			 i.setId(c.getInt(c.getColumnIndex(FRIGIDER_LOCAL_ID)));
			 i.setGeneral_name(c.getString(c.getColumnIndex(FRIGIDER_GENERAL_NAME)));
			 i.setPoza(c.getString(c.getColumnIndex(FRIGIDER_POZA)));
			 i.setUm(c.getString(c.getColumnIndex(FRIGIDER_UM)));
			 i.setUm_id(c.getInt(c.getColumnIndex(FRIGIDER_UM_ID)));
			 
			 ingrediente.add(i);
		 }
		 return ingrediente;
	}
	
	public void actualizeazaTimestampUnitati()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		ContentValues cv = new ContentValues();
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		cv.put(SETTINGS_VALUE,modifiedDate);
		db.update(SETTINGS, cv, SETTINGS_OPTION+" like '"+SETTINGS_OPT_DATA_ACTUALIZARE_UM+"'", null);
	}
	
	public void actualizeazaTimestampFrigider()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		ContentValues cv = new ContentValues();
		Date date = new Date();
		String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
		cv.put(SETTINGS_VALUE,modifiedDate);
		db.update(SETTINGS, cv, SETTINGS_OPTION+" like '"+SETTINGS_OPT_DATA_ACTUALIZARE_FRIGIDER+"'", null);
	}
	
	public boolean populeazaUnitatiDeMasura(ArrayList<UnitatiDeMasura> ums)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		 db.beginTransaction();
		 db.execSQL("delete from "+ UNITATI);
		 ContentValues values = new ContentValues();
		 int ok=1;
		 for(int i=0;i<ums.size();i++)
		 {
			values.clear();
			values.put(UNITATI_ID,ums.get(i).getId());
			values.put(UNITATI_NAME,ums.get(i).getName());
			long local_id = db.insert(UNITATI, null, values);
			if(local_id==-1) ok=0;
		 }
		 if(ok==1) db.setTransactionSuccessful();
		 
		 db.endTransaction();
		 if(ok==1)
			 	return true;
		 else
			 	return false;
	}
	
	public ArrayList<UnitatiDeMasura> unitatileDeMasura()
	{
		SQLiteDatabase db = this.getReadableDatabase();
		String selectQuery = "SELECT * FROM " + UNITATI;		 
		Cursor c = db.rawQuery(selectQuery, null);
		ArrayList<UnitatiDeMasura> units= new ArrayList<UnitatiDeMasura>();
		while(c.moveToNext())
		{
			 UnitatiDeMasura um= new UnitatiDeMasura();
			 int id=  c.getInt(c.getColumnIndex(UNITATI_ID));
			 String name= c.getString(c.getColumnIndex(UNITATI_NAME));
			 um.setId(id);
			 um.setName(name);
			 units.add(um);
		 }
		return units;		
		
	}
	/********db handler opps************/
	public void closeDB() {
	        SQLiteDatabase db = this.getReadableDatabase();
	        if (db != null && db.isOpen())
	            db.close();
	    }
	 
	
}
