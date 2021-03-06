package com.fyp.careerrecomendation.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.fyp.careerrecomendation.utils.AppConstants;
import com.fyp.careerrecomendation.utils.VolleyRequestsent;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class RegistrationFragment extends Fragment {
View view;


    String IsUserExist = "isUserExist";
    private ProgressDialog pDialog;
    EditText et_name_user, et_mobile_user, et_address_user, et_email_user;
    Button registration_btn_user;
    TextInputLayout et_password_user, et_confirm_password_user;
    String user_type = "2", user_name = "",user_phone="", user_email = "", user_address = "", user_password = "", user_confirm_password = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.singup_fragment_user, container, false);
        initialization();
        return view;
    }

    private void initialization() {
        et_name_user = view.findViewById(R.id.user_name);
        et_email_user = view.findViewById(R.id.user_email);
        et_mobile_user = view.findViewById(R.id.user_mobile_number);
        et_address_user = view.findViewById(R.id.user_address);
        et_password_user = view.findViewById(R.id.user_password);
        et_confirm_password_user = view.findViewById(R.id.user_confirm_password);
        registration_btn_user = view.findViewById(R.id.register_btn_buyer);
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);

        registration_btn_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    IsUserExist(user_email);
                }else {
                    Toast.makeText(getContext(), "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //Validating data
    private boolean validate() {
        boolean valid = true;
        user_name = et_name_user.getText().toString();
        user_email = et_email_user.getText().toString();
        user_phone = et_mobile_user.getText().toString();
        user_address = et_address_user.getText().toString();
        user_password = et_password_user.getEditText().getText().toString();
        user_confirm_password = et_confirm_password_user.getEditText().getText().toString();



        if (user_name.isEmpty()) {
            et_name_user.setError("Pleaase enter name");
            valid = false;
        } else {
            et_name_user.setError(null);
        }
        if (user_email.isEmpty()) {
            et_email_user.setError("Please enter email");
            valid = false;
        } else {
            et_email_user.setError(null);
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(user_email).matches()) {
            et_email_user.setError("Email formate is wrong");
            valid = false;
        } else {
            et_email_user.setError(null);
        }
        if (user_phone.isEmpty()) {
            et_mobile_user.setError("Please enter mobile");
            valid = false;
        } else {
            et_mobile_user.setError(null);
        }
        if (user_address.isEmpty()) {
            et_address_user.setError("Please enter address");
            valid = false;
        } else {
            et_address_user.setError(null);
        }
        if (user_password.isEmpty() || user_confirm_password.isEmpty() || !user_confirm_password.equals(user_password)) {
            et_password_user.setError("Password don't Match");
            et_confirm_password_user.setError("Password don't Match");
            valid = false;
        } else {
            et_password_user.setError(null);
            et_confirm_password_user.setError(null);
        }

        if (user_type.isEmpty()) {
            valid = false;
        }

        return valid;
    }


    private void IsUserExist(final String user_email) {
        Log.e("check1122", "email" + user_email);
        pDialog.setMessage("Loading ...");
        pDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, AppConstants.mainurl+IsUserExist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        if (jsonObject.getString("status").equals("false")) {
                            pDialog.dismiss();
                            Toast.makeText(getContext(), "Email Already Registered", Toast.LENGTH_SHORT).show();

                        } else {
                            pDialog.dismiss();
                            //Toast.makeText(getContext(), "HELLO", Toast.LENGTH_SHORT).show();
                            VerifyCodeFragment fragment = new VerifyCodeFragment();
                            Bundle args = new Bundle();
                            args.putString("Mobile", user_phone);
                            args.putString("Name", user_name);
                            args.putString("Address", user_address);
                            args.putString("Email", user_email);
                            args.putString("user_type", user_type);
                            args.putString("Password", user_password);
                            args.putString("code", jsonObject.getString("code"));
                            fragment.setArguments(args);
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, fragment);
                            fragmentTransaction.commit();


                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pDialog.dismiss();
                    Toast.makeText(getContext(), ""+e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getContext(), "Error Please tyr again"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", user_email);
                return params;

            }

        };
        checkAndHandleSSLHandshake(getActivity());
        VolleyRequestsent.getInstance().addRequestQueue(stringRequest);
    }


    /**
     * By passing SSL
     */
    @SuppressLint("TrulyRandom")
    private void bypassSSLValidation() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (NoSuchAlgorithmException | KeyManagementException ex ) {
            ex.printStackTrace();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void checkAndHandleSSLHandshake(Activity activity){

        bypassSSLValidation();

    }

}
