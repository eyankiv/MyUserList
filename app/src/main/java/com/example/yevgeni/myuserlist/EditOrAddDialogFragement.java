package com.example.yevgeni.myuserlist;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by yevgeni on 18/11/2016.
 */

public class EditOrAddDialogFragement extends DialogFragment{

    String dialogTitle = "";
    String usrNameHint = "";

    public void setUsrNameHint(String usrNameHint) {
        this.usrNameHint = usrNameHint;
    }

    EditText inputUsr,inputPass;
    Boolean isEdit;
    Button btnSubmit;




    EditOrAddListener listener;


    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }


    public void setInputUsr(EditText inputUsr) {
        this.inputUsr = inputUsr;
    }


    public void setInputPass(EditText inputPass) {
        this.inputPass = inputPass;
    }


    public void setEdit(Boolean edit) {
        isEdit = edit;
    }


    public void setListener(EditOrAddListener listener) {
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragement_edit_add_dialog,container);

        getDialog().setTitle(dialogTitle);
        inputUsr = (EditText)view.findViewById(R.id.editUsrName);
        inputPass = (EditText)view.findViewById(R.id.editUsrPassword);
        btnSubmit = (Button)view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(btnSubmitListener);

            if(!isEdit){
                inputUsr.setHint("Enter User Name");
                inputPass.setHint("Enter Password");
            }else {
                inputUsr.setHint(usrNameHint);//needs to pull data from which user we adding
                inputPass.setHint("******");
            }


        return view;
    }
    
    View.OnClickListener btnSubmitListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String usrNameEntered = inputUsr.getText().toString();
            String usrPassEntered = inputPass.getText().toString();

            if (isEdit) {
                    if (listener != null) {
                        listener.edit(usrNameEntered, usrPassEntered);
                        dismiss();
                    }

                } else{
                    if(listener!=null)
                        listener.add(usrNameEntered,usrPassEntered);

                    if(!TextUtils.isEmpty(usrNameEntered)){
                        if (!TextUtils.isEmpty(usrPassEntered))
                            dismiss();
                        Toast.makeText(view.getContext(), "PLease Enter a Password", Toast.LENGTH_SHORT).show();

                    }else {
                        Toast.makeText(view.getContext(), "Please Enter a User Name", Toast.LENGTH_SHORT).show();
                    }
                }

            }
    };




    public static interface EditOrAddListener{
        void edit(String usrName, String usrPassword);
        void add(String usrName, String usrPassword);
//        void removeUser();
    }
}
