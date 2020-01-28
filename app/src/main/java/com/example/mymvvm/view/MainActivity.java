package com.example.mymvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import android.os.Bundle;

import com.example.mymvvm.R;
import com.example.mymvvm.databinding.ActivityMainBinding;
import com.example.mymvvm.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        UserViewModel userViewModel = new UserViewModel(this);
        activityMainBinding.setUser(userViewModel);

    }
}
