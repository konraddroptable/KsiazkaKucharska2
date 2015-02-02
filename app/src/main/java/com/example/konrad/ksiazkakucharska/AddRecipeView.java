package com.example.konrad.ksiazkakucharska;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.konrad.ksiazkakucharska.data.Recipe;
import com.example.konrad.ksiazkakucharska.data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;
import org.springframework.util.StringUtils;

/**
 * Created by Konrad on 2015-01-16.
 */
@EActivity(R.layout.activity_addrecipe)
public class AddRecipeView extends ActionBarActivity {
    //passed user
    @Extra
    User user;

    //background tasks
    @Bean
    @NonConfigurationInstance
    RestBackgroundAddRecipe restBackgroundAddRecipe;

    //region EditTexts
    @ViewById
    EditText title;
    @ViewById
    EditText introduction;
    @ViewById
    EditText ingredients;
    @ViewById
    EditText steps;
    @ViewById
    EditText servings;
    @ViewById
    EditText preparationMinutes;
    @ViewById
    EditText cookingMinutes;
    //endregion


    @AfterViews
    void init() {
    }


    @Click
    void addrecipeClicked(){
        //check for required fields
        //TODO: if doesn't work well...
        if(StringUtils.hasText(title.getText().toString()) == true &&
                StringUtils.hasText(ingredients.getText().toString()) == true &&
                StringUtils.hasText(steps.getText().toString()) == true &&
                StringUtils.hasText(servings.getText().toString()) == true){

            //new class
            Recipe recipe = new Recipe();
            recipe.title = title.getText().toString();
            recipe.introduction = introduction.getText().toString();
            recipe.ingredients = ingredients.getText().toString();
            recipe.steps = steps.getText().toString();
            recipe.servings = servings.getText().toString();
            recipe.preparationMinutes = preparationMinutes.getText().toString();
            recipe.cookingMinutes = cookingMinutes.getText().toString();
            //owner id
            recipe.ownerId = user.id;

            if(user.pictureId != null){
                recipe.pictureId = user.pictureId;
            } else{
                recipe.pictureId = null;
            }

            //POST operation
            restBackgroundAddRecipe.addRecipe(recipe,user);

            //go back to MainView intention and pass user instance
            MainView_.intent(this).user(user).start();
        } else{
            Toast.makeText(this,"Uzupełnij wymagane pola!",Toast.LENGTH_LONG).show();
        }
    }

    @Click
    void addPictureClicked(){
        PictureActivity_.intent(this).user(user).start();

    }

    public void showSuccess(){
        Toast.makeText(this,"Dodano przepis!",Toast.LENGTH_LONG).show();
    }

    public void showError(Exception e){
        Toast.makeText(this, "LOL, nie działa\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace(); //debug
    }
}
