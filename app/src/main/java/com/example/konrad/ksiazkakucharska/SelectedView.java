package com.example.konrad.ksiazkakucharska;

import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.example.konrad.ksiazkakucharska.data.Recipe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

/**
 * Created by Konrad on 2015-01-16.
 */
@EActivity(R.layout.activity_selectrecipe)
public class SelectedView extends ActionBarActivity {

    @Extra
    Recipe recipe;

    @ViewById
    TextView title;
    @ViewById
    TextView introduction;
    @ViewById
    TextView ingredients;
    @ViewById
    TextView created;
    @ViewById
    TextView preparationMinutes;
    @ViewById
    TextView cookingMinutes;
    @ViewById
    TextView servings;

    @AfterViews
    void init() {
        title.setText(recipe.title);
        introduction.setText(recipe.introduction);
        ingredients.setText(recipe.ingredients);
        created.setText(recipe.created);
        preparationMinutes.setText(recipe.preparationMinutes);
        cookingMinutes.setText(recipe.cookingMinutes);
        servings.setText(recipe.servings);
    }
}
