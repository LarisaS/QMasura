package com.proiect.qmasura;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import com.proiect.qmasura.obiecte.Ingredient;
import com.proiect.qmasura.obiecte.Reteta;
import com.proiect.qmasura.obiecte.SummaryReteta;
import com.proiect.qmasura.obiecte.UnitatiDeMasura;
import com.proiect.qmasura.sqlite.DbHelper;
import com.proiect.qmasura.utilitare.ClasaUtilitara;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CautaReteteFragment  extends Fragment {
	View fragmentView;
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ArrayList<Ingredient> ingrediente;
	private CheckBox time1,time2,time3,dific1,dific2,dific3;
	private AutoCompleteTextView filtrare_ingrediente;
	private TextView label1,label2,label3;
	private Button cauta;
	private Map<Integer,Ingredient> selectate;
	
	/*****dummy******/
	ArrayList<Reteta> retete;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setHasOptionsMenu(true);
	    }
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 		ingrediente= new ArrayList<Ingredient>();
		 		selectate= new HashMap<Integer,Ingredient>();
		 		DbHelper db_helper= new DbHelper(this.getActivity());
		 		ingrediente=db_helper.ingredienteDinFrigider();
			 	 db_helper.close();
				String tomato = "http://www.foodproductiondaily.com/var/plain_site/storage/images/publications/food-beverage-nutrition/foodproductiondaily.com/innovations/packaging-made-from-tomato-waste/8593675-1-eng-GB/Packaging-made-from-tomato-waste.jpg";
		        String onions="http://upload.wikimedia.org/wikipedia/commons/8/85/Red_onions.jpg";
		        String eggs="http://www.writtenchinese.com/wp-content/uploads/2013/09/Eggs.jpg";
		        String carrot="https://www.agric.wa.gov.au/sites/gateway/files/carrot-1.jpg";
		        String cabbage="http://topfoodfacts.com/wp-content/uploads/2013/01/cabbage.jpg";
				retete= new ArrayList<Reteta>();
				
				/*for(int i=0;i<10;i++)
				{
					Reteta r= new Reteta("Sugestie Reteta numarul " +(i+1));
					if(i%4==0)
					r.setPoza("https://c2.staticflickr.com/2/1317/1469874334_737335c623.jpg");
					else
						if(i%4==1)
							r.setPoza("http://resiliencefitness.com/wp-content/uploads/2013/09/salad.jpg");
						else
							if(i%4==2)
								r.setPoza("http://mixedgreensblog.com/wp-content/uploads/2010/04/green-goddess-soup.jpg");
							else 
								if(i%4==3)
									r.setPoza("http://steamykitchen.com/wp-content/uploads/2010/03/garlic-herb-steak.jpg");
				
					retete.add(r);
				
				}*/
		
		         fragmentView=inflater.inflate(R.layout.cauta_retete, container, false);
		         
		         cauta=(Button)fragmentView.findViewById(R.id.cauta_retete_action);
		         
		         final GridView imagini= (GridView)fragmentView.findViewById(R.id.lista_de_ingrediente);
		         CautaReteteGridAdaptor adaptor_grid= new CautaReteteGridAdaptor(getActivity(),ingrediente);
		         imagini.setAdapter(adaptor_grid);
		         imagini.setOnItemClickListener(new OnItemClickListener() {
			        public void onItemClick(AdapterView<?> parent, View v,
			                int position, long id) {	
			        	Ingredient sel=(Ingredient)imagini.getAdapter().getItem(position);
			        	if(selectate.containsKey(position))
			        	{	
			        		selectate.remove(position);
			        		RelativeLayout rl=(RelativeLayout)v.findViewById(R.id.search_ingredient_overlay_gradient);
			        		rl.setBackgroundResource(R.drawable.overlay_gradient);
			        	}
			        	else
			        	{
			        		selectate.put(position,sel);
			        		RelativeLayout rl=(RelativeLayout)v.findViewById(R.id.search_ingredient_overlay_gradient);
			        		rl.setBackgroundResource(R.drawable.selected_gradient);
			        	}
			        	
			        	Iterator it = selectate.entrySet().iterator();
			            while (it.hasNext()) {
			                Map.Entry pairs = (Map.Entry)it.next();
			                Ingredient ing=(Ingredient)pairs.getValue();
			                ing.display();
			            }
			        }
		         });
		         
		         Typeface tf = Typeface.createFromAsset(this.getActivity().getAssets(),
			                "fonts/Sunshine.ttf");
		         
		         cauta.setTypeface(tf);
		         cauta.setOnClickListener(new View.OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						SearchReceipes cauta_async= new SearchReceipes(getActivity(),selectate);
						cauta_async.execute();
					}});
		         
		         /*time1=(CheckBox) fragmentView.findViewById(R.id.opt1);
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
		         label3.setTypeface(tf);*/
		         
		         return fragmentView;
		    }
	 
	 @Override
     public void onAttach(Activity activity) {
         super.onAttach(activity);
         ((RootActivity) activity).onSectionAttached(
                 getArguments().getInt(ARG_SECTION_NUMBER));
     }
	 private class SearchReceipes extends  AsyncTask<Void,String,Integer>
	    {

	    	private Context context; 
	    	private ProgressDialog pd;
	    	private Map<Integer,Ingredient> ingrediente;
	    	public SearchReceipes(Context context,Map ingr)
	    	{
	    		this.context=context;
	    		this.ingrediente=ingr;
	    	}
	    	

	    	protected void onPreExecute() {
	    	
	    		super.onPreExecute();
	    	    pd = ProgressDialog.show(context, "Cautam retete",
	    	              "Va rugam asteptati.."); 
	    	    
	    	    Log.i("Cauta Retete", "onPreExecute"); 
	    	    
	    	} 
	    	
	    	protected void onPostExecute(Integer result){ 
	    	pd.dismiss();
	    	  Log.i("Cauta Retete", "on post execute"); 
	    	}
	    	 
	    	protected void onProgressUpdate(String... text) {
	    		  Log.i("Cauta Retete", "on progress update"); 
	    	 } 
	    	
			@Override
			protected Integer doInBackground(Void... params) {
				// TODO Auto-generated method stub
			Log.i("Cauta Retete", "do in background"); 
			HttpClient httpclient = new DefaultHttpClient();
			try {
					HttpGet httppost = new HttpGet("https://qmasura-ruby.herokuapp.com/api/recipes/calculate");	 
					HttpResponse rez = httpclient.execute(httppost);
					String s= ClasaUtilitara.getStringFromJson(rez.getEntity());				
					Log.i("update DB", "listare retete "+s);
					JSONArray retete= new JSONArray(s);
					ArrayList<SummaryReteta> retete_gasite= new ArrayList<SummaryReteta>();
					for(int j=0;j<retete.length();j++)
					{
						Log.i("update DB", "pas "+j+"1");
						JSONObject reteta_json=retete.getJSONObject(j);
						Log.i("update DB", "pas "+j+"2");
						SummaryReteta um=ClasaUtilitara.getSummaryRetetaFromJSON(reteta_json);
						um.display();
						Log.i("update DB", "pas "+j+"3");
						retete_gasite.add(um);
					}
					final FragmentTransaction ft = getFragmentManager().beginTransaction(); 
		        	 ListareReteteFragment fragment= new ListareReteteFragment();
		        	 Bundle args= new Bundle();
		        	 args.putSerializable("retete", retete_gasite);// aici se vor transmite retetele sugerate
		        	 fragment.setArguments(args);
		        	 ft.replace(R.id.container, fragment, "ReteteSugerate"); 
		        	 ft.addToBackStack("Retete sugerate");
		        	 ft.commit(); 
			}
			catch(Exception e)
			{
					Log.i("EXCEPTIE", e.getLocalizedMessage());
			}
			finally{
				
			}
			return 1;
			}
	    	
	    	
	    }
	    

}