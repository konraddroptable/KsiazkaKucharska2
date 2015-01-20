package com.example.konrad.ksiazkakucharska;

import com.example.konrad.ksiazkakucharska.data.Comment;
import com.example.konrad.ksiazkakucharska.data.CommentList;
import com.example.konrad.ksiazkakucharska.data.CookBook;
import com.example.konrad.ksiazkakucharska.data.EmailAndPassword;
import com.example.konrad.ksiazkakucharska.data.Like;
import com.example.konrad.ksiazkakucharska.data.LikeList;
import com.example.konrad.ksiazkakucharska.data.Recipe;
import com.example.konrad.ksiazkakucharska.data.RegisterNew;
import com.example.konrad.ksiazkakucharska.data.User;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Post;
import org.androidannotations.annotations.rest.RequiresHeader;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientHeaders;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by Konrad on 2015-01-15.
 */
@Rest(rootUrl = "http://pegaz.wzr.ug.edu.pl:8080/rest",
        converters = { MappingJackson2HttpMessageConverter.class })
@RequiresHeader({"X-Dreamfactory-Application-Name"})
public interface CookbookRestClient extends RestClientHeaders {

    //Post for registering new user
    @Post("/user/register/?login=true")
    RegisterNew registerNewUser(RegisterNew registerNew);


    //Get&Post for selecting/adding recipes
    @Get("/db/recipes")
    CookBook getCookBook();

    @RequiresHeader({"X-Dreamfactory-Application-Name","X-Dreamfactory-Session-Token"})
    @Post("/db/recipes")
    void addRecipe(Recipe recipe);



    //region Get&Post for selecting/adding comments
    @Get("/db/comments?filter={path}")
    CommentList getComment(String path);

    @RequiresHeader({"X-Dreamfactory-Application-Name","X-Dreamfactory-Session-Token"})
    @Post("/db/comments")
    void addComment(Comment comment);
    //endregion

    //region Get&Post likes
    @Get("/db/likes?filter={path}")
    LikeList getLike(String path);

    @RequiresHeader({"X-Dreamfactory-Application-Name","X-Dreamfactory-Session-Token"})
    @Post("/db/likes")
    void setLike(Like like);
    //endregion



    //Login for adding recipes/comments
    @Post("/user/session")
    User login(EmailAndPassword emailAndPassword);
}
