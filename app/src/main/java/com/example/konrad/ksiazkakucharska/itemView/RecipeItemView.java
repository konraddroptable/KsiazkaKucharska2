package com.example.konrad.ksiazkakucharska.itemView;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.konrad.ksiazkakucharska.R;
import com.example.konrad.ksiazkakucharska.data.Recipe;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

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
    TextView acronym;

    public RecipeItemView(Context context){
        super(context);
    }

    public void bind(Recipe recipe){
        title.setText(recipe.title);
//        created.setText(recipe.created.toString());
        introduction.setText(recipe.introduction);
        acronym.setText(recipe.title.substring(0,1));
}
}
