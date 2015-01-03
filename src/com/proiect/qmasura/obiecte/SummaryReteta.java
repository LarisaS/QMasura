package com.proiect.qmasura.obiecte;

import java.io.Serializable;
import java.util.ArrayList;

import android.util.Log;

public class SummaryReteta implements Serializable{

	private String name,link,poza;
	private int id;
	private float penalty;
	private ArrayList<IngredientLipsa> lipsesc;
	public SummaryReteta(int id,String name,String link,String poza,float penalty)
	{
		this.id=id;
		this.name=name;
		this.link=link;
		this.poza=poza;
		this.penalty=penalty;
		lipsesc= new ArrayList<IngredientLipsa>();
	}
	
	public SummaryReteta()
	{
		lipsesc= new ArrayList<IngredientLipsa>();
	}
	
	public void adaugaIngredientLipsa(IngredientLipsa ingr)
	{
		lipsesc.add(ingr);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPoza() {
		return poza;
	}

	public void setPoza(String poza) {
		this.poza = poza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPenalty() {
		return penalty;
	}

	public void setPenalty(float penalty) {
		this.penalty = penalty;
	}
	
	public void display()
	{
		Log.i("REteta ", "Reteta "+id+" "+name+" "+penalty+" Ingrediente lipsa"+lipsesc.size());
	}
}
