package com.proiect.qmasura;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItemAdapter extends ArrayAdapter<String> {

	private final Context context;
	private  ArrayList<String> values;

	public MenuItemAdapter(Context context,ArrayList<String> values) {
		super(context,R.layout.menu_item,values);
		this.context = context;
	    this.values = values; 
	   
	}
	

	
	
	 public View getView(int position, View convertView, ViewGroup parent) {
	       LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	       View rowView = inflater.inflate(R.layout.menu_item, parent, false);
	       TextView title = (TextView) rowView.findViewById(R.id.menu_item_title);
	       ImageView logo = (ImageView) rowView.findViewById(R.id.menu_item_icon);
	       
	       Typeface tf = Typeface.createFromAsset(context.getAssets(),
	                "fonts/Sunshine.ttf");
	        title.setTypeface(tf);
	       
	       if(position==0)
	    	   logo.setImageResource(R.drawable.dark_logo);
	       else
	    	   if(position==1)
	    		   logo.setImageResource(R.drawable.cauta);
	    	   else
	    		   if(position==2)
	    			   logo.setImageResource(R.drawable.frigider);
	    		   else
	    			   if(position==3)
	    				   logo.setImageResource(R.drawable.meniu_icon);
	    			   else 
	    				   if(position==4)
	    					   logo.setImageResource(R.drawable.lista);
	       
	       title.setText(values.get(position));
	       
	       return rowView; 
	    }

}

