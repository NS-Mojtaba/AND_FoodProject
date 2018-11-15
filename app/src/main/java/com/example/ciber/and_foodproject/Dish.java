package com.example.ciber.and_foodproject;

import android.widget.ImageView;

public class Dish {

    public String category;
    public String name;
    public String description;
    public ImageView image;

    public Dish(String _category, String _name, String _description){
        this.category = _category;
        this.name = _name;
        this.description = _description;
    }

    
}
