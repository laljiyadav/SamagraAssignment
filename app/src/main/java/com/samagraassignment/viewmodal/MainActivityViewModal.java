package com.samagraassignment.viewmodal;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.samagraassignment.MainActivity;
import com.samagraassignment.db.DatabaseClient;
import com.samagraassignment.model.Comment;
import com.samagraassignment.model.Photo;
import com.samagraassignment.model.Post;
import com.samagraassignment.model.Todo;
import com.samagraassignment.network.RetrofitClient;
import com.samagraassignment.repository.TimeStamp;
import com.samagraassignment.utils.Utils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.samagraassignment.utils.Constant.COMMENTS;
import static com.samagraassignment.utils.Constant.PHOTOS;
import static com.samagraassignment.utils.Constant.POSTS;
import static com.samagraassignment.utils.Constant.TODOS;

public class MainActivityViewModal extends AndroidViewModel {

    public MainActivityViewModal(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> userMutableLiveData;


    public LiveData<String> getUser() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        getUserModal();
        return userMutableLiveData;
    }

    public MutableLiveData<TimeStamp> commentApiMutableLiveData;


    public LiveData<TimeStamp> getCommentsApiMutable() {
        if (commentApiMutableLiveData == null) {
            commentApiMutableLiveData = new MutableLiveData<>();
        }
        getComments();
        getPosts();
        getTODOS();
        getPHOTOS();
        return commentApiMutableLiveData;
    }



    public void getUserModal()
    {
        userMutableLiveData.setValue("Lalji Ydava");

    }


    public void getComments()
    {

        Long startTime= Utils.getUnixTimeStamp();
        TimeStamp timeStamp=new TimeStamp();
        timeStamp.setStartTime(startTime);
        timeStamp.setApiName("CLS");
//        txtStartTimeApiComment.setText("Start : "+startTime);


        RetrofitClient.getInstance().getComments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Comment>>() {
                    @Override
                    public void onNext(List<Comment> response) {
                        //Handle logic
//                        timeStamp.setEndTime(Utils.getUnixTimeStamp());
                        timeStamp.setEndTime(Utils.getUnixTimeStamp());

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle error
                    }

                    @Override
                    public void onComplete() {

                        commentApiMutableLiveData.setValue(timeStamp);

//                        txtStartTimeApiComment.setText("Start : "+startTime+"\n"+"End : "+Utils.getUnixTimeStamp());
                        saveTask(COMMENTS);

                    }
                });
    }

    public void getPosts()
    {

        Long startTime= Utils.getUnixTimeStamp();
        TimeStamp timeStamp=new TimeStamp();
        timeStamp.setStartTime(startTime);
        timeStamp.setApiName("CLS_POST");
//        txtStartTimeApiComment.setText("Start : "+startTime);


        RetrofitClient.getInstance().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Post>>() {
                    @Override
                    public void onNext(List<Post> response) {
                        //Handle logic
//                        timeStamp.setEndTime(Utils.getUnixTimeStamp());
                        timeStamp.setEndTime(Utils.getUnixTimeStamp());

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle error
                    }

                    @Override
                    public void onComplete() {

                        commentApiMutableLiveData.setValue(timeStamp);

//                        txtStartTimeApiComment.setText("Start : "+startTime+"\n"+"End : "+Utils.getUnixTimeStamp());
                        saveTask(POSTS);

                    }
                });
    }

    public void getTODOS()
    {

        Long startTime= Utils.getUnixTimeStamp();
        TimeStamp timeStamp=new TimeStamp();
        timeStamp.setStartTime(startTime);
        timeStamp.setApiName("CLS_TODOS");
//        txtStartTimeApiComment.setText("Start : "+startTime);


        RetrofitClient.getInstance().getTodos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Todo>>() {
                    @Override
                    public void onNext(List<Todo> response) {
                        //Handle logic
//                        timeStamp.setEndTime(Utils.getUnixTimeStamp());
                        timeStamp.setEndTime(Utils.getUnixTimeStamp());

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle error
                    }

                    @Override
                    public void onComplete() {

                        commentApiMutableLiveData.setValue(timeStamp);

//                        txtStartTimeApiComment.setText("Start : "+startTime+"\n"+"End : "+Utils.getUnixTimeStamp());
                        saveTask(TODOS);

                    }
                });
    }

    public void getPHOTOS()
    {

        Long startTime= Utils.getUnixTimeStamp();
        TimeStamp timeStamp=new TimeStamp();
        timeStamp.setStartTime(startTime);
        timeStamp.setApiName("CLS_PHOTOS");
//        txtStartTimeApiComment.setText("Start : "+startTime);


        RetrofitClient.getInstance().getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Photo>>() {
                    @Override
                    public void onNext(List<Photo> response) {
                        //Handle logic
//                        timeStamp.setEndTime(Utils.getUnixTimeStamp());
                        timeStamp.setEndTime(Utils.getUnixTimeStamp());

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle error
                    }

                    @Override
                    public void onComplete() {

                        commentApiMutableLiveData.setValue(timeStamp);

//                        txtStartTimeApiComment.setText("Start : "+startTime+"\n"+"End : "+Utils.getUnixTimeStamp());
                        saveTask(PHOTOS);

                    }
                });
    }



    private void saveTask(String api) {

        TimeStamp timeStamp=new TimeStamp();
        timeStamp.setStartTime(Utils.getUnixTimeStamp());
        timeStamp.setApiName(api);

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                timeStamp.setEndTime(Utils.getUnixTimeStamp());
                //adding to database
                DatabaseClient.getInstance(getApplication().getApplicationContext()).getAppDatabase()
                        .timeStampDao()
                        .insert(timeStamp);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
//                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

                getTasks(timeStamp.getStartTime(),timeStamp.getEndTime(),timeStamp.getStartTime(), api);
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }


    private void getTasks(Long start,Long end,Long startTime,String api) {
        class GetTasks extends AsyncTask<Void, Void, TimeStamp> {

            @Override
            protected TimeStamp doInBackground(Void... voids) {
                TimeStamp taskList = DatabaseClient
                        .getInstance(getApplication().getApplicationContext())
                        .getAppDatabase()
                        .timeStampDao()
                        .getTimeStamp(startTime,api);
                return taskList;
            }

            @Override
            protected void onPostExecute(TimeStamp tasks) {
                super.onPostExecute(tasks);

                commentApiMutableLiveData.setValue(tasks);
//                if (api.equalsIgnoreCase(COMMENTS))
//                {
////                    txtStartTimeComment.setText(
////                            " Start Save : "+tasks.getStartTime()+" \n"+"End Save : "+tasks.getEndTime());
//                    commentApiMutableLiveData.setValue(tasks);
//                }else if (api.equalsIgnoreCase(POSTS))
//                {
//                   commentApiMutableLiveData.setValue();
//                }else if (api.equalsIgnoreCase(PHOTOS))
//                {
//
//                }else if (api.equalsIgnoreCase(TODOS))
//                {
//
//                }


            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

}
