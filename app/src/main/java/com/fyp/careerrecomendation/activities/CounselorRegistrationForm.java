package com.fyp.careerrecomendation.activities;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.fragment.LoginFragment;
import com.fyp.careerrecomendation.utils.AppConstants;
import com.fyp.careerrecomendation.utils.VolleyRequestsent;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CounselorRegistrationForm extends AppCompatActivity {
    String RegisterUrl = "isUserExist";
    private ProgressDialog pDialog;
    EditText et_name,et_intro_des,et_office_address,et_qualification ,et_mobile, et_city, et_email;
    Button registration_btn;
    TextInputLayout et_password, et_confirm_password;
    String user_type = "1", con_name = "",con_qualification="",con_office_address="",
            con_intro="", con_email = "", con_mobile = "", con_city = "", con_password = "", con_confirm_password = "";
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.counselor_registration_form);
        initialization();
    }

    private void initialization() {
        pDialog = new ProgressDialog(CounselorRegistrationForm.this);
        pDialog.setCancelable(false);
        et_name=findViewById(R.id.user_name);
        et_mobile=findViewById(R.id.user_mobile_number);
        et_email=findViewById(R.id.user_email);
        et_city=findViewById(R.id.user_address);
        et_qualification=findViewById(R.id.user_qualification);
        et_office_address=findViewById(R.id.user_office_address);
        et_intro_des=findViewById(R.id.user_bio);
        et_password=findViewById(R.id.user_password);
        et_confirm_password=findViewById(R.id.user_confirm_password);
        registration_btn=findViewById(R.id.register_btn);
        registration_btn.setOnClickListener(view -> {

            if (validate()){
                UserRegistration(user_type,con_name,con_mobile,con_email,con_city,con_qualification,con_intro,con_office_address,con_password);
            }

        });
    }

    private void UserRegistration(String user_type,String con_name, String con_mobile, String con_email, String con_city, String con_qualification, String con_intro, String con_office_address, String con_password) {
        pDialog.setMessage("Registring User....");
        pDialog.show();

        StringRequest stringRequest2 = new StringRequest(Request.Method.POST, AppConstants.mainurl+"register", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.hide();
                Log.d("VerifyActivity","user method call"+response.toString());
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        if (jsonObject.getString("status").equals("true")) {
                            pDialog.dismiss();
                            Toast.makeText(CounselorRegistrationForm.this, "Registered Successfully. Wait for Approval", Toast.LENGTH_SHORT).show();
                            // Goto Login Page
                            finish();

                        } else {

                            pDialog.dismiss();
                            Toast.makeText(CounselorRegistrationForm.this, " Sorry try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pDialog.dismiss();
                    Toast.makeText(CounselorRegistrationForm.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Log.d("Response error","Volley response errror is"+error.getMessage());
                Toast.makeText(CounselorRegistrationForm.this, "Please ty again", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", con_name);
                params.put("mobile", con_mobile);
                params.put("password", con_password);
                params.put("address", con_city);
                params.put("email", con_email);
                params.put("user_type", user_type);
                params.put("qualification", con_qualification);
                params.put("counselor_city", con_office_address);
                params.put("description", con_intro);
                return params;

            }
        };
        VolleyRequestsent.getInstance().addRequestQueue(stringRequest2);
    }

    //Validating data
    private boolean validate() {
        boolean valid = true;
        con_name = et_name.getText().toString();
        con_email = et_email.getText().toString();
        con_city = et_city.getText().toString();
        con_mobile = et_mobile.getText().toString();
        con_qualification = et_qualification.getText().toString();
        con_intro = et_intro_des.getText().toString();
        con_office_address=et_office_address.getText().toString();
        con_password=et_password.getEditText().getText().toString();
        con_confirm_password=et_confirm_password.getEditText().getText().toString();



        if (con_name.isEmpty()) {
            et_name.setError("Pleaase enter name");
            valid = false;
        } else {
            et_name.setError(null);
        }
        if (con_qualification.isEmpty()) {
             et_qualification.setError("Pleaase enter qualification");
            valid = false;
        }else {
            et_qualification.setError(null);
        }
        if (con_city.isEmpty()) {
            et_city.setError("Pleaase enter city");
            valid = false;
        } else {
            et_city.setError(null);
        }
        if (con_intro.isEmpty()) {
            et_intro_des.setError("Pleaase enter introduction");
            valid = false;
        } else {
            et_intro_des.setError(null);
        }
        if (con_email.isEmpty()) {
            et_email.setError("Please enter email");
            valid = false;
        } else {
            et_email.setError(null);
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(con_email).matches()) {
            et_email.setError("Email formate is wrong");
            valid = false;
        } else {
            et_email.setError(null);
        }
        if (con_mobile.isEmpty()) {
            et_mobile.setError("Please enter mobile");
            valid = false;
        } else {
            et_mobile.setError(null);
        }
        if (con_office_address.isEmpty()) {
            et_office_address.setError("Please enter address");
            valid = false;
        } else {
            et_office_address.setError(null);
        }
        if (con_password.isEmpty() || con_confirm_password.isEmpty() || !con_confirm_password.equals(con_password)) {
            et_password.setError("Password don't Match");
            et_confirm_password.setError("Password don't Match");
            valid = false;
        } else {
            et_password.setError(null);
            et_confirm_password.setError(null);
        }

        return valid;
    }





}
