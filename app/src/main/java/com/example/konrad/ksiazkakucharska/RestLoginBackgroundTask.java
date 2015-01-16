package com.example.konrad.ksiazkakucharska;

import com.example.konrad.ksiazkakucharska.data.EmailAndPassword;
import com.example.konrad.ksiazkakucharska.data.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Konrad on 2015-01-15.
 */
@EBean
public class RestLoginBackgroundTask {
    @RootContext
    LoginActivity activity;
    @RestService
    CookbookRestClient restClient;

    //login in background
    @Background
    void login(EmailAndPassword emailAndPassword){
        try{
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");

            User user = restClient.login(emailAndPassword);
            publishResult(user);
        } catch(Exception e) {
            publishError(e);
        }
    }

    @UiThread
    void publishResult(User user){
        activity.loginSuccess(user);
    }
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }

}
