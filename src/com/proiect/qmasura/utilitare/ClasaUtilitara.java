package com.proiect.qmasura.utilitare;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import com.proiect.qmasura.R;
import com.proiect.qmasura.obiecte.GeneralIngredient;
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
		   * ArrayList<Ingredient> ingrediente;
			private String description,name, picture;
			private String dificultate;
			private int time,nr_persons;
			private int local_id, id;
		   * 
		   */
		    
		 String name,picture,description,dificultate;
		 int time, nr_persons,id;
		 Reteta tmp= new Reteta();
		 try
		 {
			name= json_reteta.getString("name");
			id=json_reteta.getInt("id");
			picture=json_reteta.getString("picture");
			description=json_reteta.getString("description");
			JSONArray ingredients= json_reteta.getJSONArray("ingredients");
			ArrayList<Ingredient> ingrediente= new ArrayList<Ingredient>();
			for(int i=0;i<ingredients.length();i++)
			{
				JSONObject ingr_obj=ingredients.getJSONObject(i);
				Ingredient ingr= getIngredientFromJSON(ingr_obj);
				ingrediente.add(ingr);
			}
			tmp.setId(id);
			tmp.setName(name);
			tmp.setDescription(description);
			tmp.setIngrediente(ingrediente);
			tmp.setIngrediente(ingrediente);
		 }
		 catch(Exception e)
		 {
		 }
		 
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
		 String picture="",general_name,um="",name;
		 int um_id,id;
		 float cantitate=0;
		 Ingredient tmp= new Ingredient();
		 try
		 {
			 if(json_ingredient.has("unity"))
			 {
				 JSONObject um_json= json_ingredient.getJSONObject("unity"); 
				 um_id=um_json.getInt("id");
				 um=um_json.getString("name");
				 tmp.setUm(um);
				 tmp.setUm_id(um_id);
			 }
			 
			 if(json_ingredient.has("id"))
			 {
				 id=json_ingredient.getInt("id");
			 }
			 else id=0;
			 if(json_ingredient.has("general_name"))
			 {
				 general_name=json_ingredient.getString("general_name");
			 }
			 else
				 general_name="";
			 if(json_ingredient.has("name"))
			 {
				 name=json_ingredient.getString("name");
			 }
			 else
				 name="";
			if(json_ingredient.has("recipe_quantity"))
			{
				cantitate=(float)json_ingredient.getDouble("recipe_quantity");
			}
			else
				cantitate=0;
			
			if(json_ingredient.has("rec_unity_name") )
			{
				um=json_ingredient.getString("rec_unity_name");
				if(um==null)
					um="";
			}
			else
				
			
			
			if(json_ingredient.has("picture") && !json_ingredient.isNull("picture"))
			{
				picture=json_ingredient.getString("picture");
			}
			else
				picture="";
			
			tmp.setId(id);
			tmp.setGeneral_name(general_name);
			tmp.setName(name);
			tmp.setCantitate(cantitate);
			tmp.setPoza(picture);	
			tmp.setUm(um);
			 
			 
		 }
		 catch(Exception e)
		 {
			 return null;
		 }
		 
		 
		 return tmp;
		 
	 }
	
	
	
	public static GeneralIngredient geGeneralIngredientFromJSON(String name,JSONObject rest_json_ingredient)
	 {
		 String picture="",general_name,ums;
		 GeneralIngredient tmp= new GeneralIngredient();
		 tmp.setGeneralName(name);
		 try
		 {
			 if(rest_json_ingredient.has("picture") && !rest_json_ingredient.isNull("picture"))
				 picture=rest_json_ingredient.getString("picture");
			 else picture="";
			 
			 if(rest_json_ingredient.has("um_ids")){
				 ArrayList<String> um_ids= new ArrayList<String>();
				 JSONArray ids= rest_json_ingredient.getJSONArray("um_ids");
				 for(int i=0;i<ids.length();i++)
					 um_ids.add(ids.getString(i));
				 ums=StringUtils.join(um_ids.toArray(),',');
			 }
			 else return null;
			 
			 tmp.setPicure(picture);
			 tmp.setUms(ums);
			
		 }
		 catch(Exception e)
		 {
			 return null;
		 }
		 
		 
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
		 String cantitate="-";
		 try
		 {
			 id_ingredient=json_ingredient.getInt("id_ingredient");
			// picture=json_ingredient.getString("picture");
			 name=json_ingredient.getString("name");
			 if(json_ingredient.has("cantitate"))
				 cantitate=json_ingredient.getString("cantitate");
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
			 if(json_ingredient.has("picture") && !json_ingredient.isNull("picture"))
				 picture=json_ingredient.getString("picture");
			 else
				 picture="";
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
	 
	public static String styleForReceipePage()
	{
		return "<style>" +
				".wrapper_continut { font-size:14px; } "+
				".lipseste { color:#ff0000; }"+
				".title { width:100%; text-align:center; font-size:18px; }"+
				"h3 { font-size:16px; }"+
				" ul { padding:0px; margin:0px; }"+
				" ul li { font-weight:300; list-style-type: none; padding:0px; margin:0px;}"+
				".spacer { padding:5px 0;}"+
				".descriere {}"+
				"</style>";
	}
	
	public static String listOfIngredientsHTML(ArrayList<Ingredient> ingrediente,ArrayList<IngredientLipsa> lipsa)
	{
		Map<Integer,IngredientLipsa> lipsa_map= new HashMap<Integer, IngredientLipsa>();
		
		for(int i=0;i<lipsa.size();i++)
		{
			lipsa_map.put(lipsa.get(i).getId(), lipsa.get(i));
		}
		
		StringBuilder html= new StringBuilder();
		html.append("<h3 class='ingrediente'>");
		html.append("Ingrediente");
		html.append("</h3>");
		html.append("<ul>");
		if(ingrediente.size()>0)
			for(int i=0;i<ingrediente.size();i++)
			{
				if(ingrediente.get(i)!=null)
				{
					ingrediente.get(i).display();
					if(lipsa_map.containsKey(ingrediente.get(i).getId()))
					{
						html.append("<li class='lipseste'>");
					}
					else
						html.append("<li>");
					html.append(ingrediente.get(i).getGeneral_name());
					html.append("  "+ingrediente.get(i).getCantitateCuUnitati());
					if(lipsa_map.containsKey(ingrediente.get(i).getId()))
					{
						html.append("(");
						html.append(lipsa_map.get(ingrediente.get(i).getId()).getCantitate());
						html.append(")");
					}
					
					html.append("</li>");
				}
			}
		else
			html.append("Ingredientele nu sunt disponibile");
		html.append("</ul>");
		String rsp=html.toString();
		return rsp;
	}
	
	public static String listOfMissingIngredientsHTML(ArrayList<IngredientLipsa> ingrediente)
	{
		StringBuilder html= new StringBuilder();
		html.append("<ul>");
		for(int i=0;i<ingrediente.size();i++)
		{
			if(ingrediente.get(i)!=null)
			{
				html.append("<li>");
				html.append(ingrediente.get(i).getName());
				html.append(ingrediente.get(i).getCantitate());
				html.append("</li>");
			}
		}
		html.append("</ul>");
		String rsp=html.toString();
		return rsp;
	}
	
	public static String descriereReteta(Reteta reteta)
	{
		StringBuilder html= new StringBuilder();
		html.append(styleForReceipePage());
		html.append("<div class='wrapper_continut'>");
		html.append("<h1 class='title'>"+reteta.getName()+"</h1>");
		html.append("<img width='"+100+"%' alt='"+reteta.getName()+"' src='https://qmasura-ruby.herokuapp.com/api/recipes/getPicture?id="+reteta.getId()+"' />");
		html.append("<table><tbody><tr><td>");
		html.append("</td><td>");
		html.append("</td><td>");
		html.append("</td></tr></tbody></table>");
		html.append(listOfIngredientsHTML(reteta.getIngrediente(),reteta.getIngrediente_lipsa()));
		/*html.append(listOfMissingIngredientsHTML(reteta.getIngrediente_lipsa()));*/
		html.append("<h3>Preparare</h3>");
		html.append("<span class='descriere'>");
		html.append(reteta.getDescription());
		html.append("</span>");
		html.append("</div>");
		String rsp=html.toString();
		return rsp;
	}
	
	public static String descriereRetetaHTML(Reteta reteta)
	{
		StringBuilder html= new StringBuilder();
		html.append(styleForReceipePage());
		html.append(listOfIngredientsHTML(reteta.getIngrediente(),reteta.getIngrediente_lipsa()));
		//html.append(listOfMissingIngredientsHTML(reteta.getIngredienteLipsa()));
		html.append(descriereRetetaHTML(reteta));
		return html.toString();
	}
}
