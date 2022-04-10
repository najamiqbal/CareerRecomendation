package com.fyp.careerrecomendation.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.adapters.ConselorsListAdapter;
import com.fyp.careerrecomendation.adapters.DepartmentsListAdapter;
import com.fyp.careerrecomendation.models.DepartmentsModel;
import com.fyp.careerrecomendation.models.UserModelClass;
import com.fyp.careerrecomendation.utils.AppConstants;
import com.fyp.careerrecomendation.utils.VolleyRequestsent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class DepartmentsListFragment extends Fragment {
    View view;
    RecyclerView info_recyclerView;
    List<DepartmentsModel> ItemList;
    private ProgressDialog pDialog;
    DepartmentsListAdapter mAdapter;
    String total,metric_with,inter_with,eligibility,interests,softskills;

    String getDepartmentsUrl = "getDepartments";
    String getRecomendationUrl = "getSuggestions";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.departments_list_fragment, container, false);
        initialization();
        return view;
    }

    private void initialization() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        info_recyclerView = view.findViewById(R.id.recycler_view_departments);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        ItemList = new ArrayList<>();
        info_recyclerView.setLayoutManager(linearLayoutManager);

        if (getArguments() != null) {

            eligibility = getArguments().getString("eligibility");
            total = getArguments().getString("total_marks");
            metric_with = getArguments().getString("metric_with");
            inter_with = getArguments().getString("inter_with");
            interests = getArguments().getString("interests");
            softskills = getArguments().getString("softskills");

            Log.d("VerifyData","user Data  "+eligibility+total+metric_with+inter_with+"      =====>"+interests);
            GetRecomendedDepartments(eligibility,metric_with,inter_with,interests,total,softskills);

        }else {
            GetDepartments();
        }
    }

    private void GetRecomendedDepartments(String eligibility, String metric_with, String inter_with, String interests, String total,String softskills) {
        pDialog.setMessage("Loading...");
        pDialog.show();
       // ItemList.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstants.mainurl + getRecomendationUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response is ==> ", response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                       // Log.d("status", "CHECK" + jsonObject.getString("name"));

                        if (jsonObject.getString("status").equals("false")) {
                            Toast.makeText(getContext(), ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            DepartmentsModel model = new DepartmentsModel();
                            model.setId(jsonObject.getString("department_id"));
                            model.setPoint_name(jsonObject.getString("name"));
                            model.setDes(jsonObject.getString("description"));
                            ItemList.add(model);
                        }
                    }
                    pDialog.dismiss();
                    if (ItemList != null) {
                        mAdapter = new DepartmentsListAdapter(getContext(), ItemList);
                        info_recyclerView.setAdapter(mAdapter);
                    } else {
                        Toast.makeText(getContext(), "NO DATA", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    pDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(getContext(), "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getContext(), "Some Error", Toast.LENGTH_SHORT).show();


            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("eligibility", eligibility);
                params.put("interests", interests);
                params.put("matricWith", metric_with);
                params.put("interWith", inter_with);
                params.put("minAggregate", total);
                params.put("softskills", softskills);
                return params;

            }
        };
        VolleyRequestsent.getInstance().addRequestQueue(stringRequest);
    }


    private void GetDepartments() {
        pDialog.setMessage("Loading...");
        pDialog.show();
        ItemList.clear();
        Log.d("Response is", "CHECK RESPONSE"+getDepartmentsUrl+" ");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConstants.mainurl +getDepartmentsUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response is", "CHECK RESPONSE"+response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                        DepartmentsModel model=new DepartmentsModel();
                        model.setId(jsonObject.getString("department_id"));
                        model.setPoint_name(jsonObject.getString("name"));
                        model.setDes(jsonObject.getString("description"));
                        ItemList.add(model);

                    }
                    pDialog.dismiss();
                    if (ItemList != null) {
                        mAdapter = new DepartmentsListAdapter(getContext(), ItemList);
                        info_recyclerView.setAdapter(mAdapter);
                    } else {
                        Toast.makeText(getContext(), "NO DATA", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    pDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(getContext(), "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getContext(), "Some Error"+error.toString(), Toast.LENGTH_SHORT).show();


            }
        });
        VolleyRequestsent.getInstance().addRequestQueue(stringRequest);
    }

}
