package com.proiect.qmasura.obiecte;

import java.io.Serializable;

public class IngredientLipsa implements Serializable {

	private String name;
	private int id;
	private float cantitate;
	
	public IngredientLipsa()
	{}
	public IngredientLipsa(int id, String name,float cantitate)
	{
		this.id=id;
		this.name=name;
		this.cantitate=cantitate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getCantitate() {
		return cantitate;
	}
	public void setCantitate(float cantitate) {
		this.cantitate = cantitate;
	}
	
	
}
