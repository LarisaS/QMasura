package com.proiect.qmasura;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.proiect.qmasura.obiecte.Ingredient;
import com.proiect.qmasura.obiecte.UnitatiDeMasura;
import com.proiect.qmasura.sqlite.DbHelper;
import com.proiect.qmasura.utilitare.ClasaUtilitara;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class RootActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.root_view);

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
		.cacheOnDisc(true).cacheInMemory(true)
		.imageScaleType(ImageScaleType.EXACTLY)
		.displayer(new FadeInBitmapDisplayer(300)).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions)
				.memoryCache(new WeakMemoryCache())
				.discCacheSize(100 * 1024 * 1024).build();
		
		ImageLoader.getInstance().init(config);
        
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        Log.i("AAAAAAAAAAAAA","on create -aaa");
        UpdateDB updateDb= new UpdateDB(this);
        updateDb.execute();
        
    }
    
    
    private class UpdateDB extends  AsyncTask<Void,String,Integer>
    {

    	private Context context; 
    	private ProgressDialog pd;
    	
    	public UpdateDB(Context context)
    	{
    		this.context=context;
    	}
    	

    	protected void onPreExecute() {
    	
    		super.onPreExecute();
    	    pd = ProgressDialog.show(context, "Verificam datele",
    	              "Va rugam asteptati.."); 
    	    
    	    Log.i("update DB", "onPreExecute"); 
    	    
    	} 
    	
    	protected void onPostExecute(Integer result){ 
    	pd.dismiss();
    	}
    	 
    	protected void onProgressUpdate(String... text) {
    		  Log.i("update DB", "on progress update"); 
    	 } 
    	
		@Override
		protected Integer doInBackground(Void... params) {
			// TODO Auto-generated method stub
		Log.i("AAAAAAAAAAAAA","on create -aaa");
		Log.i("update DB", "do in background"); 
		DbHelper db_helper= new DbHelper(context);
		HttpClient httpclient = new DefaultHttpClient();
		try {
			String ultima_updatare_um=db_helper.dataUpdateUnitatiMasura();
			Log.i("update DB", "Ultima updatare:"+ultima_updatare_um); 
			if(!ultima_updatare_um.isEmpty() && ultima_updatare_um.equals("0"))
			{
				HttpGet httppost = new HttpGet("https://qmasura-ruby.herokuapp.com/api/unities/listAll");	 
				HttpResponse rez = httpclient.execute(httppost);
				String s= ClasaUtilitara.getStringFromJson(rez.getEntity());				
				Log.i("update DB", "listare unitati de masura "+s);
				JSONArray unitati_array= new JSONArray(s);
				ArrayList<UnitatiDeMasura> ums= new ArrayList<UnitatiDeMasura>();
				for(int j=0;j<unitati_array.length();j++)
				{
					JSONObject unit_obj=unitati_array.getJSONObject(j);
					UnitatiDeMasura um=ClasaUtilitara.getUnitateFromJSON(unit_obj);
					ums.add(um);
				}
				db_helper.populeazaUnitatiDeMasura(ums);
				db_helper.actualizeazaTimestampUnitati();
			}
			else
			{
				Log.i("update DB", "unitati de masura");
				ArrayList<UnitatiDeMasura> units= db_helper.unitatileDeMasura();
				for(int i=0;i<units.size();i++)
					units.get(i).display();
			}
			/*
				HttpPost httppost = new HttpPost("https://qmasura-ruby.herokuapp.com/api/ingredients/listAll");	 
				HttpResponse rez = httpclient.execute(httppost);
				String s= ClasaUtilitara.getStringFromJson(rez.getEntity());
				JSONArray ingredients_array= new JSONArray(s);
				for(int j=0;j<ingredients_array.length();j++)
				{
					JSONObject ingredient_obj=ingredients_array.getJSONObject(j);
					Ingredient i= new Ingredient(ingredient_obj);
				}
				*/
		}
		catch(Exception e)
		{
				Log.i("EXCEPTIE", e.getMessage());
		}
		finally{
			db_helper.close();
		}
		return 1;
		}
    	
    	
    }
    

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        int number= position+1;
      
        switch(number)
        {
        case 1:  
        {	
            Bundle args = new Bundle();
            args.putInt("section_number", number);
            HomeScreenFragment fragment= new HomeScreenFragment();
            fragment.setArguments(args);
            fragmentManager.beginTransaction().replace(R.id.container, fragment ).commit();
            break;  
         }
        case 2: 
        {	
           Bundle args = new Bundle();
           args.putInt("section_number", number);
           CautaReteteFragment fragment= new CautaReteteFragment();
           fragment.setArguments(args);
           fragmentManager.beginTransaction().replace(R.id.container, fragment ).commit();
           break;  
        }
        case 3:
        	{
        		 Bundle args = new Bundle();
                 args.putInt("section_number", number);
                 FrigiderulMeuFragment fragment=new FrigiderulMeuFragment() ;
                 fragment.setArguments(args);
        		 fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
                 break;
        	}
        case 4:
        	{
        		Bundle args = new Bundle();
                args.putInt("section_number", number);
                ListareMeniuriFragment fragment=new ListareMeniuriFragment() ;
                fragment.setArguments(args);
        		fragmentManager.beginTransaction() .replace(R.id.container, fragment ).commit();
                break;
                
        	}
        default:  
        	{
        		 Bundle args = new Bundle();
                 args.putInt("section_number", number);
                 HomeScreenFragment fragment= new HomeScreenFragment();
                 fragment.setArguments(args);
                 fragmentManager.beginTransaction().replace(R.id.container, fragment ).commit();
                 
        	}
        }
        
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.home_screen, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
      /*  int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }*/
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.placeholder_fragment, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((RootActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
