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
import com.fyp.careerrecomendation.adapters.DepartmentsListAdapter;
import com.fyp.careerrecomendation.models.DepartmentsModel;
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
    // define the default variables for proper certificate validation
    private static final SSLSocketFactory defaultSSLSocketFactory = HttpsURLConnection.getDefaultSSLSocketFactory();
    private static final HostnameVerifier defaultSSLHostnameVerifier = HttpsURLConnection.getDefaultHostnameVerifier();
    String getDepartmentsUrl = "https://devapis.tk/career-recommendation/Api.php?action=getDepartments";

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
        GetDepartments();
    }



    private void GetDepartments() {
        pDialog.setMessage("Loading...");
        pDialog.show();
        ItemList.clear();
        Log.d("Response is", "CHECK RESPONSE"+getDepartmentsUrl+" ");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getDepartmentsUrl, new Response.Listener<String>() {
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
        checkAndHandleSSLHandshake(getActivity());
        VolleyRequestsent.getInstance().addRequestQueue(stringRequest);
    }

    private void setDefaultSettingsForHttpsConnection(){
        HttpsURLConnection.setDefaultSSLSocketFactory(defaultSSLSocketFactory);
        HttpsURLConnection.setDefaultHostnameVerifier(defaultSSLHostnameVerifier);
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
