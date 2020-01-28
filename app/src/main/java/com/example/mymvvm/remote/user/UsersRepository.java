package com.example.mymvvm.remote.user;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mymvvm.model.User;
import com.example.mymvvm.remote.APIService;
import com.example.mymvvm.remote.RetroClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
    private MutableLiveData<ArrayList<User>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<User> userArrayList = new ArrayList<>();
    public UsersRepository()
    {

    }

    public void getUsers()
    {
        // API Call
        APIService apiService = RetroClass.getApiService();
        Call<UsersList> usersListCall = apiService.getUsersList("saeed");
        usersListCall.enqueue(new Callback<UsersList>() {
            @Override
            public void onResponse(Call<UsersList> call, Response<UsersList> response) {
                if (response.isSuccessful() && response.body()!=null)
                {
                    userArrayList.addAll(response.body().getUsers());
                    mutableLiveData.setValue(userArrayList);
                }
            }

            @Override
            public void onFailure(Call<UsersList> call, Throwable t) {
                Log.e("Error",t.toString());
            }
        });
    }

    public MutableLiveData<ArrayList<User>> getMutableLiveData() {
        return mutableLiveData;
    }
}
