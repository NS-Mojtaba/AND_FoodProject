package com.example.ciber.and_foodproject;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintHelper;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SwipeAdapter extends PagerAdapter {


    private ArrayList<Upload> images;
    private String[] names;
    private String[] descriptions;

    private Context context;
    private LayoutInflater layoutInflater;
    private ImageAdapter imageAdapter;

    public SwipeAdapter(){

    }
    public SwipeAdapter(Context context, ArrayList<Dish> dishes) {
        this.context = context;
        setAllArrays(dishes);
        //this.imageAdapter = new ImageAdapter(this.context,images);
    }

    public void setImageArray(ArrayList<Upload> _images){
        images = _images;
    }
    public void setNameArray(String[] _names){
        names = new String[_names.length];
        names = _names;
    }
    public void setDescriptionArray(String[] _descriptions){
        descriptions = new String[_descriptions.length];
        descriptions = _descriptions;
    }

    public void setAllArrays(ArrayList<Dish> list){
        String [] name = new String[list.size()];
        String [] desc = new String[list.size()];
        ArrayList<Upload> imgUrl = new ArrayList<Upload>();
        for (int i=0; i<list.size(); i++){
            name[i] = list.get(i).name;
            desc[i] = list.get(i).description;
            imgUrl.add(new Upload(list.get(i).name, list.get(i).imageURL));
        }
        setNameArray(name);
        setDescriptionArray(desc);
        setImageArray(imgUrl);
    }
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.image_view);
        TextView textName = (TextView)view.findViewById(R.id.name);
        TextView textDescription = (TextView)view.findViewById(R.id.des);

        Glide.with(context)
                //.load(images.get(position).getImageUrl())
                .load(images.get(position).getImageUrl())
                .into(imageView);

        textName.setText(names[position]);
        textDescription.setText(descriptions[position]);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout)object);
    }

}