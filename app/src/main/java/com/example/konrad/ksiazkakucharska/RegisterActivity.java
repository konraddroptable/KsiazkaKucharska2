package com.example.konrad.ksiazkakucharska;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.konrad.ksiazkakucharska.data.EmailAndPassword;
import com.example.konrad.ksiazkakucharska.data.RegisterNew;
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
 * Created by Konrad on 2015-01-15.
 */
@EActivity(R.layout.activity_register)
public class RegisterActivity extends ActionBarActivity {
    @Bean
    @NonConfigurationInstance
    RestBackgroundRegister restBackgroundRegister;

    @ViewById
    EditText email;
    @ViewById
    EditText firstName;
    @ViewById
    EditText lastName;
    @ViewById
    EditText newPassword;
    @ViewById
    CheckBox autoSignIn;

    ProgressDialog ringProgressDialog;

    @AfterViews
    void init() {
        //auto sign-in selected by default
        autoSignIn.setChecked(true);

        ringProgressDialog = new ProgressDialog(this);
        ringProgressDialog.setMessage("Ładowanie...");
        ringProgressDialog.setIndeterminate(true);
    }

    @Click
    void registerClicked(){
        if(StringUtils.hasText(email.getText().toString()) == true &&
                StringUtils.hasText(firstName.getText().toString()) == true &&
                StringUtils.hasText(lastName.getText().toString()) == true &&
                StringUtils.hasText(newPassword.getText().toString()) == true) {
            ringProgressDialog.show();
            //new class, fill class
            RegisterNew registerNew = new RegisterNew();
            boolean flag;

            registerNew.firstName = firstName.getText().toString();
            registerNew.lastName = lastName.getText().toString();
            registerNew.email = email.getText().toString();
            registerNew.newPassword = newPassword.getText().toString();

            //rest background task
            restBackgroundRegister.registerNewUser(registerNew);
        } else{
            Toast.makeText(this, "Uzupełnij wymagane pola",Toast.LENGTH_LONG).show();
        }
        //start MainView intent -> pass user class to the MainView intent

    }


    public void registerSuccess(User user){
        ringProgressDialog.dismiss();
        if(autoSignIn.isChecked() == true) {
            MainView_.intent(this).user(user).start();
            Toast.makeText(this, "Zarejestrowano:\n" + user.displayName, Toast.LENGTH_LONG).show();
        } else{
            MainView_.intent(this).start();
            Toast.makeText(this, "Zarejestrowano:\n" + user.displayName, Toast.LENGTH_LONG).show();
        }
    }

    public void showError(Exception e){
        ringProgressDialog.dismiss();
        Toast.makeText(this,"LOL, nie działa\n" + e.getMessage(),Toast.LENGTH_LONG).show();
    }

}
