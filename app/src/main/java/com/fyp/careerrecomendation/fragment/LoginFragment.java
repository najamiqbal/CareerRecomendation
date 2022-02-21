package com.fyp.careerrecomendation.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.activities.MainActivity;

public class LoginFragment extends Fragment implements View.OnClickListener{
View view;
    TextView forgetpass, registration;
    EditText et_email, et_pass;
    Button btn_login;
    private ProgressDialog pDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.login_fragment,container,false);
        initializeVariable();
        return view;
    }
    private void initializeVariable() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        registration = view.findViewById(R.id.newuser);
        forgetpass = view.findViewById(R.id.forgetpass);
        et_email=view.findViewById(R.id.et_email);
        et_pass=view.findViewById(R.id.password);
        btn_login=view.findViewById(R.id.loginbtn);



        // click
        registration.setOnClickListener(this);
        forgetpass.setOnClickListener(this);
        btn_login.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginbtn:


                   Intent intent = new Intent(getContext(), MainActivity.class);
                   startActivity(intent);
                   getActivity().finish();


                break;
            case R.id.newuser:

                RegistrationFragment signUpFragmentUser = new RegistrationFragment();
                FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.fragment_container, signUpFragmentUser);
                fragmentTransaction1.addToBackStack("forgetpass_fragment");
                fragmentTransaction1.commit();

                break;
            case R.id.forgetpass:

                ForgetPassFragment forgetPassFragment = new ForgetPassFragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, forgetPassFragment);
                fragmentTransaction.addToBackStack("forgetpass_fragment");
                fragmentTransaction.commit();
                break;
        }
    }





}
