package com.example.mymvvm.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mymvvm.ClickHandleInterface;
import com.example.mymvvm.R;
import com.example.mymvvm.databinding.ItemUserBinding;
import com.example.mymvvm.viewmodel.UserViewModel;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomHolder> {

    private ArrayList<UserViewModel> arrayList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ClickHandleInterface clickHandleInterface;
    public UserAdapter(ArrayList<UserViewModel> arrayList, ClickHandleInterface clickHandleInterface) {
        this.clickHandleInterface = clickHandleInterface;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater==null)
        layoutInflater = LayoutInflater.from(parent.getContext());
        ItemUserBinding itemUserBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_user,parent,false);
        return new CustomHolder(itemUserBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomHolder holder, int position) {
        UserViewModel userViewModel = arrayList.get(position);
        holder.bind(userViewModel);
        holder.itemView.setOnClickListener(v -> clickHandleInterface.setPosition(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder{
        private ItemUserBinding itemUserBinding;
        private CustomHolder(ItemUserBinding itemUserBinding) {
            super(itemUserBinding.getRoot());
            this.itemUserBinding = itemUserBinding;
        }
        private void bind(UserViewModel userViewModel)
        {
            this.itemUserBinding.setItem(userViewModel);
            this.itemUserBinding.executePendingBindings();
        }

        public ItemUserBinding getItemUserBinding() {
            return itemUserBinding;
        }
    }

}
