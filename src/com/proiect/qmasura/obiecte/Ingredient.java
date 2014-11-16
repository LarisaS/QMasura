package com.proiect.qmasura.obiecte;

import java.io.Serializable;

public class Ingredient implements Serializable {
	private String cantitate, nume, url_poza, unitati;
	
	public Ingredient(String nume,String poza)
	{
		this.nume=nume;
		this.url_poza=poza;
		cantitate="0";
		unitati="";
	}
	
	public String getCantitateCuUnitati()
	{
		return cantitate+" "+unitati;
		
	}
	

	public String getNume()
	{
		return this.nume;
	}
	
	public String getUrlPoza()
	{
		return this.url_poza;
	}
	
	public String getCantitate()
	{
		return this.cantitate;
		
	}
	
	public void setCantitate(String cantit)
	{
		this.cantitate=cantit;
	}
	
	public void setNume(String nume)
	{
		this.nume=nume;
	}
	
	public void setUnitate(String unit)
	{
		this.unitati=unit;
	}
	
	public void setURLPoza(String url)
	{
		this.url_poza=url;
	}
}
