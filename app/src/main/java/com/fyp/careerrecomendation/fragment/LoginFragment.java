package com.fyp.careerrecomendation.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.activities.MainActivity;
import com.fyp.careerrecomendation.models.UserModelClass;
import com.fyp.careerrecomendation.utils.AppConstants;
import com.fyp.careerrecomendation.utils.SharedPrefManager;
import com.fyp.careerrecomendation.utils.VolleyRequestsent;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends Fragment implements View.OnClickListener{
View view;
    TextView forgetpass, registration;
    EditText et_email;
    TextInputLayout et_pass;
    Button btn_login;
    String t_email, t_password;
    String Login_url = "login";
    private ProgressDialog pDialog;
    UserModelClass userModelClass = new UserModelClass();
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

                if (validate()) {
                    UserLogin(t_email, t_password);
                }

                /*   Intent intent = new Intent(getContext(), MainActivity.class);
                   startActivity(intent);
                   getActivity().finish();*/


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

    //Validating data
    private boolean validate() {
        boolean valid = true;
        t_email = et_email.getText().toString().trim();
        t_password = et_pass.getEditText().getText().toString().trim();

        if (t_email.isEmpty()) {
            et_email.setError("Please Enter Phone");
            valid = false;
        } else {
            et_email.setError(null);
        }

        if (t_password.isEmpty()) {
            et_pass.setError("Please Enter Correct Password");
            valid = false;
        } else {
            et_pass.setError(null);
        }

        return valid;
    }

    private void UserLogin(final String t_email, final String t_pass) {
        pDialog.setMessage("Login please Wait....");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstants.mainurl+Login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response is", response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        //2 is use for user account
                        if (jsonObject.getString("status").equals("true")){

                            if (jsonObject.getString("user_type").equals("2")) {

                                userModelClass.setUser_name(jsonObject.getString("username"));
                                userModelClass.setUser_id(jsonObject.getString("id"));
                                userModelClass.setUser_mobile(jsonObject.getString("mobile"));
                                userModelClass.setUser_email(jsonObject.getString("email"));
                                userModelClass.setUser_address(jsonObject.getString("address"));
                                userModelClass.setUser_password(jsonObject.getString("password"));
                                userModelClass.setUser_type(jsonObject.getString("user_type"));


                                if (SharedPrefManager.getInstance(getContext()).addUserToPref(userModelClass)) {
                                    pDialog.dismiss();
                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                } else {
                                    pDialog.dismiss();
                                    Toast.makeText(getContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                                }

                            }

                        }else {
                            pDialog.dismiss();
                            Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (JSONException e) {
                    pDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(getContext(), "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getContext(), "Some Error Here", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", t_email);
                params.put("password", t_pass);
                return params;

            }
        };
        VolleyRequestsent.getInstance().addRequestQueue(stringRequest);
    }



}
