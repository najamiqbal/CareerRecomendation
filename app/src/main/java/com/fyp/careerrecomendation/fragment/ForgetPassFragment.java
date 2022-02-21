package com.fyp.careerrecomendation.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fyp.careerrecomendation.R;

public class ForgetPassFragment extends Fragment
{
    View view;
    Button btn_submit;
    EditText et_email;
    TextView tv_gologin;
    String t_email="najamiqbal829@gmail.com";
    private ProgressDialog pDialog;
    String Isexist_url = "https://houseofsoftwares.com/color-blindness/Api.php?action=resetPassword";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_forgetpassword,container,false);
        initialiazation();
        return view;
    }

    private void initialiazation() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        btn_submit=view.findViewById(R.id.submit_btn);
        et_email=view.findViewById(R.id.et_phone_no);
        tv_gologin=view.findViewById(R.id.backtologin);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!t_email.isEmpty()){


                    ForgetPassVerifyCode fragment=new ForgetPassVerifyCode();
                    Bundle args = new Bundle();
                    args.putString("email", t_email);
                    args.putString("code", "12345");
                    fragment.setArguments(args);
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.commit();
                }else {
                    Toast.makeText(getContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv_gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment homeFragment = new LoginFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
            }
        });
    }



}
