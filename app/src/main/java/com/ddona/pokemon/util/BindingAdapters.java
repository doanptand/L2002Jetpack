package com.ddona.pokemon.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.ddona.pokemon.R;

public class BindingAdapters {

    @BindingAdapter("set_src")
    public static void setImage(ImageView imgView, String link){
        Glide.with(imgView).load(link)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(imgView);
    }
}
