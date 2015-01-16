package com.example.konrad.ksiazkakucharska.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.konrad.ksiazkakucharska.data.CookBook;
import com.example.konrad.ksiazkakucharska.data.Recipe;
import com.example.konrad.ksiazkakucharska.itemView.RecipeItemView;
import com.example.konrad.ksiazkakucharska.itemView.RecipeItemView_;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 2015-01-15.
 */
@EBean
public class RecipeListAdapter extends BaseAdapter {

    @RootContext
    Context context;

    List<Recipe> recipes = new ArrayList<Recipe>();

    public RecipeListAdapter() {
    }

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Recipe getItem(int i) {
        return recipes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RecipeItemView recipeItemView;
        if (convertView == null) {
            recipeItemView = RecipeItemView_.build(context);
        } else {
            recipeItemView = (RecipeItemView) convertView;
        }

        recipeItemView.bind(getItem(position));

        return recipeItemView;
    }

    public void update(CookBook cookBook) {
        recipes.clear();
        recipes.addAll(cookBook.records);
        notifyDataSetChanged();
    }
}
