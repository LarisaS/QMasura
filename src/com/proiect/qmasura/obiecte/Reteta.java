package com.proiect.qmasura.obiecte;

import java.io.Serializable;
import java.util.ArrayList;

public class Reteta implements Serializable {
	ArrayList<Ingredient> ingrediente;
	ArrayList<String> pasi;
	String nume, url_poza;
	String dificultate,timp;
	
	String mod_preparare;
	
	public Reteta(String nume)
	{
		ingrediente= new ArrayList<Ingredient>();
		pasi= new ArrayList<String>();
		this.nume=nume;
	}
	

	public Reteta()
	{
		ingrediente= new ArrayList<Ingredient>();
		pasi= new ArrayList<String>();
	}
	
	public void setUrlPoza(String url)
	{
		this.url_poza=url;
	}
	
	public void setTimp(String timp)
	{
		this.timp=timp;
	}
	
	public void setDificultate(String dif)
	{
		this.dificultate=dif;
	}
	
	public void setNume(String nume)
	{
		this.nume=nume;
	}
	
	public void setModPreparare(String preparare)
	{
		this.mod_preparare=preparare;
	}
	
	public String getModPreparare()
	{
		return this.mod_preparare;
	}
	
	public void setIngrediente(ArrayList<Ingredient> ingr)
	{
		this.ingrediente=ingr;
	}
	
	public String getNume()
	{
		return nume;
	}
	
	public String getUrlPoza()
	{
		return url_poza;
	}
	
	public ArrayList<Ingredient> getIngrediente()
	{
		return ingrediente;
	}
}
