package com.example.konrad.ksiazkakucharska;

import com.example.konrad.ksiazkakucharska.data.Comment;
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
public class RestBackgroundTask {
    @RootContext
    MainView activity; //activity with listview
    @RootContext
    SelectedView selectedView;

    @RestService
    CookbookRestClient restClient; //use that cool interface


    //load recipes in background withoud blocking main application ;-)
    @Background
    void getCookBook(){
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            CookBook cookBook = restClient.getCookBook();
            publishResult(cookBook);
        } catch (Exception e){ //TODO: avoid using that form of catching exceptions
            publishError(e);
        }
    }


    @Background
    void getComment(){
        try{
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            CommentList commentList = restClient.getComment();
            publishComment(commentList);
        } catch (Exception e){
            errorComment(e);
        }
    }

    //background updating
    @UiThread
    void publishResult(CookBook cookBook){
        activity.updateCookbook(cookBook);
    }
    //background updating -> sth failed
    void publishError(Exception e){
        activity.showError(e);
    }


    @UiThread
    void publishComment(CommentList commentList){
        selectedView.updateComments(commentList);
    }
    void errorComment(Exception e){
        selectedView.showError(e);
    }

}