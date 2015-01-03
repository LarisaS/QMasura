package com.proiect.qmasura;

import java.util.ArrayList;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.proiect.qmasura.obiecte.Ingredient;
import com.proiect.qmasura.obiecte.Reteta;
import com.proiect.qmasura.obiecte.SummaryReteta;
import com.proiect.qmasura.utilitare.ClasaUtilitara;

public class ListareReteteFragment extends Fragment {
	View fragmentView;
	private static final String ARG_SECTION_NUMBER = "section_number";
	private ArrayList<SummaryReteta> retete;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 
		 			if(!this.getArguments().isEmpty() && this.getArguments().containsKey("retete"))
		 					retete=(ArrayList<SummaryReteta>) this.getArguments().getSerializable("retete");
		 			else
		 					retete= new ArrayList<SummaryReteta>();
		 	
		            fragmentView=inflater.inflate(R.layout.listare_retete, container, false);
		         	final GridView imagini= (GridView)fragmentView.findViewById(R.id.lista_retete_sugerate);
					ListareReteteAdaptor adaptor_grid= new ListareReteteAdaptor(getActivity(),retete);
					imagini.setAdapter(adaptor_grid);
					imagini.setClickable(true);
					imagini.setOnItemClickListener(new OnItemClickListener() {
				        public void onItemClick(AdapterView<?> parent, View v,
				                int position, long id) {	  
				        	SummaryReteta reteta_selectata= (SummaryReteta)imagini.getAdapter().getItem(position);
				        	RetreiveRecipe cautaRetetaAsync = new RetreiveRecipe(getActivity(),reteta_selectata);
				        	cautaRetetaAsync.execute();
				        }
				    });
		         return fragmentView;
		    }

	 
	 
	 
	 public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		    // Inflate the menu items for use in the action bar
		 
		    super.onCreateOptionsMenu(menu,inflater);
		     
		}
	 
	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	     // Handle presses on the action bar items
	     return super.onOptionsItemSelected(item);
	 }
	 
	 @Override
     public void onAttach(Activity activity) {
         super.onAttach(activity);
         ((RootActivity) activity).onSectionAttached(
                 getArguments().getInt(ARG_SECTION_NUMBER));
     }

	 private class RetreiveRecipe extends  AsyncTask<Void,String,Integer>
	    {

	    	private Context context; 
	    	private ProgressDialog pd;
	    	private SummaryReteta reteta;
	    	private Reteta reteta_gasita;
	    	public RetreiveRecipe(Context context,SummaryReteta reteta)
	    	{
	    		this.context=context;
	    		this.reteta=reteta;
	    	}
	    	

	    	protected void onPreExecute() {
	    	
	    		super.onPreExecute();
	    	    pd = ProgressDialog.show(context, "Incropim reteta",
	    	              "Va rugam asteptati.."); 
	    	    
	    	    Log.i("Descriere Reteta", "onPreExecute"); 
	    	    
	    	} 
	    	
	    	protected void onPostExecute(Integer result){ 
	    	pd.dismiss();
	    	  Log.i("Descriere Reteta", "on post execute"); 
	    	}
	    	 
	    	protected void onProgressUpdate(String... text) {
	    		  Log.i("Descriere Reteta", "on progress update"); 
	    	 } 
	    	
			@Override
			protected Integer doInBackground(Void... params) {
				// TODO Auto-generated method stub
			Log.i("Descriere Reteta", "do in background"); 
			HttpClient httpclient = new DefaultHttpClient();
			try {
					Log.i("Reteta","url reteta :"+"https://qmasura-ruby.herokuapp.com/api/recipes/find?recipe[id]="+reteta.getId());
					HttpGet httppost = new HttpGet("https://qmasura-ruby.herokuapp.com/api/recipes/find?recipe[id]="+reteta.getId());	 
					HttpResponse rez = httpclient.execute(httppost);
					String s= ClasaUtilitara.getStringFromJson(rez.getEntity());				
					Log.i("update DB", "listare retete "+s);
					JSONArray reteta_array= new JSONArray(s);
					JSONObject reteta_obj= reteta_array.getJSONObject(0);
					
					Reteta aux_reteta=ClasaUtilitara.getRetetaFromJSON(reteta_obj);
					aux_reteta.setIngredienteLipsa(reteta.getIngredienteLipsa());
					Log.i("DEBUG","LIPSA ");
					for(int i=0;i<aux_reteta.getIngredienteLipsa().size();i++)
					{
					aux_reteta.getIngredienteLipsa().get(i).display();
					}
					reteta_gasita=aux_reteta;
					Log.i("Reteta Gasita",reteta_gasita.getIngredienteLipsa().size()+"");
					final FragmentTransaction ft = getFragmentManager().beginTransaction(); 
		        	DescriereRetetaFragment fragment= new DescriereRetetaFragment();
		        	Bundle args= new Bundle();
		        	args.putSerializable("reteta", aux_reteta);// aici se vor transmite retetele sugerate
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


