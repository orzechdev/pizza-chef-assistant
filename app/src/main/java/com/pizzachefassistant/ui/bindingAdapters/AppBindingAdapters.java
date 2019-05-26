package com.pizzachefassistant.ui.bindingAdapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public class AppBindingAdapters {

    @BindingAdapter("imgSrc")
    public static void setImageResource(ImageView imageView, Integer resource){
        if (resource != null) {
            imageView.setImageResource(resource);
        }
    }

}
