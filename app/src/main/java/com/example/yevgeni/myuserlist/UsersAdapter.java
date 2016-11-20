package com.example.yevgeni.myuserlist;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yevgeni on 16/11/2016.
 */

public class UsersAdapter extends ArrayAdapter<Users>{
    Activity activity;
    List<Users> usersList;


    public UsersAdapter(Activity activity, List<Users> usersList) {
        super(activity, R.layout.item_users,usersList);
        this.activity = activity;
        this.usersList = usersList;
    }

    static class UsersContainer{
        TextView userName;
        Button userDeleteBtn;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        UsersContainer usersContainer = new UsersContainer();
        if (rowView == null){
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_users,null);
            usersContainer.userName = (TextView)rowView.findViewById(R.id.usrName);
            usersContainer.userDeleteBtn = (Button) rowView.findViewById(R.id.delItemBtn);
            usersContainer.userDeleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   usersList.remove(position);
                    notifyDataSetChanged();
                }
            });
            rowView.setTag(usersContainer);


        }else {
            usersContainer = (UsersContainer)rowView.getTag();
        }
        usersContainer.userName.setText(usersList.get(position).getUserName());

        return rowView;
    }

}
