package com.example.konrad.ksiazkakucharska.itemView;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.konrad.ksiazkakucharska.R;
import com.example.konrad.ksiazkakucharska.data.Comment;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Konrad on 2015-01-17.
 */

@EViewGroup(R.layout.list_comment)
public class CommentItemView extends RelativeLayout {
    @ViewById
    TextView ownerId;
    @ViewById
    TextView created;
    @ViewById
    TextView text;


    public CommentItemView(Context context){
        super(context);
    }

    public void bind(Comment comment){

        //show display name if user is signed in
        if(comment.displayName != null){
            ownerId.setText(comment.displayName);
        } else {
            ownerId.setText(String.valueOf(comment.ownerId));
        }

        created.setText(comment.created);
        text.setText(comment.text);
    }
//        created.setText(recipe.created.toString());
}
