package com.samagraassignment.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


import android.os.Bundle;
import android.widget.TextView;

import com.samagraassignment.R;
import com.samagraassignment.viewmodal.MainActivityViewModal;

public class AppMainActivity extends AppCompatActivity {


    private MainActivityViewModal mainActivityViewModal;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        userActivityBinding = DataBindingUtil.setContentView(this,        R.layout.activity_app_main);
        setContentView(R.layout.activity_app_main);
        txt=findViewById(R.id.txt);
        mainActivityViewModal = new ViewModelProvider(this).get(MainActivityViewModal.class);//        ViewDataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_app_main);

        mainActivityViewModal.getUser().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                txt.setText(""+s);
            }
        });
    }
}