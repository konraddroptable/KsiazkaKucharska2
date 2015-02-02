package com.example.konrad.ksiazkakucharska;

import com.example.konrad.ksiazkakucharska.adapter.RecipeListAdapter;
import com.example.konrad.ksiazkakucharska.data.CommentList;
import com.example.konrad.ksiazkakucharska.data.CookBook;
import com.example.konrad.ksiazkakucharska.data.EmailAndPassword;
import com.example.konrad.ksiazkakucharska.data.Picture;
import com.example.konrad.ksiazkakucharska.data.Recipe;
import com.example.konrad.ksiazkakucharska.data.User;
import com.example.konrad.ksiazkakucharska.data.UserList;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Konrad on 2015-01-15.
 */

@EBean
public class RestBackgroundTask {
    @RootContext
    MainView activity; //activity with listview

    @RestService
    CookbookRestClient restClient; //use that cool interface


    //load recipes in background withoud blocking main application ;-)
    @Background
    void getCookBook(User user){
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            CookBook cookBook = restClient.getCookBook();

            if(user != null) {
                List<Integer> list = new ArrayList<Integer>();
                String userIds = null;
                //get unique IDs
                for (Recipe recipe : cookBook.records) {
                    if (list.contains(recipe.ownerId) == false) {
                        list.add(recipe.ownerId);
                        userIds += recipe.ownerId + ",";
                    }
                }
                userIds += 3 + ","; //fixes problem with example@example.com :-)

                //String preparation
                String ids = userIds.substring(0,userIds.length()-1);

                //get
                restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
                restClient.setHeader("X-Dreamfactory-Session-Token", user.sessionId);
                UserList userList = restClient.getUserId(ids);

                for (Recipe recipe : cookBook.records) {
                    for (int i = 0; i < userList.records.size(); i++) {
                        if (userList.records.get(i).id == recipe.ownerId)
                            recipe.displayName = userList.records.get(i).displayName;
                    }
                }
            }
            //refresh adapter
            publishResult(cookBook);

            //update pictures
            for(Recipe recipe: cookBook.records){
                if (recipe.pictureId != null){
                    Picture picture = restClient.getPictreById(recipe.pictureId);
                    recipe.pictureBytes = picture.base64bytes;
                }
            }


        } catch (Exception e){ //TODO: avoid using that form of catching exceptions
            publishError(e);
        }
    }

    //background updating
    @UiThread
    void publishResult(CookBook cookBook){
        activity.updateCookbook(cookBook);
    }
    //background updating -> sth failed
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }



}
