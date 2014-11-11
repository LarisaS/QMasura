package com.proiect.qmasura;

import java.util.ArrayList;

import com.proiect.qmasura.obiecte.Ingredient;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class CautaReteteFragment  extends Fragment {
	View fragmentView;
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ArrayList<Ingredient> ingrediente;
	private CheckBox time1,time2,time3,dific1,dific2,dific3;
	private AutoCompleteTextView filtrare_ingrediente;
	private TextView label1,label2,label3;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setHasOptionsMenu(true);
	    }
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 
		 ingrediente= new ArrayList<Ingredient>();
		 String tomato = "http://www.foodproductiondaily.com/var/plain_site/storage/images/publications/food-beverage-nutrition/foodproductiondaily.com/innovations/packaging-made-from-tomato-waste/8593675-1-eng-GB/Packaging-made-from-tomato-waste.jpg";
         String onions="http://upload.wikimedia.org/wikipedia/commons/8/85/Red_onions.jpg";
         String eggs="http://www.writtenchinese.com/wp-content/uploads/2013/09/Eggs.jpg";
         String carrot="https://www.agric.wa.gov.au/sites/gateway/files/carrot-1.jpg";
         String cabbage="http://topfoodfacts.com/wp-content/uploads/2013/01/cabbage.jpg";
		
		for(int i=0;i<400;i++)
		{
			  if(i%5==0)
			  {
				  Ingredient ing=new Ingredient("Ceapa",onions);
				  ing.setCantitate("12");
				  ing.setUnitate("buc");
				 ingrediente.add(ing);
				 
			  }
		          else
		       if(i%5==1)
		       {
	 				 ingrediente.add(new Ingredient("Rosii",tomato));
	 			  }
		       else
		    	   if(i%5==2)
		    	   {
		 				 ingrediente.add(new Ingredient("Oua",eggs));
		 			  }
		    	   else
		    		   if(i%5==3)
		    		   {
			 				 ingrediente.add(new Ingredient("Morcovi",carrot));
			 			  }
		    		   else
		    			   if(i%5==4)
		    			   {
				 				 ingrediente.add(new Ingredient("Varza",cabbage));
				 			  }
			
				}
		         fragmentView=inflater.inflate(R.layout.cauta_retete, container, false);
		         GridView imagini= (GridView)fragmentView.findViewById(R.id.lista_de_ingrediente);
		         CautaReteteGridAdaptor adaptor_grid= new CautaReteteGridAdaptor(getActivity(),ingrediente);
		         imagini.setAdapter(adaptor_grid);
		         imagini.setOnItemClickListener(new OnItemClickListener() {
			        public void onItemClick(AdapterView<?> parent, View v,
			                int position, long id) {	   
			        }
		         });
		         
		         Typeface tf = Typeface.createFromAsset(this.getActivity().getAssets(),
			                "fonts/Sunshine.ttf");
		         
		         time1=(CheckBox) fragmentView.findViewById(R.id.opt1);
		         time2=(CheckBox) fragmentView.findViewById(R.id.opt2);
		         time3=(CheckBox) fragmentView.findViewById(R.id.opt3);
		         dific1=(CheckBox) fragmentView.findViewById(R.id.opt_dif_1);
		         dific2=(CheckBox) fragmentView.findViewById(R.id.opt_dif_2);
		         dific3=(CheckBox) fragmentView.findViewById(R.id.opt_dif_3);
		         filtrare_ingrediente=(AutoCompleteTextView) fragmentView.findViewById(R.id.filtrare_ingrediente_cauta);
		         label1=(TextView) fragmentView.findViewById(R.id.label_1);
		         label2=(TextView) fragmentView.findViewById(R.id.label_2);
		         label3=(TextView) fragmentView.findViewById(R.id.label_3);
		         
		         
		         time1.setTypeface(tf);
		         time2.setTypeface(tf);
		         time3.setTypeface(tf);
		         dific1.setTypeface(tf);
		         dific2.setTypeface(tf);
		         dific3.setTypeface(tf);
		         filtrare_ingrediente.setTypeface(tf);
		         label1.setTypeface(tf);
		         label2.setTypeface(tf);
		         label3.setTypeface(tf);
		         
		         return fragmentView;
		    }
	 
	 @Override
     public void onAttach(Activity activity) {
         super.onAttach(activity);
         ((RootActivity) activity).onSectionAttached(
                 getArguments().getInt(ARG_SECTION_NUMBER));
     }

}