package com.proiect.qmasura.obiecte;

import java.util.ArrayList;

public class Reteta {
	ArrayList<Ingredient> ingrediente;
	ArrayList<String> pasi;
	String nume, url_poza;
	
	public Reteta()
	{
		ingrediente= new ArrayList<Ingredient>();
		pasi= new ArrayList<String>();
	}
}
