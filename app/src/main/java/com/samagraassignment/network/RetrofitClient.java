package com.samagraassignment.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.samagraassignment.model.Comment;
import com.samagraassignment.model.Photo;
import com.samagraassignment.model.Post;
import com.samagraassignment.model.Todo;

import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL ="https://jsonplaceholder.typicode.com/";

    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    Gson gson = new GsonBuilder().setLenient().create();
    private RetrofitClient() {
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();


        Gson gson = new GsonBuilder().setLenient().create();
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder().client(okHttpClient).baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson)).build();


    }


    public static synchronized RetrofitClient getInstance() {
        if (mInstance == null) {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public Api getApi() {
        return retrofit.create(Api.class);
    }

    public static ResponseBody responseBody(JSONObject jsonObject)
    {
        return ResponseBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString());
    }

    public Observable<List<Comment>> getComments() {
        return getApi().getComments();
    }

    public Observable<List<Photo>> getPhotos() {
        return getApi().getPhoto();
    }

    public Observable<List<Todo>> getTodos() {
        return getApi().getTodos();
    }

    public Observable<List<Post>> getPosts() {
        return getApi().getPosts();
    }
}