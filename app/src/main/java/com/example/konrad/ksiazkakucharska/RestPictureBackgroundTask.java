package com.example.konrad.ksiazkakucharska;


import com.example.konrad.ksiazkakucharska.data.Picture;
import com.example.konrad.ksiazkakucharska.data.ResultWithId;
import com.example.konrad.ksiazkakucharska.data.User;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

/**
 * Created by Konrad on 2015-01-21.
 */

@EBean
public class RestPictureBackgroundTask {
    @RootContext
    PictureActivity pictureActivity;

    @RestService
    CookbookRestClient restClient;

    @Background
    void addPicture(String pictureBytes, User apiUser) {
        try {
            Picture picture = new Picture();
            picture.ownerId = apiUser.id;
            picture.base64bytes = pictureBytes;
            restClient.setHeader("X-Dreamfactory-Application-Name", "phonebook");
            restClient.setHeader("X-Dreamfactory-Session-Token", apiUser.sessionId);
            ResultWithId result = restClient.addPicture(picture);
            publishAddPictureSuccess(result.id);
        } catch (Exception e) {
            publishError(e);
        }
    }

    @UiThread
    void publishAddPictureSuccess(int id) {
        pictureActivity.pictureAdded(id);
    }

    @UiThread
    void publishError(Exception e) {
        pictureActivity.addPictureFailed(e);
    }
}
