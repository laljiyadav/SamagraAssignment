package com.samagraassignment.network;
import com.google.gson.JsonObject;
import com.samagraassignment.model.Comment;
import com.samagraassignment.model.Photo;
import com.samagraassignment.model.Post;
import com.samagraassignment.model.Todo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface Api {

   @GET("comments")
   Observable<List<Comment>> getComments();

   @GET("photos")
   Observable<List<Photo>> getPhoto();

   @GET("todos")
   Observable<List<Todo>> getTodos();

   @GET("posts")
   Observable<List<Post>> getPosts();


}
