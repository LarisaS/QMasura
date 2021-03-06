package com.proiect.qmasura;

import com.proiect.qmasura.utilitare.ClasaUtilitara;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class HomeScreenFragment extends Fragment {
	private View myFragmentView;
	private static final String ARG_SECTION_NUMBER = "section_number";

	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
		        Bundle savedInstanceState) {
		        // Inflate the layout for this fragment
		 		myFragmentView=inflater.inflate(R.layout.home_screen_view, container, false);
		        ImageView bg_img=(ImageView) myFragmentView.findViewById(R.id.home_bg_image);
		        bg_img.setImageResource(ClasaUtilitara.imagineHomePage());
		        return myFragmentView;
		    }
	 
	 @Override
     public void onAttach(Activity activity) {
         super.onAttach(activity);
         ((RootActivity) activity).onSectionAttached(
                 getArguments().getInt(ARG_SECTION_NUMBER));
     }

}
