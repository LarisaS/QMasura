package com.proiect.qmasura.utilitare;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class NewGridItem extends RelativeLayout {

	 public NewGridItem(Context context) {
	        super(context);
	        
	    }
	    public NewGridItem(Context context, AttributeSet attrs, int defStyle)
	    {
	    super(context, attrs, defStyle); 
	    }
	    public NewGridItem(Context context, AttributeSet attrs) {
	    super(context, attrs); 
	    }
	  
	
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, widthMeasureSpec);
	}

}
