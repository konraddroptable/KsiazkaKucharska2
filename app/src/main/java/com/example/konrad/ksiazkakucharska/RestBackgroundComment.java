package com.example.konrad.ksiazkakucharska;

import com.example.konrad.ksiazkakucharska.data.CommentList;
import com.example.konrad.ksiazkakucharska.data.CookBook;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Konrad on 2015-01-15.
 */

@EBean
public class RestBackgroundComment {
    @RootContext
    SelectedView activity; //activity with listview

    @RestService
    CookbookRestClient restClient; //use that cool interface


    //load recipes in background withoud blocking main application ;-)
    @Background
    void getComment(){
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            CommentList commentList = restClient.getComment();
            publishResult(commentList);
        } catch (Exception e){ //TODO: avoid using that form of catching exceptions
            publishError(e);
        }
    }

    //background updating
    @UiThread
    void publishResult(CommentList commentList){
        activity.updateComments(commentList);
}
    //background updating -> sth failed
    void publishError(Exception e){
        activity.showError(e);
    }



}
