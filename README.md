SocialTokenAutoComplete
=======================

In social app, you often @somebody for something. This project gives you a more visual way to @somebody and show the message.


This project is modified from https://github.com/splitwise/TokenAutoComplete and is compitable with TokenAutoComplete. You can see a demo in SocialTokenActivity. If you need update some styles, you can easily modify these xml files in layout folder.


![demo](https://github.com/bitjjj/SocialTokenAutoComplete/blob/master/demo.png)


An Example:

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
            
        }
       
    }
    

Demo
====
[![Demo](https://camo.githubusercontent.com/dc1ffe0e4d25c2c28a69423c3c78000ef7ee96bf/68747470733a2f2f646576656c6f7065722e616e64726f69642e636f6d2f696d616765732f6272616e642f656e5f6170705f7267625f776f5f34352e706e67)](https://play.google.com/store/apps/details?id=com.socialtokenautocomplete)


    
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SocialTokenAutoComplete-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1004)
