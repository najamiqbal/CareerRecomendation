package com.fyp.careerrecomendation.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.adapters.ConselorsListAdapter;
import com.fyp.careerrecomendation.models.UserModelClass;

import java.util.ArrayList;
import java.util.List;

public class ConselorsListFragment extends Fragment {
    View view;
    RecyclerView dr_recyclerView;
    List<UserModelClass> ItemList;
    private ProgressDialog pDialog;
    ConselorsListAdapter mAdapter;
    String getDoctorsUrl = "https://houseofsoftwares.com/color-blindness/Api.php?action=getDoctors";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.doctors_list_fragment,container,false);
        initilization();
        return  view;
    }


    private void initilization() {
        pDialog = new ProgressDialog(getContext());
        pDialog.setCancelable(false);
        dr_recyclerView = view.findViewById(R.id.recycler_view_doctorsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        //GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,2);
        ItemList = new ArrayList<>();
        dr_recyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new ConselorsListAdapter(getContext(), ItemList);
         dr_recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Conselor's");
        super.onViewCreated(view, savedInstanceState);
    }
}
