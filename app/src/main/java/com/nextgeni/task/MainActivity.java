package com.nextgeni.task;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nextgeni.task.fragment.CategoryFragment;


public class MainActivity extends AppCompatActivity {

    CategoryFragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       fragment =  new CategoryFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, fragment , fragment.getClass().getName())
                .commit();
    }
}
