package com.samagraassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.samagraassignment.db.DatabaseClient;
import com.samagraassignment.model.Comment;
import com.samagraassignment.model.Photo;
import com.samagraassignment.model.Post;
import com.samagraassignment.model.Todo;
import com.samagraassignment.network.RetrofitClient;
import com.samagraassignment.repository.TimeStamp;
import com.samagraassignment.utils.Constant;
import com.samagraassignment.utils.Utils;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.samagraassignment.utils.Constant.COMMENTS;
import static com.samagraassignment.utils.Constant.PHOTOS;
import static com.samagraassignment.utils.Constant.POSTS;
import static com.samagraassignment.utils.Constant.TODOS;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    TextView txtStartTimeComment,txtStartTimeApiComment,txtStartTimePost,txtStartTimeApiPost,txtStartTimeTodo,txtStartTimeApiTodo,txtStartTimePhoto,txtStartTimeApiPhoto;
    Button btnComments,btnPost,btnUnixTimestamp,btnPhotos,btnTodos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtStartTimeComment=findViewById(R.id.txtStartTimeComment);
        txtStartTimeApiComment=findViewById(R.id.txtStartTimeApiComment);

        txtStartTimePost=findViewById(R.id.txtStartTimePost);
        txtStartTimeApiPost=findViewById(R.id.txtStartTimeApiPost);
        txtStartTimeTodo=findViewById(R.id.txtStartTimeTodo);
        txtStartTimePhoto=findViewById(R.id.txtStartTimePhoto);
        txtStartTimeApiTodo=findViewById(R.id.txtStartTimeApiTodo);
        txtStartTimeApiPhoto=findViewById(R.id.txtStartTimeApiPhoto);

        btnComments=findViewById(R.id.btnComments);
        btnPost=findViewById(R.id.btnPost);
        btnUnixTimestamp=findViewById(R.id.btnUnixTimestamp);
        btnPhotos=findViewById(R.id.btnPhotos);
        btnTodos=findViewById(R.id.btnTodos);



        getComments();
        getPhotos();
        getTodos();
        getPosts();

        btnComments.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        btnPhotos.setOnClickListener(this);
        btnTodos.setOnClickListener(this);

    }


    private void getComments()
    {

        Long startTime=Utils.getUnixTimeStamp();
        txtStartTimeApiComment.setText("Start : "+startTime);


        RetrofitClient.getInstance().getComments()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Comment>>() {
                    @Override
                    public void onNext(List<Comment> response) {
                        //Handle logic
//                        timeStamp.setEndTime(Utils.getUnixTimeStamp());

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle error
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                        txtStartTimeApiComment.setText("Start : "+startTime+"\n"+"End : "+Utils.getUnixTimeStamp());


                        saveTask(COMMENTS);

                    }
                });
    }

    private void getPhotos()
    {

        Long startTime=Utils.getUnixTimeStamp();
        txtStartTimeApiPhoto.setText("Start : "+startTime);


        RetrofitClient.getInstance().getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Photo>>() {
                    @Override
                    public void onNext(List<Photo> response) {
                        //Handle logic

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle error
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        txtStartTimeApiPhoto.setText("Start : "+startTime+"\n"+"End : "+Utils.getUnixTimeStamp());

                        saveTask( PHOTOS);

                    }
                });
    }

    private void getTodos()
    {



        Long startTime=Utils.getUnixTimeStamp();
        txtStartTimeApiTodo.setText("Start : "+startTime);
        RetrofitClient.getInstance().getTodos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Todo>>() {
                    @Override
                    public void onNext(List<Todo> response) {
                        //Handle logic

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle error
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        txtStartTimeApiTodo.setText("Start : "+startTime+"\n"+"End : "+Utils.getUnixTimeStamp());

                        saveTask(TODOS);
                    }
                });
    }

    private void getPosts()
    {


        Long startTime=Utils.getUnixTimeStamp();
        txtStartTimeApiPost.setText("Start : "+startTime);

        RetrofitClient.getInstance().getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Post>>() {
                    @Override
                    public void onNext(List<Post> response) {
                        //Handle logic

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle error
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        txtStartTimeApiPost.setText("Start : "+startTime+"\n"+"End : "+Utils.getUnixTimeStamp());
                        saveTask(POSTS);
                    }
                });
    }


    private void getTasks(Long start,Long end,Long startTime,String api) {
        class GetTasks extends AsyncTask<Void, Void, TimeStamp> {

            @Override
            protected TimeStamp doInBackground(Void... voids) {
                TimeStamp taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .timeStampDao()
                        .getTimeStamp(startTime,api);
                return taskList;
            }

            @Override
            protected void onPostExecute(TimeStamp tasks) {
                super.onPostExecute(tasks);


                if (api.equalsIgnoreCase(COMMENTS))
                {
                    txtStartTimeComment.setText(
                            " Start Save : "+tasks.getStartTime()+" \n"+"End Save : "+tasks.getEndTime());
                }else if (api.equalsIgnoreCase(POSTS))
                {
                    txtStartTimePost.setText(
                            " Start Save : "+tasks.getStartTime()+" \n"+"End Save : "+tasks.getEndTime());
                }else if (api.equalsIgnoreCase(PHOTOS))
                {
                    txtStartTimePhoto.setText(
                            " Start Save : "+tasks.getStartTime()+" \n"+"End Save : "+tasks.getEndTime());
                }else if (api.equalsIgnoreCase(TODOS))
                {
                    txtStartTimeTodo.setText(
                            " Start Save : "+tasks.getStartTime()+" \n"+"End Save : "+tasks.getEndTime());
                }


            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
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
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
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

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnComments:
                getComments();
                break;
            case R.id.btnPhotos:
                getPhotos();
                break;
            case R.id.btnPost:
                getPosts();
                break;
            case R.id.btnTodos:
                getTodos();
                break;

        }
    }
}