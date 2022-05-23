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
import com.fyp.careerrecomendation.adapters.QueryListAdapter;
import com.fyp.careerrecomendation.models.QueryModel;
import com.fyp.careerrecomendation.models.UserModelClass;
import com.fyp.careerrecomendation.utils.AppConstants;
import com.fyp.careerrecomendation.utils.SharedPrefManager;
import com.fyp.careerrecomendation.utils.VolleyRequestsent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyQueriesFragment extends Fragment {

    View view;
    RecyclerView dr_recyclerView;
    List<QueryModel> ItemList;
    private ProgressDialog pDialog;
    QueryListAdapter mAdapter;
    String getQueriesUrl = "getFeedbacks",user_id="";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.my_queries_fragment,container,false);
        initilization();
        return view;
    }

    private void initilization() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        UserModelClass userModelClass= SharedPrefManager.getInstance(getContext()).getUser();
        user_id=userModelClass.getUser_id();
        dr_recyclerView = view.findViewById(R.id.recycler_view_queries);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,2);
        ItemList = new ArrayList<>();
        dr_recyclerView.setLayoutManager(linearLayoutManager);
        GetMyQueries(user_id);

    }

    private void GetMyQueries(String user_id) {
        pDialog.setMessage("please Wait....");
        pDialog.show();
        Log.d("status", "CHECK=====>id" + user_id);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConstants.mainurl + getQueriesUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Response is", response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        //Log.d("status", "CHECK" + jsonObject.getString("mobile"));

                        QueryModel model = new QueryModel();

                        model.setQuery(jsonObject.getString("feedback"));
                        model.setQuery_ans(jsonObject.getString("reply"));
                        ItemList.add(model);

                    }
                    pDialog.dismiss();
                    if (ItemList != null) {
                        mAdapter = new QueryListAdapter(getContext(), ItemList);
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
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", user_id);
                return params;
            }
        };
        VolleyRequestsent.getInstance().addRequestQueue(stringRequest);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("My Queries");
        super.onViewCreated(view, savedInstanceState);
    }
}
