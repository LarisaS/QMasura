package com.proiect.qmasura;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.proiect.qmasura.obiecte.Reteta;
import com.proiect.qmasura.obiecte.SummaryReteta;

public class ListareReteteAdaptor extends BaseAdapter {

	private Context context;
	private ArrayList<SummaryReteta> retete;
	
	public ListareReteteAdaptor(Context context, ArrayList<SummaryReteta> values)
	{
		this.context=context;
		this.retete=values;
		//Toast.makeText(context, ""+poze.size(), Toast.LENGTH_SHORT).show();

	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return retete.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return retete.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("ResourceAsColor") @Override
	public View getView(int pozsition, View convertView, ViewGroup parent) {
		  View grid;
	      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	          if (convertView == null) {
	            grid = new View(context);
	            grid = inflater.inflate(R.layout.listare_retete_item, null);
	          } else {
	            grid = (View) convertView;	     
	          }
	          SquareImageView imageView = (SquareImageView)grid.findViewById(R.id.imagine_reteta_sugerata);
	          imageView.setImageResource(R.drawable.default_bg);
	          TextView title=(TextView) grid.findViewById(R.id.titlu_reteta_sugerata);
	          /*ImageButton adauga_la_meniu=(ImageButton) grid.findViewById(R.id.grid_item_sterge);
	          adauga_la_meniu.setTag( pozsition);
	          adauga_la_meniu.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(context, ((Integer)v.getTag())+"", Toast.LENGTH_SHORT).show();
				}
	        	  
	          });*/
	          
	          Typeface tf = Typeface.createFromAsset(context.getAssets(),
		                "fonts/Sunshine.ttf");
		      title.setTypeface(tf);
		      
		      ImageLoader imageLoader = ImageLoader.getInstance();
		      DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
		          				.cacheOnDisc(true).resetViewBeforeLoading(true)
		          				.showImageForEmptyUri(R.drawable.default_bg)
		          				.showImageOnFail(R.drawable.default_bg)
		          				.showImageOnLoading(R.drawable.default_bg).build();
		          		  
		        
		      title.setText(retete.get(pozsition).getName());
		      imageLoader.displayImage(retete.get(pozsition).getPoza(), imageView, options);
		    	          
	          
	          //download and display image from url
	          return grid;
	}


}
