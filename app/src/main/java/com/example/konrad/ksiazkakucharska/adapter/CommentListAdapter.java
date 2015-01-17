package com.example.konrad.ksiazkakucharska.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.konrad.ksiazkakucharska.data.Comment;
import com.example.konrad.ksiazkakucharska.data.CommentList;
import com.example.konrad.ksiazkakucharska.itemView.CommentItemView;
import com.example.konrad.ksiazkakucharska.itemView.CommentItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 2015-01-17.
 */

@EBean
public class CommentListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    List<Comment> comments = new ArrayList<Comment>();

    public CommentListAdapter() {
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Comment getItem(int i) {
        return comments.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommentItemView commentItemView;
        if (convertView == null) {
            commentItemView = CommentItemView_.build(context);
        } else {
            commentItemView = (CommentItemView) convertView;
        }

        commentItemView.bind(getItem(position));

        return commentItemView;
    }

    public void update(CommentList commentList) {
        comments.clear();
        comments.addAll(commentList.records);
        notifyDataSetChanged();
    }
}
