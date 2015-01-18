package com.example.konrad.ksiazkakucharska;

import com.example.konrad.ksiazkakucharska.data.CommentList;
import com.example.konrad.ksiazkakucharska.data.LikeList;

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


    //load recipes in background withoud blocking main application ;-)
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
