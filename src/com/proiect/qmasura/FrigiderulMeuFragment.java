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

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.proiect.qmasura.obiecte.DetailedGeneralIngredient;
import com.proiect.qmasura.obiecte.Ingredient;
import com.proiect.qmasura.obiecte.SummaryReteta;
import com.proiect.qmasura.sqlite.DbHelper;
import com.proiect.qmasura.utilitare.ClasaUtilitara;

public class FrigiderulMeuFragment extends Fragment {
	View fragmentView;
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ArrayList<Ingredient> ingredienteDisponibile;
	private ArrayList<DetailedGeneralIngredient> ingredienteGenerale;
	FrigiderulMeuGridAdaptor adaptor_grid;
	private Map<Integer,Ingredient> all_ingredients;
	
	GridView imagini;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setHasOptionsMenu(true);
	    }
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 		all_ingredients= new HashMap<Integer,Ingredient>();
	 		
		 		ingredienteDisponibile= new ArrayList<Ingredient>();
		 		DbHelper db_helper= new DbHelper(this.getActivity());
		 		ingredienteDisponibile=db_helper.ingredienteDinFrigider();
		 		ingredienteGenerale= db_helper.obtineIngredienteGenerale();
		 		db_helper.close();
		 	
		 		for(int i=0;i<ingredienteDisponibile.size(); i++){
		 			all_ingredients.put(i,ingredienteDisponibile.get(i));
		 		}
		 		
		        fragmentView=inflater.inflate(R.layout.frigiderul_meu_view, container, false);
		         imagini= (GridView)fragmentView.findViewById(R.id.frigiderul_meu);
					 adaptor_grid= new FrigiderulMeuGridAdaptor(getActivity(),ingredienteDisponibile,this);
					imagini.setAdapter(adaptor_grid);
					imagini.setOnItemClickListener(new OnItemClickListener() {
				        public void onItemClick(AdapterView<?> parent, View v,
				                int position, long id) {
				        	final Ingredient selectat= (Ingredient)parent.getItemAtPosition(position);
				        	
				        	AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

				        	alert.setTitle(selectat.getGeneral_name());
				        	alert.setMessage("Actualizare cantitate");

				        	// Set an EditText view to get user input 
				        	final EditText input = new EditText(getActivity());
				        	input.setRawInputType(Configuration.KEYBOARD_12KEY);
						       
				        	input.setText(selectat.getCantitate()+"");
				        	alert.setView(input);

				        
				        	
				        	alert.setPositiveButton("Salveaza", new DialogInterface.OnClickListener() {
				        	public void onClick(DialogInterface dialog, int whichButton) {
					        	 if(input.getText().toString().isEmpty())
					     			Toast.makeText(getActivity(), "Adaugati o cantitate", Toast.LENGTH_SHORT).show();
					     		 else{
					     			try{
					     				float cantit= Float.parseFloat(input.getText().toString());
					     				selectat.setCantitate(cantit);
					     				selectat.display();
					     				DbHelper db_helper= new DbHelper(getActivity());
					     				db_helper.actualizeazaCantitateIngredientFrigider(selectat);
					     				 adaptor_grid.notifyDataSetChanged();
					     				 imagini.invalidateViews();
					     			} catch (NumberFormatException e){
					     				Toast.makeText(getActivity(), "Cantitatea introdusa nu este corecta", Toast.LENGTH_SHORT).show();
					     			}
					     		}
				        	}
				        	});

				        	alert.setNegativeButton("Anuleaza", new DialogInterface.OnClickListener() {
				        	  public void onClick(DialogInterface dialog, int whichButton) {
				        	    // Canceled.
				        	  }
				        	});

				        	alert.show();
				        }
				    });
		         return fragmentView;
		    }

	 
	 
	 
	 public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		    // Inflate the menu items for use in the action bar
		 	inflater = this.getActivity().getMenuInflater();
		    inflater.inflate(R.menu.meniu_frigider, menu);
		    super.onCreateOptionsMenu(menu,inflater);
		     
		}
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     // Handle presses on the action bar items
	     switch (item.getItemId()) {
	         case R.id.adauga_ingredient:
	         {
	        	 final FragmentTransaction ft = getFragmentManager().beginTransaction(); 
	        	 AdaugaIngredientFragment fragment= new AdaugaIngredientFragment();
	        	 Bundle args= new Bundle();
	        	 args.putSerializable("ingrediente", ingredienteGenerale);
	        	 fragment.setArguments(args);
	        	 ft.replace(R.id.container, fragment, "AdaugaIngredient"); 
	        	 ft.addToBackStack("Adauga ingredient");
	        	 ft.commit(); 
	        	
	            return true;
	         }
	         case R.id.sugereaza_retete:{
	        	 SearchReceipes cauta_async;
				cauta_async= new SearchReceipes(getActivity(),all_ingredients);
				cauta_async.execute();
	         }
	         default:
	             return super.onOptionsItemSelected(item);
	     }
	 }
	 
	 @Override
     public void onAttach(Activity activity) {
         super.onAttach(activity);
         ((RootActivity) activity).onSectionAttached(
                 getArguments().getInt(ARG_SECTION_NUMBER));
     }
	 
	 public void refresh(){
		 adaptor_grid.notifyDataSetChanged();
		 imagini.invalidateViews();
	 }
	 
	 public void removeIngr(int pos){
		 ingredienteDisponibile.remove(pos);
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
					StringBuilder url=new StringBuilder();
					url.append("https://qmasura-ruby.herokuapp.com/api/recipes/calculate?");
					
					Iterator it = ingrediente.entrySet().iterator();
		            while (it.hasNext()) {
		            	Log.i("SEARCH", "INGREDIENT LA CAUTARE");		            	
		            	url.append("frig%5B");
		                Map.Entry pairs = (Map.Entry)it.next();
		                Ingredient ing=(Ingredient)pairs.getValue();
		                ing.display();
		                url.append(ing.getGeneral_name());
		                url.append("%5D%5Bcantitate%5D=");
		                url.append(ing.getCantitate());
		                url.append("&");
		                url.append("frig%5B");
		                url.append(ing.getGeneral_name());
		                url.append("%5D%5Bum_id%5D=");
		                url.append(ing.getUm_id());
		                if(it.hasNext())
		                	url.append("&");
		            }
		            HttpGet httppost = new HttpGet(url.toString().replace(" ", "%20"));
		            
		            Log.i("TEST","CAUTA URL "+url.toString().replace(" ", "%20"));
		            
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


