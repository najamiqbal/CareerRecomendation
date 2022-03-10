package com.fyp.careerrecomendation.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fyp.careerrecomendation.R;

public class UserProfile extends Fragment {
    View view;
    private ProgressDialog pDialog;
    EditText edit_name,edit_mobile,edit_addres,edit_email,edit_current_pass,new_pass;
    Button edit_info_btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.user_profile,container,false);
        initialization();
        return view;
    }
    private void initialization() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        edit_name=view.findViewById(R.id.user_name_edit);
        edit_mobile=view.findViewById(R.id.user_mobile_number_edit);
        edit_email=view.findViewById(R.id.user_email_edit);
        edit_current_pass=view.findViewById(R.id.user_password_edit);
        new_pass=view.findViewById(R.id.user_confirm_password_edit);
        edit_info_btn=view.findViewById(R.id.edit_btn_user);
        edit_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Info Updated Successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
