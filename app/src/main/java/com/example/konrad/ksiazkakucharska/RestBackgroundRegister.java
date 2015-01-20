package com.example.konrad.ksiazkakucharska;

import com.example.konrad.ksiazkakucharska.data.Like;
import com.example.konrad.ksiazkakucharska.data.LikeList;
import com.example.konrad.ksiazkakucharska.data.Recipe;
import com.example.konrad.ksiazkakucharska.data.RegisterNew;
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
public class RestBackgroundRegister {
    @RootContext
    RegisterActivity activity; //activity with listview

    @RestService
    CookbookRestClient restClient; //use that cool interface


    @Background
    void registerNewUser(RegisterNew registerNew){
        try{
            //rest client headers for POST
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");

            //new class, fill output (user) class
            RegisterNew registerNew1 = restClient.registerNewUser(registerNew);
            User user = new User();
            user.id = registerNew1.id;
            user.displayName = registerNew1.displayName;
            user.firstName = registerNew1.firstName;
            user.lastName = registerNew1.lastName;
            user.sessionId = registerNew1.sessionId; //most important;

            publishResult(user); //pass user to publishResult
        } catch(Exception e){
            publishError(e);
        }
    }

    //background updating
    @UiThread
    void publishResult(User user){
        activity.registerSuccess(user);
}
    //background updating -> sth failed
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }



}
