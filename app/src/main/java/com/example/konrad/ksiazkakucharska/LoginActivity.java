package com.example.konrad.ksiazkakucharska;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.konrad.ksiazkakucharska.data.EmailAndPassword;
import com.example.konrad.ksiazkakucharska.data.Recipe;
import com.example.konrad.ksiazkakucharska.data.User;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

/**
 * Created by Konrad on 2015-01-15.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends ActionBarActivity {
    @Bean
    @NonConfigurationInstance
    RestLoginBackgroundTask restLoginBackgroundTask;

    @ViewById
    EditText email;
    @ViewById
    EditText password;

    ProgressDialog ringProgressDialog;

    //Bundle
//    @Extra
//    Bundle bundle;
//    @Extra
//    Recipe recipe;
    @Extra
    User user;

    @AfterViews
    void init() {
        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładowanie...");
        ringProgressDialog.setIndeterminate(true);
    }

    @Click
    void loginClicked(){
        ringProgressDialog.show();
        EmailAndPassword emailAndPassword = new EmailAndPassword();
        emailAndPassword.email = email.getText().toString(); //example@example.com
        emailAndPassword.password = password.getText().toString(); //test00
        restLoginBackgroundTask.login(emailAndPassword);
    }

    @Click
    void startRegisterClicked(){
        RegisterActivity_.intent(this).start();
    }

    public void loginSuccess(User user){
        ringProgressDialog.dismiss();
        MainView_.intent(this).user(user).start();
        Toast.makeText(this, "Zalogowano", Toast.LENGTH_LONG).show();
    }

    public void showError(Exception e){
        ringProgressDialog.dismiss();
        Toast.makeText(this, "LOL, nie działa\n" + e.getMessage(),Toast.LENGTH_LONG).show();
        e.printStackTrace();
    }
}
