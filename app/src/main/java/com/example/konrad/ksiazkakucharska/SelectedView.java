package com.example.konrad.ksiazkakucharska;

import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.konrad.ksiazkakucharska.adapter.CommentListAdapter;
import com.example.konrad.ksiazkakucharska.data.Comment;
import com.example.konrad.ksiazkakucharska.data.CommentList;
import com.example.konrad.ksiazkakucharska.data.Recipe;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
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

    @Bean
    @NonConfigurationInstance
    RestBackgroundComment restBackgroundComment;

    @Bean
    CommentListAdapter adapter;

    @ViewById
    ListView listComment;

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
    TextView preparationMinutes;
    @ViewById
    TextView cookingMinutes;
    @ViewById
    TextView servings;
//endregion

    @AfterViews
    void init() {
        listComment.setAdapter(adapter);

        title.setText(recipe.title);
        introduction.setText(recipe.introduction);
        ingredients.setText(recipe.ingredients);
        created.setText(recipe.created);
        preparationMinutes.setText(recipe.preparationMinutes);
        cookingMinutes.setText(recipe.cookingMinutes);
        servings.setText(recipe.servings);

        restBackgroundComment.getComment();
    }

    public void updateComments(CommentList commentList){
        CommentList commentList1 = new CommentList();
        ListIterator listIterator = commentList.records.listIterator();

        if(commentList != null) {
            while (listIterator.hasNext()) {
                Comment comment = (Comment) listIterator.next();

                if (comment.recipeId == recipe.id)
                    commentList1.records.add(comment);
            }

            if (commentList1 != null) {
                adapter.update(commentList1);
            }
        }
    }

    public void showError(Exception e){
        Toast.makeText(this, "LOL, nie działa\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace(); //debug
    }
}
