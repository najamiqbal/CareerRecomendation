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

public class ResetPassFragment extends Fragment {
    View view;
    EditText NewPass,ConfirmPass;
    Button update_btn;
    private ProgressDialog pDialog;
    String t_email="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.resetpassword_fragment,container,false);
        initialization();
        return view;
    }
    private void initialization() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        if (getArguments()!=null){
            t_email=getArguments().getString("email");
        }
        NewPass=view.findViewById(R.id.NewPass);
        ConfirmPass=view.findViewById(R.id.ConfirmPass);
        update_btn=view.findViewById(R.id.btn_Update_password);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NewPass.getText().toString().isEmpty() && !ConfirmPass.getText().toString().isEmpty() && !t_email.isEmpty()){
                    if (NewPass.getText().toString().equals(ConfirmPass.getText().toString())){

                        Toast.makeText(getContext(), "Password Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }


}
