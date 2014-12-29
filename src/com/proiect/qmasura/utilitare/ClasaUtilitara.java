package com.proiect.qmasura.utilitare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

import com.proiect.qmasura.R;
import com.proiect.qmasura.obiecte.Ingredient;
import com.proiect.qmasura.obiecte.Reteta;
import com.proiect.qmasura.obiecte.UnitatiDeMasura;





public class ClasaUtilitara {

	private static int[] desert={R.drawable.desert1,R.drawable.desert2,R.drawable.desert3};
	private static int[] craciun;
	private static int[] paste;
	private static int[] mic_dejun;
	private static int[] pranz;
	private static int[] cina;
	private static int[] vegetarian;
	private static int[] traditional;
	private static int happy_new_year;
	
	
	public static int imagineHomePage()
	{
		int bg= R.drawable.default_bg;
		/*
		 * Idee: putem folosi o imagine data de un url -  daca are conexiune la internet
		 * Alegerea imaginii trebuie facuta in acest caz intr-o functie de php
		 */
		
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf_month = new SimpleDateFormat("MM");
		SimpleDateFormat sdf_year = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf_day= new SimpleDateFormat("dd");
		SimpleDateFormat sdf_hour= new SimpleDateFormat("HH");
		String luna = sdf_month.format(c.getTime());
		String an= sdf_year.format(c.getTime());
		String zi= sdf_day.format(c.getTime());
		String ora= sdf_hour.format(c.getTime());
		
		int[] sarbatoare = verificaSarbatoare(zi, luna,an);
		
		bg= pozaRandom(desert);
		
		return bg;
		
	}

	//nu este folosita inca
	private static int[] verificaSarbatoare(String zi, String luna, String an) {
		// TODO Auto-generated method stub
		int[] poze=null;
		try{
		int zi_i=Integer.parseInt(zi);
		int luna_i=Integer.parseInt(luna);
		
		switch(luna_i)
		{
		case 12:
				{
					if(zi_i>1 && zi_i<30)
						poze=craciun;
					break;
				}
		
		}
		
		return poze;
		}
		catch(Exception e)
		{
			return null;
		}
		
	}
	
	//nu este folosita
	private static int pozaRandom(int[] sursa)
	{
		int max= sursa.length;
		Random rand = new Random();

		int  n = rand.nextInt(max);
	
		return sursa[0];
	}

	
	public static String getStringFromJson(HttpEntity entity) throws IOException
		{
			
		      InputStream is = entity.getContent();
		           
		      BufferedReader reader = new BufferedReader ( new InputStreamReader(is,"UTF-8"),8);
		      StringBuilder sb = new StringBuilder();
		       String  line = null;
		       while ((line = reader.readLine()) != null) {
		        	   {   
		        	   sb.append(line);
		        	   }
		           } 
		  
		    
		       line= sb.toString();
		       is.close();
		       reader.close(); 
		       return line;
		     
		}

	 
	public static Reteta getRetetaFromJSON(JSONObject json_reteta)
	 {
		  /**
		   * private String description,name, picture;
			 private String dificultate;
			 private int time,nr_persons;
			 private int local_id, id;
		   * 
		   */
		    
		 String name,picture,description,dificultate;
		 int time, nr_persons,local_id,id;
		 
		 Reteta tmp= new Reteta();
		 return tmp;
		 
	 }
	
	public static UnitatiDeMasura getUnitateFromJSON(JSONObject json_um)
	 {
		int id=0;
		String name="";
		UnitatiDeMasura um= new UnitatiDeMasura();
		try
		{
		id=json_um.getInt("id");
		name=json_um.getString("name");
		um.setId(id);
		um.setName(name);
		}
		catch(Exception e)
		{
			
		}
		return um;
	 }
	 
	public static Ingredient getIngredientFromJSON(JSONObject json_ingredient)
	 {
		  /**
		   *"id":1,
		   *"name":"mere",
		   *"general_name":"mere",
		   *"category_id":6,
		   *"buc_quantity":150.0,
		   *"picture":null
		   */
		 
		 String picture,name,general_name;
		 int category_id,id;
		 double buc_quantity;
		 try
		 {
			 id=json_ingredient.getInt("id");
			 picture=json_ingredient.getString("picture");
			 name=json_ingredient.getString("name");
			 general_name=json_ingredient.getString("general_name");
			 category_id=json_ingredient.getInt("category_id");
			 buc_quantity=json_ingredient.getDouble("buc_quantity");
		 }
		 catch(Exception e)
		 {
			 return null;
		 }
		 Ingredient tmp= new Ingredient();
		 tmp.setId(id);
		 tmp.setName(name);
		 tmp.setGeneral_name(general_name);
		 tmp.setPoza(picture);
		 tmp.setCategory_id(category_id);
		 return tmp;
		 
	 }
	 
}
