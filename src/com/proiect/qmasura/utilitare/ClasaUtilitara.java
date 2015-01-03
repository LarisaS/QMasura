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
import com.proiect.qmasura.obiecte.IngredientLipsa;
import com.proiect.qmasura.obiecte.Reteta;
import com.proiect.qmasura.obiecte.SummaryReteta;
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
		 
		 String picture="",general_name,um;
		 int um_id,id;
		 float cantitate=0;
		 try
		 {
			 JSONObject um_json= json_ingredient.getJSONObject("unity");
			 id=json_ingredient.getInt("id");
			// picture=json_ingredient.getString("picture");
			 general_name=json_ingredient.getString("general_name");
			 um_id=um_json.getInt("id");
			 um=um_json.getString("name");
			 if(json_ingredient.has("cantitate"))
				 cantitate=(float)json_ingredient.getDouble("cantitate");
		 }
		 catch(Exception e)
		 {
			 return null;
		 }
		 Ingredient tmp= new Ingredient();
		 tmp.setId(id);
		 tmp.setGeneral_name(general_name);
		 tmp.setPoza(picture);
		 tmp.setUm(um);
		 tmp.setUm_id(um_id);
		 tmp.setCantitate(cantitate);
		 return tmp;
		 
	 }
	
	public static IngredientLipsa getIngredientLipsaFromJSON(JSONObject json_ingredient)
	 {
		  /**
		   *"id_ingredient":1,
		   *"name":"mere",
		   *"cantitate":"mere"
		   */
		 
		 String name;
		 int id_ingredient;
		 float cantitate=0;
		 try
		 {
			 id_ingredient=json_ingredient.getInt("id_ingredient");
			// picture=json_ingredient.getString("picture");
			 name=json_ingredient.getString("name");
			 if(json_ingredient.has("cantitate"))
				 cantitate=(float)json_ingredient.getDouble("cantitate");
		 }
		 catch(Exception e)
		 {
			 return null;
		 }
		 IngredientLipsa tmp= new IngredientLipsa();
		 tmp.setId(id_ingredient);
		 tmp.setName(name);
		 tmp.setCantitate(cantitate);
		 return tmp;
		 
	 }
	
	
	public static Ingredient getIngredientFromFrigiderJSON(JSONObject json_ingredient)
	 {
		
		 String picture,general_name;
		 int id;
		 try
		 {
			 id=json_ingredient.getInt("id");
			 picture=json_ingredient.getString("picture");
			 general_name=json_ingredient.getString("general_name");
		 }
		 catch(Exception e)
		 {
			 return null;
		 }
		 Ingredient tmp= new Ingredient();
		 tmp.setId(id);
		 tmp.setGeneral_name(general_name);
		 tmp.setPoza(picture);
		 return tmp;
		 
	 }

	public static SummaryReteta getSummaryRetetaFromJSON(JSONObject reteta_json) {
		// TODO Auto-generated method stub
		String name,poza,link;
		int id;
		float penalty;
		 
		SummaryReteta tmp= new SummaryReteta();
		
		 try
		 {
			 id=reteta_json.getInt("recipe_id");
			 poza="";
			 penalty=(float)reteta_json.getDouble("penalty");
			 name=reteta_json.getString("name");
			 link=reteta_json.getString("link");
			 JSONArray lipsa= reteta_json.getJSONArray("lipsesc");
			 for(int i=0;i<lipsa.length();i++)
			 {
				 IngredientLipsa li=getIngredientLipsaFromJSON(lipsa.getJSONObject(i));
				 tmp.adaugaIngredientLipsa(li);
			 }
			 tmp.setId(id);
			 tmp.setLink(link);
			 tmp.setName(name);
			 tmp.setPenalty(penalty);
			 tmp.setPoza(poza);
		 }
		 catch(Exception e)
		 {
			 return null;
		 }
		return tmp;
	}
	 
}
