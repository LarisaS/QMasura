package com.proiect.qmasura.obiecte;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;

import com.proiect.qmasura.utilitare.ClasaUtilitara;

public class Reteta  implements Serializable {
	
	/*
	 * [{"id":1,
	 * 	 "name":"Prajitura cu mac si cu crema de smantana",
	 * 	 "motto":"Prajitura cu mac si cu o crema fina de smantana.",
	 *   "time":60,
	 *   "nr_persons":8,
	 *   "difficulty_id":4,
	 *   "country_id":1,
	 *   "picture":"",
	 *   "link":"https://retete.lidl.ro/reteta/prajitura-cu-mac-si-cu-crema-de-smantana/948",
	 *   "description":
	 * */
	
	ArrayList<Ingredient> ingrediente;
	ArrayList<IngredientLipsa> ingrediente_lipsa;
	private String description,name, picture;
	private String dificultate;
	private int time,nr_persons;
	private int local_id, id;
	
	public Reteta(String json)
	{
		Reteta tmp= ClasaUtilitara.getRetetaFromJSON(null);
	}
	

	public Reteta()
	{
		ingrediente= new ArrayList<Ingredient>();
		ingrediente_lipsa= new ArrayList<IngredientLipsa>();
	}
	
	
	
	public ArrayList<IngredientLipsa> getIngrediente_lipsa() {
		return ingrediente_lipsa;
	}


	public void setIngrediente_lipsa(ArrayList<IngredientLipsa> ingrediente_lipsa) {
		this.ingrediente_lipsa = ingrediente_lipsa;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setPoza(String url)
	{
		this.picture=url;
	}
	
	public void setIngredienteLipsa(ArrayList<IngredientLipsa> ingrds)
	{
		ingrediente_lipsa=ingrds;
	}
	
	public ArrayList<IngredientLipsa> getIngredienteLipsa()
	{
		return ingrediente_lipsa;
	}
	public void setTime(int timp)
	{
		this.time=timp;
	}
	
	public void setDificultate(String dif)
	{
		this.dificultate=dif;
	}
	
	public void setName(String nume)
	{
		this.name=nume;
	}
	
	public void setDescription(String preparare)
	{
		this.description=preparare;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public void setIngrediente(ArrayList<Ingredient> ingr)
	{
		this.ingrediente=ingr;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPicure()
	{
		return picture;
	}
	
	public ArrayList<Ingredient> getIngrediente()
	{
		return ingrediente;
	}
	
	public void display()
	{
		Log.i("Reteta ","Reteta "+id+" "+name);
	}
}
