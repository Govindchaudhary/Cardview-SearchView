package com.example.android.mycardview;

/**
 * Created by Dell on 3/26/2017.
 */

public class Country {
    private String name;
    private  int flag_id;
    public Country(String name,int flag_id) {
        this.setName(name);
        this.setFlag_id(flag_id);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlag_id() {
        return flag_id;
    }

    public void setFlag_id(int flag_id) {
        this.flag_id = flag_id;
    }
}
