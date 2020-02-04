package com.example.mymvvm.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymvvm.model.User;
import com.example.mymvvm.remote.user.UsersRepository;
import com.example.mymvvm.view.adapter.UserAdapter;

import java.util.ArrayList;

public class UserViewModel extends BaseObservable {
    private ArrayList<UserViewModel> arrayListHolder = new ArrayList<>();
    private MutableLiveData<ArrayList<UserViewModel>> arrayListMutableLiveData = new MutableLiveData<>();
    private String name;
    private String phone;
    private Context context;

    private UserViewModel(User user) {
        this.name = user.getName();
        this.phone = user.getPhone();
    }

    public UserViewModel(Context context) {
        this.context = context;
        //Connect to server API
        UsersRepository usersRepository = new UsersRepository();
        usersRepository.getUsers();
        usersRepository.getMutableLiveData().observe((LifecycleOwner) context, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                for (int i=0;i<users.size();i++)
                {
                    UserViewModel userViewModel = new UserViewModel(users.get(i));
                    arrayListHolder.add(userViewModel);
                }
                arrayListMutableLiveData.setValue(arrayListHolder);
            }
        });


//        for (int i=0;i<5;i++)
//        {
//            User user = new User("saeedkazemi : " + i,"09380680646");
//            UserViewModel userViewModel = new UserViewModel(user);
//            arrayListHolder.add(userViewModel);
//        }
//        arrayListMutableLiveData.setValue(arrayListHolder);
    }

    @BindingAdapter("bind:recyclerUser")
    public static void recyclerViewBinder(final RecyclerView recyclerView, MutableLiveData<ArrayList<UserViewModel>> arrayListMutableLiveData)
    {
        arrayListMutableLiveData.observe((LifecycleOwner) recyclerView.getContext(), new Observer<ArrayList<UserViewModel>>() {
            @Override
            public void onChanged(ArrayList<UserViewModel> userViewModels) {
                UserAdapter userAdapter = new UserAdapter(userViewModels, position -> Log.e("eee",String.valueOf(position)));
                recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),RecyclerView.VERTICAL,false));
                recyclerView.setAdapter(userAdapter);
            }
        });
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.example.mymvvm.BR.name);
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        notifyPropertyChanged(com.example.mymvvm.BR.phone);
    }


    public MutableLiveData<ArrayList<UserViewModel>> getArrayListMutableLiveData() {
        return arrayListMutableLiveData;
    }

    public void onClick(UserViewModel item)
    {
        Log.e("eee",item.name);
        Log.e("eee",item.phone);
    }
}
