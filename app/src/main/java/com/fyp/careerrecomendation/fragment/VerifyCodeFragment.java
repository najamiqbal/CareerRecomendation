package com.fyp.careerrecomendation.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fyp.careerrecomendation.R;

public class VerifyCodeFragment extends Fragment {
    View view;
    private ProgressDialog pDialog;
    Button verify;
    TextView timer;
    EditText Code;
    Handler handler;
    int count = 120;
    String user_mobile="",user_Name="",user_Password="",user_Email="",user_Address="",verification_code="",user_type="",dr_bio="",clinic_address,dr_specialization="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.verify_code_fragment,container,false);
        initialization();
        return view;
    }

    private void initialization() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        verify = view.findViewById(R.id.btn_verify);
        timer = view.findViewById(R.id.resettimer);
        handler = new Handler(getActivity().getMainLooper());
        Code = view.findViewById(R.id.verificationcode);
        if (getArguments()!=null){
            user_type = getArguments().getString("user_type");
            if (user_type.equals("2")){
                user_mobile = getArguments().getString("Mobile");
                user_Name = getArguments().getString("Name");
                user_Email = getArguments().getString("Email");
                user_Address = getArguments().getString("Address");
                user_Password = getArguments().getString("Password");
                verification_code = getArguments().getString("code");
            }else {

                user_mobile = getArguments().getString("Mobile");
                user_Name = getArguments().getString("Name");
                user_Email = getArguments().getString("Email");
                user_Address = getArguments().getString("Address");
                user_Password = getArguments().getString("Password");
                verification_code = getArguments().getString("code");
                dr_specialization = getArguments().getString("drspe");
                dr_bio = getArguments().getString("CompDesc");
                clinic_address = getArguments().getString("ClinicAddress");

            }
             Log.d("VerifyCode","user Data  "+user_type+user_Name+user_mobile+user_Email+user_Address+user_Password);

        }

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Code.getText().toString().isEmpty()) {

                    if(Code.getText().toString().equals(verification_code)){



                       Toast.makeText(getContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();


                    }else {
                        Toast.makeText(getContext(), "Please enter Valid code", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getContext(), "Please Enter Verification Code", Toast.LENGTH_SHORT).show();
                }

            }
        });
        thread();

    }


    private void thread() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (count >= 0) {
                    try {
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        Log.i("TAG", e.getMessage());
                    }
                    Log.i("TAG", "Thread id in while loop: " + Thread.currentThread().getId() + ", Count : " + count);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            timer.setText("Seconds Left " + count);
                        }
                    });
                    if (count == 0) {
                        // getActivity().onBackPressed();
                        // save the changes
                    }
                    count--;
                }
            }
        }).start();
    }


}
