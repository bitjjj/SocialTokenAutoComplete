package com.tokenautocomplete.social.demo;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.socialtokenautocomplete.R;
import com.tokenautocomplete.FilteredArrayAdapter;
import com.tokenautocomplete.social.Contact;
import com.tokenautocomplete.social.SocialContactsCompletionView;

public class SocialTokenActivity extends Activity {
    SocialContactsCompletionView completionView;
    Contact[] people;
    ArrayAdapter<Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        people = new Contact[]{
                new Contact("Marshall Weir", "marshall@example.com",R.drawable.author0),
                new Contact("Margaret Smith", "margaret@example.com",R.drawable.author1),
                new Contact("Max Jordan", "max@example.com",R.drawable.author2),
                new Contact("Meg Peterson", "meg@example.com",R.drawable.author3),
                new Contact("Amanda Johnson", "amanda@example.com",R.drawable.author4),
                new Contact("Terry Anderson", "terry@example.com",R.drawable.author0)
        };

        adapter = new FilteredArrayAdapter<Contact>(this, R.layout.contact_layout, people) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {

                    LayoutInflater l = (LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                    convertView = (View)l.inflate(R.layout.contact_layout, parent, false);
                }

                Contact p = getItem(position);
                ((TextView)convertView.findViewById(R.id.name)).setText(p.getName());
                ((ImageView)convertView.findViewById(R.id.icon_image)).setBackgroundResource(p.getIcon());
                ((TextView)convertView.findViewById(R.id.email)).setText(p.getEmail());

                return convertView;
            }

            @Override
            protected boolean keepObject(Contact obj, String mask) {
                mask = mask.toLowerCase(Locale.getDefault());

                return mask.contains("@") && obj.getName().toLowerCase(Locale.getDefault()).startsWith(mask.substring(mask.lastIndexOf("@")+1));
            }
        };

        completionView = (SocialContactsCompletionView)findViewById(R.id.searchView);
        completionView.setAdapter(adapter);

        if (savedInstanceState == null) {
            //completionView.setPrefix("To: ");
            //completionView.addObject(people[0]);
            //completionView.addObject(people[1]);
        }

      
        
    }

    private void updateTokenConfirmation() {
        StringBuilder sb = new StringBuilder("Current tokens:\n");
        for (Object token: completionView.getObjects()) {
            sb.append(token.toString());
            sb.append("\n");
        }
    }


   
}
