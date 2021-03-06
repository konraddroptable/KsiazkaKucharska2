package com.example.konrad.ksiazkakucharska;

import android.app.ProgressDialog;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konrad.ksiazkakucharska.adapter.CommentListAdapter;
import com.example.konrad.ksiazkakucharska.data.Comment;
import com.example.konrad.ksiazkakucharska.data.CommentList;
import com.example.konrad.ksiazkakucharska.data.Like;
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

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Konrad on 2015-01-16.
 */
@EActivity(R.layout.activity_selectrecipe)
public class SelectedView extends ActionBarActivity {
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
    RestBackgroundComment restBackgroundComment;

    @Bean
    @NonConfigurationInstance
    RestBackgroundLike restBackgroundLike;
    //endregion

    @Bean
    CommentListAdapter adapter;

    //region list with comments
    @ViewById
    ListView listComment;
    //endregion

    //buttons
    @ViewById
    Button comment;
    @ViewById
    Button like;
    //imageview
    @ViewById
    ImageView avatar;

//region TextVievs
    @ViewById
    TextView title;
    @ViewById
    TextView introduction;
    @ViewById
    TextView ingredients;
    @ViewById
    TextView created;
    @ViewById
    TextView steps;
    @ViewById
    TextView preparationMinutes;
    @ViewById
    TextView cookingMinutes;
    @ViewById
    TextView servings;
    @ViewById
    TextView likes;
    @ViewById
    TextView author;
//endregion

    @AfterViews
    void init() {
        listComment.setAdapter(adapter);

        //unpack bundle
        recipe = (Recipe)bundle.getSerializable("recipe");
        user = (User)bundle.getSerializable("user");

        //process dialog
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładowanie...");
        ringProgressDialog.setIndeterminate(true);

        //for loading comments
        ringProgressDialog.show();

        //set textviews
        title.setText(recipe.title);
        introduction.setText(recipe.introduction);
        ingredients.setText(recipe.ingredients);
        created.setText("Utworzono: " + recipe.created);

        if(recipe.preparationMinutes != null) {
            preparationMinutes.setText(recipe.preparationMinutes + "min.");
        } else{
            preparationMinutes.setText(null);
        }

        if(recipe.cookingMinutes != null) {
            cookingMinutes.setText(recipe.cookingMinutes + "min.");
        } else{
            cookingMinutes.setText(null);
        }

        servings.setText(recipe.servings);
        steps.setText(recipe.steps);

        //set display name
        if(recipe.displayName != null){
            author.setText("Autor: " + recipe.displayName.toString());
        } else{
            author.setText(null);
        }

        //set imageview
        if(recipe.pictureBytes != null){
            Utility.decodeAndSetImage(recipe.pictureBytes, avatar);
        } else{
            //avatar.setImageDrawable(getResources().getDrawable(R.drawable.cooking_pot));
            avatar.setImageDrawable(getResources().getDrawable(R.drawable.cooking_pot));
            Utility.colorFilter(avatar);
        }

        //fill comments and likes
        restBackgroundComment.getComment("recipeId=" + Integer.toString(recipe.id), user);
        restBackgroundLike.getLike("recipeId=" + Integer.toString(recipe.id));

    }

    @Click
    void likeClicked(){
        //check if user is logged in
        if(user == null) {
            LoginActivity_.intent(this).user(user).start();
        } else {
            ringProgressDialog.show();
            //add like to the recipe
            restBackgroundLike.setLike(user, recipe);
            //refresh like's
            restBackgroundLike.getLike("recipeId=" + Integer.toString(recipe.id));
        }
    }

    @Click
    void commentClicked(){
        //check if user is logged in
        if(user == null) {
            LoginActivity_.intent(this).user(user).start();
        } else {
            //pack bundle to another activity
            Bundle bundle = new Bundle();
            bundle.putSerializable("recipe",recipe);
            bundle.putSerializable("user",user);

            //go to commentview activity with packed bundle
            CommentView_.intent(this).bundle(bundle).start();
        }
    }

    @Click
    void homeClicked(){
        //check if user is logged in
        if(user == null) {
            MainView_.intent(this).start();
        } else {
            MainView_.intent(this).user(user).start();
        }
    }

    public void updateComments(CommentList commentList){
        if(commentList != null) {
//            ListIterator listIterator = commentList.records.listIterator();
//            CommentList commentList1 = new CommentList();
//
//            while (listIterator.hasNext()) {
//                Comment comment = (Comment) listIterator.next();
//
//                if (comment.recipeId == recipe.id) {
//                    commentList1.records.add(comment);
//                }
//            }
//
//            if (commentList1 != null) {
                //adapter.update(commentList1);
                adapter.update(commentList);

                //set ListView height to number of items

                Utility.getListViewSize(listComment);

                //stop progress dialog after loading comments
                ringProgressDialog.dismiss();
        }
//        }
    }


    public void updateLikes(LikeList likeList){
//        if(likeList != null){
//            ListIterator listIterator = likeList.records.listIterator();
//            int counter = 0;
//
//            while(listIterator.hasNext()){
//                Like like = (Like) listIterator.next();
//
//                if(like.recipeId == recipe.id) {
//                    counter++;
//                }
//            }
//
//            if(counter != 0){
//                likes.setText(String.valueOf(counter) + " osób lubi to.");
//            }
//        }
        if(likeList != null){
            likes.setText(Integer.toString(likeList.records.size()) + " osób lubi to.");
        }
        //stop progressdialog after loading likes
        ringProgressDialog.dismiss();

    }


    public void showError(Exception e){
        Toast.makeText(this, "LOL, nie działa\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace(); //debug
    }
}
