package com.tokenautocomplete.social;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.socialtokenautocomplete.R;
import com.tokenautocomplete.TokenCompleteTextView;

public class SocialContactsCompletionView extends TokenCompleteTextView {

	private final String AT = "@";
	
    public SocialContactsCompletionView(Context context) {
        super(context);
    }

    public SocialContactsCompletionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SocialContactsCompletionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    
    @Override
    protected int beforeReplacingText(Editable editable,int start,int end){
    	return !editable.toString().contains(AT) ? start : editable.toString().lastIndexOf(AT);
    }

    @Override
    protected View getViewForObject(Object object) {
    	LayoutInflater l = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    	
    	if(!(object instanceof Contact)){
    		TextView view = (TextView)l.inflate(R.layout.default_textview, (ViewGroup)SocialContactsCompletionView.this.getParent(), false);
    		view.setText(object.toString());
    		return view;
    	}
    	
    	else{
	        Contact p = (Contact)object;
	
	        LinearLayout view = (LinearLayout)l.inflate(R.layout.contact_token, (ViewGroup)SocialContactsCompletionView.this.getParent(), false);
	        
	        ((ImageView)view.findViewById(R.id.icon_image_token)).setBackgroundResource(p.getIcon());
	        ((TextView)view.findViewById(R.id.name_token)).setText(p.getName());
	        return view;
    	}
    }

    @Override
    protected Object defaultObject(String completionText) {
       
    	return completionText;
    }
}
