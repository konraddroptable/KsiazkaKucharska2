package com.example.konrad.ksiazkakucharska;

import com.example.konrad.ksiazkakucharska.data.CommentList;
import com.example.konrad.ksiazkakucharska.data.Like;
import com.example.konrad.ksiazkakucharska.data.LikeList;
import com.example.konrad.ksiazkakucharska.data.Recipe;
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
public class RestBackgroundLike {
    @RootContext
    SelectedView activity; //activity with listview

    @RestService
    CookbookRestClient restClient; //use that cool interface


    //load recipes in background without blocking main application ;-)
    @Background
    void getLike(String path){
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            LikeList likeList = restClient.getLike(path);
            publishResult(likeList);
        } catch (Exception e){ //TODO: avoid using that form of catching exceptions
            publishError(e);
        }
    }

    @Background
    void setLike(User user, Recipe recipe){
        try{
            //rest client headers for POST
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            restClient.setHeader("X-Dreamfactory-Session-Token", user.sessionId);

            //new class
            Like like = new Like();
            like.recipeId = recipe.id;
            like.ownerId = user.id;

            //add like
            restClient.setLike(like);

            //refresh
            getLike("recipeId=" + Integer.toString(recipe.id));
        } catch(Exception e){
            publishError(e);
        }
    }

    //background updating
    @UiThread
    void publishResult(LikeList likeList){
        activity.updateLikes(likeList);
}
    //background updating -> sth failed
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }



}
