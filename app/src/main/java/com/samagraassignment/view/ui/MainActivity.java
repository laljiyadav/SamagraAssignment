package com.samagraassignment.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.samagraassignment.R;
import com.samagraassignment.repository.TimeStamp;
import com.samagraassignment.utils.Constant;
import com.samagraassignment.viewmodal.MainActivityViewModal;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private MainActivityViewModal mainActivityViewModal;
    private TextView txtStartTimeComment,txtStartTimeApiComment,txtStartTimePost,txtStartTimeApiPost,txtStartTimeTodo,txtStartTimeApiTodo,txtStartTimePhoto,txtStartTimeApiPhoto;
    private Button btnComments,btnPost,btnUnixTimestamp,btnPhotos,btnTodos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityViewModal = new ViewModelProvider(this).get(MainActivityViewModal.class);
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

        btnComments.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        btnPhotos.setOnClickListener(this);
        btnTodos.setOnClickListener(this);

        mainActivityViewModal.getCommentsApiMutable().observe(this, new Observer<TimeStamp>() {
            @Override
            public void onChanged(TimeStamp timeStamp) {
                Log.i(TAG, "onChanged: "+timeStamp.getApiName());
                if (timeStamp.getApiName().equalsIgnoreCase("CLS"))
                {
                    txtStartTimeApiComment.setText("Start : "+timeStamp.getStartTime()+"\n"+"End : "+timeStamp.getEndTime());

                }else if (timeStamp.getApiName().equalsIgnoreCase(Constant.COMMENTS)){
                    txtStartTimeComment.setText("Start Save : "+timeStamp.getStartTime()+" \n"+"End Save : "+timeStamp.getEndTime());
                }else if (timeStamp.getApiName().equalsIgnoreCase("CLS_POST")){
                    txtStartTimePost.setText("Start Save : "+timeStamp.getStartTime()+" \n"+"End Save : "+timeStamp.getEndTime());
                }else if (timeStamp.getApiName().equalsIgnoreCase(Constant.POSTS)){
                    txtStartTimeApiPost.setText("Start Save : "+timeStamp.getStartTime()+" \n"+"End Save : "+timeStamp.getEndTime());
                }else if (timeStamp.getApiName().equalsIgnoreCase("CLS_TODOS")){
                    txtStartTimeTodo.setText("Start Save : "+timeStamp.getStartTime()+" \n"+"End Save : "+timeStamp.getEndTime());
                }else if (timeStamp.getApiName().equalsIgnoreCase(Constant.TODOS)){
                    txtStartTimeApiTodo.setText("Start Save : "+timeStamp.getStartTime()+" \n"+"End Save : "+timeStamp.getEndTime());
                }else if (timeStamp.getApiName().equalsIgnoreCase("CLS_PHOTOS")){
                    txtStartTimePhoto.setText("Start Save : "+timeStamp.getStartTime()+" \n"+"End Save : "+timeStamp.getEndTime());
                }else if (timeStamp.getApiName().equalsIgnoreCase(Constant.PHOTOS)){
                    txtStartTimeApiPhoto.setText("Start Save : "+timeStamp.getStartTime()+" \n"+"End Save : "+timeStamp.getEndTime());
                }


            }
        });


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnComments:
                mainActivityViewModal.getComments();
                break;
            case R.id.btnPost:
                mainActivityViewModal.getPosts();
                break;
            case R.id.btnPhotos:
                mainActivityViewModal.getPHOTOS();
                break;
            case R.id.btnTodos:
                mainActivityViewModal.getTODOS();
                break;
        }
    }
}