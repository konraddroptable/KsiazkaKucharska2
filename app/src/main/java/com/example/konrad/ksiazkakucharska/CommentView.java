package com.example.konrad.ksiazkakucharska;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konrad.ksiazkakucharska.adapter.CommentListAdapter;
import com.example.konrad.ksiazkakucharska.data.CommentList;
import com.example.konrad.ksiazkakucharska.data.LikeList;
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
@EActivity(R.layout.activity_addcomment)
public class CommentView extends ActionBarActivity {
    @Extra
    Recipe recipe;
    @Extra
    User user;

    @Extra
    Bundle bundle;

    //process dialog
    ProgressDialog ringProgressDialog;

    //region background tasks
    @Bean
    @NonConfigurationInstance
    RestBackgroundAddComment restBackgroundAddComment;

    @ViewById
    EditText text;

//region TextVievs
    @ViewById
    TextView title;
    @ViewById
    TextView introduction;
//endregion

    @AfterViews
    void init() {
        //process dialog
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładowanie...");
        ringProgressDialog.setIndeterminate(true);

        //unpack bundle
        recipe = (Recipe)bundle.getSerializable("recipe");
        user = (User)bundle.getSerializable("user");

        //set textviews
        title.setText(recipe.title);
        introduction.setText(recipe.introduction);
    }


    @Click
    void addcommentClicked(){
        //check if comment is null
        if (!StringUtils.hasText(text.getText().toString())) {
            Toast.makeText(this,"Uzupełnij brakujące pola!",Toast.LENGTH_LONG).show();
        } else {
            //POST operation
            restBackgroundAddComment.addComment(user, recipe, text.getText().toString());

            //pass bundle to SelectedView activity
            Bundle bundle = new Bundle();
            bundle.putSerializable("recipe", recipe);
            bundle.putSerializable("user", user);

            //go back to SelectedView
            SelectedView_.intent(this).bundle(bundle).start();
        }
    }


    public void showSuccess(){
        Toast.makeText(this,"Dodano komentarz!",Toast.LENGTH_LONG).show();
    }

    public void showError(Exception e){
        Toast.makeText(this, "LOL, nie działa\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace(); //debug
    }
}
