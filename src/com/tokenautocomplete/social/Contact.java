package com.tokenautocomplete.social;

import java.io.Serializable;

public class Contact implements Serializable{
    private String name;
    private String email;
    private int icon;

    public Contact(String n, String e,int ic) {
        name = n;
        email = e;
        this.icon = ic;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getIcon() { return icon; }

    @Override
    public String toString() { return name; }
}
