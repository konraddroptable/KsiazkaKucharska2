package com.example.konrad.ksiazkakucharska;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.konrad.ksiazkakucharska.adapter.RecipeListAdapter;
import com.example.konrad.ksiazkakucharska.data.CookBook;
import com.example.konrad.ksiazkakucharska.data.Recipe;
import com.example.konrad.ksiazkakucharska.data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_list)
@OptionsMenu(R.menu.login)
public class MainView extends ActionBarActivity {

    @Extra
    User user;
    @ViewById
    ListView list;
    @Bean
    RecipeListAdapter adapter;

    @Bean
    @NonConfigurationInstance
    RestBackgroundTask restBackgroundTask;

    ProgressDialog ringProgressDialog;

    @AfterViews
    void init(){
        list.setAdapter(adapter);
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładowanie...");
        ringProgressDialog.setIndeterminate(true);
//        Toast.makeText(this,user.sessionId,Toast.LENGTH_LONG).show();
    }

    @ItemClick
    void listItemClicked(Recipe recipe){
//        Toast.makeText(this, item.title,Toast.LENGTH_SHORT).show();
        SelectedView_.intent(this).recipe(recipe).start();
    }

    @Click
    void refreshClicked(){
        ringProgressDialog.show();
        restBackgroundTask.getCookBook();
    }

    public void updateCookbook(CookBook cookBook){
        ringProgressDialog.dismiss();
        adapter.update(cookBook);
    }

    public void showError(Exception e){
        ringProgressDialog.dismiss();
        Toast.makeText(this, "LOL, nie działa\n" + e.getMessage(), Toast.LENGTH_LONG).show();
        e.printStackTrace(); //debug
    }

//    @OptionsItem
//    void settingsSelected(){
//
//    }



//region On... methods - DO NOT DELETE THIS
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion
    //##############################################
}
