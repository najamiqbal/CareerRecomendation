package com.fyp.careerrecomendation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fyp.careerrecomendation.R;

public class UniInfoDeskFragment extends Fragment implements View.OnClickListener {
    View view;


    TextView tv_departments,tv_exam_policy,tv_library_policy,tv_intership,tv_fee_policy,tv_rules;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.uni_info_desk,container,false);
        initilization();
        return view;
    }
    private void initilization() {
        tv_exam_policy=view.findViewById(R.id.tv_exam_policy);
        tv_departments=view.findViewById(R.id.tv_departments);
        tv_library_policy=view.findViewById(R.id.tv_library_policy);
        tv_intership=view.findViewById(R.id.tv_internship_offered);
        tv_fee_policy=view.findViewById(R.id.tv_fee_policy);
        tv_rules=view.findViewById(R.id.tv_rules);

        tv_departments.setOnClickListener(this);
        tv_exam_policy.setOnClickListener(this);
        tv_library_policy.setOnClickListener(this);
        tv_intership.setOnClickListener(this);
        tv_fee_policy.setOnClickListener(this);
        tv_rules.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_departments:

                DepartmentsListFragment departmentsListFragment = new DepartmentsListFragment();
                FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.user_main_frame, departmentsListFragment);
                fragmentTransaction2.addToBackStack("fragment");
                fragmentTransaction2.commit();

                break;
            case R.id.tv_exam_policy:

                fragmentCall("Academics");

                break;
            case R.id.tv_library_policy:
                fragmentCall("library");
                break;
            case R.id.tv_internship_offered:

                fragmentCall("internship");
                break;
            case R.id.tv_fee_policy:
                fragmentCall("fee_policy");
                break;
            case R.id.tv_rules:
                fragmentCall("rules");
                break;

        }
    }

    private void fragmentCall(String s) {
        DetailViewFragment detailViewFragment = new DetailViewFragment();
        Bundle args4 = new Bundle();
        args4.putString("point", s);
        detailViewFragment.setArguments(args4);
        FragmentTransaction fragmentTransaction4 = getFragmentManager().beginTransaction();
        fragmentTransaction4.replace(R.id.user_main_frame, detailViewFragment);
        fragmentTransaction4.addToBackStack("fragment");
        fragmentTransaction4.commit();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Info Desk");
        super.onViewCreated(view, savedInstanceState);
    }
}
