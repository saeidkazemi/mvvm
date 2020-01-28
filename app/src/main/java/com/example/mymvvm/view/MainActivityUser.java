package com.example.mymvvm.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.mymvvm.R;
import com.example.mymvvm.databinding.ActivityMainBinding;
import com.example.mymvvm.databinding.ActivityMainUserBinding;
import com.example.mymvvm.viewmodel.UserViewModel;

public class MainActivityUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainUserBinding activityMainUserBinding = DataBindingUtil.setContentView(this,R.layout.activity_main_user);
        UserViewModel userViewModel = new UserViewModel(this);
        activityMainUserBinding.setUser(userViewModel);

    }
}
