package com.fyp.careerrecomendation.fragment;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.adapters.ConselorsListAdapter;
import com.fyp.careerrecomendation.models.UserModelClass;
import com.fyp.careerrecomendation.utils.AppConstants;
import com.fyp.careerrecomendation.utils.VolleyRequestsent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConselorsListFragment extends Fragment {
    View view;
    RecyclerView dr_recyclerView;
    List<UserModelClass> ItemList;
    private ProgressDialog pDialog;
    ConselorsListAdapter mAdapter;
    String getCounselorsUrl = "getCounselors";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.conselers_list_fragment, container, false);
        initilization();
        return view;
    }


    private void initilization() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        dr_recyclerView = view.findViewById(R.id.recycler_view_doctorsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,2);
        ItemList = new ArrayList<>();
        dr_recyclerView.setLayoutManager(linearLayoutManager);
        GetDoctors();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Conselor's");
        super.onViewCreated(view, savedInstanceState);
    }

    private void GetDoctors() {
        pDialog.setMessage("please Wait....");
        pDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConstants.mainurl + getCounselorsUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response is", response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        Log.d("status", "CHECK" + jsonObject.getString("mobile"));

                            UserModelClass model = new UserModelClass();
                            model.setUser_name(jsonObject.getString("username"));
                            model.setUser_email(jsonObject.getString("email"));
                            model.setUser_mobile(jsonObject.getString("mobile"));
                            model.setUser_address(jsonObject.getString("address"));
                            model.setUser_id(jsonObject.getString("counselor_id"));
                            model.setCounselor_bio(jsonObject.getString("description"));
                            model.setQualification(jsonObject.getString("qualification"));
                            model.setBusniss_address(jsonObject.getString("counselor_city"));
                            ItemList.add(model);

                    }
                    pDialog.dismiss();
                    if (ItemList != null) {
                        mAdapter = new ConselorsListAdapter(getContext(), ItemList);
                        dr_recyclerView.setAdapter(mAdapter);
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
        });
        VolleyRequestsent.getInstance().addRequestQueue(stringRequest);
    }
}
