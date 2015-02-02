package com.example.konrad.ksiazkakucharska.itemView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.konrad.ksiazkakucharska.R;
import com.example.konrad.ksiazkakucharska.Utility;
import com.example.konrad.ksiazkakucharska.data.Recipe;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.util.Random;

/**
 * Created by Konrad on 2015-01-15.
 */

@EViewGroup(R.layout.list_item)
public class RecipeItemView extends RelativeLayout {
    @ViewById
    TextView title;
    @ViewById
    TextView introduction;
    @ViewById
    TextView displayName;
    @ViewById
    ImageView avatar;


    public RecipeItemView(Context context){
        super(context);
    }



    public void bind(Recipe recipe){
        title.setText(recipe.title);
//        created.setText(recipe.created.toString());
        introduction.setText(recipe.introduction);

        if(recipe.displayName != null) {
            displayName.setText(recipe.displayName);
        } else{
            displayName.setText(null);
        }

        if(recipe.pictureBytes != null){
            Utility.decodeAndSetImage(recipe.pictureBytes, avatar);
            avatar.setColorFilter(0xffffffff, PorterDuff.Mode.MULTIPLY);
        } else {
            avatar.setImageDrawable(getResources().getDrawable(R.drawable.cooking_pot));
            Utility.colorFilter(avatar);
            //Utility.colorFilter(avatar); //set ImageView color filters
        }

//        try {
//            acronym.setText(recipe.title.substring(0, 1).toUpperCase());
//        } catch (StringIndexOutOfBoundsException e){
//            acronym.setText("E");
//        }
    }

}
