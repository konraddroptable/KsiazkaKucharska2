package com.example.konrad.ksiazkakucharska;

import com.example.konrad.ksiazkakucharska.data.CommentList;
import com.example.konrad.ksiazkakucharska.data.CookBook;
import com.example.konrad.ksiazkakucharska.data.EmailAndPassword;
import com.example.konrad.ksiazkakucharska.data.Recipe;
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

    //Get&Post for selecting/adding recipes
    @Get("/db/recipes")
    CookBook getCookBook();
    @Post("/db/recipes")
    void addCookBookEntry(Recipe recipe);

    //TODO:Get&Post for selecting/adding comments
    @Get("db/comments")
    CommentList getComment();
//    @Post("db/comments/{recipeId}")
//    void addComment(Comment comment);

    //Login for adding recipes/comments
    @Post("/user/session")
    User login(EmailAndPassword emailAndPassword);
}
