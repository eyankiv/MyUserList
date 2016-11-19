package com.example.yevgeni.myuserlist;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity implements  EditOrAddDialogFragement.EditOrAddListener, UsersAdapter.UsersAdapterDelete{

    ListView listUsers;
    ArrayList <Users> usersArray = new ArrayList<Users>();
    int position;

    List<Users> users;
    UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usersArray.add(new Users("Mike","1234"));
        usersArray.add(new Users("Elad","1235ee"));
        usersArray.add(new Users("Inna","63Jfd"));
        usersArray.add(new Users("Omer","adam3"));
        usersArray.add(new Users("Jackson","5five"));
        usersArray.add(new Users("Jeff","Jsuper3"));
        usersArray.add(new Users("Lara","larson535"));


        listUsers = (ListView)findViewById(R.id.usersListView);
        usersAdapter = new UsersAdapter(this,usersArray);
        listUsers.setAdapter(usersAdapter);

        listUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemPosition, long id) {
                Toast.makeText(MainActivity.this, "User clicked: "+usersArray.get(position).getUserName()
                        , Toast.LENGTH_SHORT).show();
                FragmentManager editFragManager = getFragmentManager();
                EditOrAddDialogFragement editDialogFragmenet = new EditOrAddDialogFragement();
                editDialogFragmenet.setEdit(true);
                editDialogFragmenet.setDialogTitle("Edit Existing User");
                editDialogFragmenet.setUsrNameHint(usersArray.get(itemPosition).getUserName());
                editDialogFragmenet.setListener(MainActivity.this);
                editDialogFragmenet.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
                editDialogFragmenet.show(editFragManager,"");
                position = itemPosition;
            }
        });
    }


    public void btnAddUsr(View view) {
        FragmentManager fragementManager = getFragmentManager();
        EditOrAddDialogFragement editOrAddDialogFragement = new EditOrAddDialogFragement();
        editOrAddDialogFragement.setEdit(false);
        editOrAddDialogFragement.setDialogTitle("ADD USER");
        editOrAddDialogFragement.setListener(this);
        editOrAddDialogFragement.show(fragementManager,"");
        editOrAddDialogFragement.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog);
    }


    @Override
    public void edit( String usrName, String usrPassword) {
        usersArray.set(position,new Users(usrName,usrPassword));
    }

    @Override
    public void add(String usrName, String usrPassword) {
        usersArray.add(new Users(usrName,usrPassword));
    }


    @Override
    public void deleteUsr(int Position) {
        usersArray.remove(position);
        usersAdapter.notifyDataSetChanged();

    }

//    @Override
//    public void removeUser() {
//        usersArray.remove(usersArray.get(position));
//    }

}
