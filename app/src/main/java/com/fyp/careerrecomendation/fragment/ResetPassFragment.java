package com.fyp.careerrecomendation.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.utils.AppConstants;
import com.fyp.careerrecomendation.utils.VolleyRequestsent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ResetPassFragment extends Fragment {
    View view;
    EditText NewPass,ConfirmPass;
    Button update_btn;
    private ProgressDialog pDialog;
    String t_email="";
    String resetPass_url = "forgetPassword";
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

                        updatePass(NewPass.getText().toString().trim(),t_email);
                    }
                }
            }
        });


    }
    private void updatePass(final String password, final String t_email) {
        Log.d("","MOBILE NUMBER"+ this.t_email +"\n"+password);
        pDialog.setMessage("Login please Wait....");
        pDialog.show();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, AppConstants.mainurl+resetPass_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        if (jsonObject.getString("status").equals("true")) {
                            pDialog.dismiss();
                            Toast.makeText(getContext(), "Password Updated", Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            LoginFragment fragment=new LoginFragment();
                            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, fragment);
                            fragmentTransaction.commit();

                        } else {
                            pDialog.dismiss();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    pDialog.dismiss();
                    Toast.makeText(getContext(), "erro catch "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Some Error", Toast.LENGTH_SHORT).show();
                pDialog.dismiss();

            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> param=new HashMap<String,String>();
                param.put("password",password);
                param.put("email", t_email);
                return param;
            }
        };
        VolleyRequestsent.getInstance().addRequestQueue(stringRequest);
    }

}
