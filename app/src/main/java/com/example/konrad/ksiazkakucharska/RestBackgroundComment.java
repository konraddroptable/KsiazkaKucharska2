package com.example.konrad.ksiazkakucharska;

import com.example.konrad.ksiazkakucharska.data.Comment;
import com.example.konrad.ksiazkakucharska.data.CommentList;
import com.example.konrad.ksiazkakucharska.data.CookBook;
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
public class RestBackgroundComment {
    @RootContext
    SelectedView activity; //activity with listview

    @RestService
    CookbookRestClient restClient; //use that cool interface


    //load recipes in background withoud blocking main application ;-)
    @Background
    void getComment(String path, User user){
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            CommentList commentList = restClient.getComment(path);
            publishResult(commentList);


            if(user != null) {
                List<Integer> list = new ArrayList<Integer>();
                String userIds = null;
                //get unique IDs
                for (Comment comment : commentList.records) {
                    if (list.contains(comment.ownerId) == false || list.size() == 0) {
                        list.add(comment.ownerId);
                        userIds += comment.ownerId + ",";
                    }
                }

                //String preparation
                String ids = userIds.substring(0,userIds.length()-1);

                //get
                restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
                restClient.setHeader("X-Dreamfactory-Session-Token", user.sessionId);
                UserList userList = restClient.getUserId(ids);

                //}
                //update display names
                //if(userList != null) {
                for (Comment comment : commentList.records) {
                    for (int i = 0; i < userList.records.size(); i++) {
                        if (userList.records.get(i).id == comment.ownerId)
                            comment.displayName = userList.records.get(i).displayName;
                    }
                }
                //}
            }
//            update display names
//            for(Comment comment: commentList.records){
//                if(user != null){
//                    restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
//                    restClient.setHeader("X-Dreamfactory-Session-Token", user.sessionId);
//                    User user1 = restClient.getUserId(comment.ownerId);
//                    comment.displayName = user1.displayName;
//                }
//            }

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
    @UiThread
    void publishError(Exception e){
        activity.showError(e);
    }



}
