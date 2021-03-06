package com.proiect.qmasura;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentTransaction;
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
import com.proiect.qmasura.obiecte.Ingredient;
import com.proiect.qmasura.sqlite.DbHelper;


public class FrigiderulMeuGridAdaptor extends BaseAdapter {

	private Context context;
	private ArrayList<Ingredient> ingrediente;
	FrigiderulMeuFragment inst;
	public FrigiderulMeuGridAdaptor(Context context, ArrayList<Ingredient> values,FrigiderulMeuFragment inst)
	{
		this.context=context;
		this.ingrediente=values;
		this.inst=inst;
		//Toast.makeText(context, ""+poze.size(), Toast.LENGTH_SHORT).show();
		 final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
		 final int cacheSize = maxMemory / 8;

	}

	public FrigiderulMeuGridAdaptor(Context context, ArrayList<Ingredient> poze,LruCache<String,Bitmap> cache)
	{
		this.context=context;
		this.ingrediente=poze;
	}

	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ingrediente.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return ingrediente.get(arg0);
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
	            grid = inflater.inflate(R.layout.frigiderul_meu_grid_item, null);
	          } else {
	            grid = (View) convertView;	     
	          }
	          SquareImageView imageView = (SquareImageView)grid.findViewById(R.id.grid_item_image);
	          imageView.setImageResource(R.drawable.default_bg);
	          TextView title=(TextView) grid.findViewById(R.id.grid_item_title);
	          TextView cantitate=(TextView) grid.findViewById(R.id.grid_item_cantitate);
	          ImageButton sterge_ingredientul=(ImageButton) grid.findViewById(R.id.grid_item_sterge);
	          sterge_ingredientul.setTag( pozsition);
	          sterge_ingredientul.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Ingredient ingr= ingrediente.get((Integer)v.getTag());
					
					DbHelper helper= new DbHelper(context);
					if(helper.stergeIngredient(ingr)){
						inst.removeIngr((Integer)v.getTag());
						inst.refresh();
						Toast.makeText(context,"Ingredientul a fost sters", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(context,"Ingredientul nu a putut fi sters", Toast.LENGTH_SHORT).show();
					}
					helper.close();
				}
	        	  
	          }); 
	          
	          Typeface tf = Typeface.createFromAsset(context.getAssets(),
		                "fonts/Bombardier.ttf");
		      title.setTypeface(tf);
		      cantitate.setTypeface(tf);
		      
		      cantitate.setText(ingrediente.get(pozsition).getCantitateCuUnitati());
		      
		      ImageLoader imageLoader = ImageLoader.getInstance();
		      DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
		          				.cacheOnDisc(true).resetViewBeforeLoading(true)
		          				.showImageForEmptyUri(R.drawable.default_bg)
		          				.showImageOnFail(R.drawable.default_bg)
		          				.showImageOnLoading(R.drawable.default_bg).build();
		        
		      title.setText(ingrediente.get(pozsition).getGeneral_name());
		      imageLoader.displayImage(ingrediente.get(pozsition).getPoza(), imageView, options);
	          return grid;
	}


}
