package com.fyp.careerrecomendation.fragment;

import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fyp.careerrecomendation.R;

public class CareerRecomendationFragment extends Fragment {
    View view;
    RadioGroup radioGroupGender,rg_matric,rg_inter ,rg_eligibility;
    RadioButton rb_gender,rb_metric,rb_inter,rb_eligibility;
    Button next_btn,go_next_btn;
    EditText et_user_name,et_inter_marks,et_metric_marks;
    TextView tv_inter,tv_intermarks;
    String user_name="",s_metric="",metric_marks="",c_inter="",inter_marks="",s_gender="",eligibility="",total="";
    LinearLayout f1_layout;
    ScrollView f2_layout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.career_recomendation_fragment,container,false);
        initialization();
        return view;
    }

    private void initialization() {

        radioGroupGender=view.findViewById(R.id.radioGender);
        f1_layout=view.findViewById(R.id.f1_layout);
        f2_layout=view.findViewById(R.id.f2_layout);
        rg_eligibility=view.findViewById(R.id.rg_eligibility);
        rg_matric=view.findViewById(R.id.radioMatric);
        rg_inter=view.findViewById(R.id.radioInter);
        next_btn=view.findViewById(R.id.btn_next);
        go_next_btn=view.findViewById(R.id.btn_go_next);
        et_user_name=view.findViewById(R.id.user_name);
        et_metric_marks=view.findViewById(R.id.et_metric_marks);
        et_inter_marks=view.findViewById(R.id.et_inter_marks);
        tv_inter=view.findViewById(R.id.intermediate);
        tv_intermarks=view.findViewById(R.id.inter_marks);
        go_next_btn.setOnClickListener(view1 -> {
            if (rg_eligibility.getCheckedRadioButtonId() != -1) {
                int selectedId = rg_eligibility.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                rb_eligibility = view.findViewById(selectedId);
                eligibility = rb_eligibility.getText().toString();
                if (eligibility.equals("matric")){
                    f1_layout.setVisibility(View.GONE);
                    f2_layout.setVisibility(View.VISIBLE);
                    rg_inter.setVisibility(View.GONE);
                    et_inter_marks.setVisibility(View.GONE);
                    tv_intermarks.setVisibility(View.GONE);
                    tv_inter.setVisibility(View.GONE);
                }else {
                    f2_layout.setVisibility(View.VISIBLE);
                    f1_layout.setVisibility(View.GONE);
                }
            }

        });
        next_btn.setOnClickListener(view1 -> {
            if (validate()){
                 Toast.makeText(getContext(), ""+total, Toast.LENGTH_SHORT).show();

                Bundle args = new Bundle();
                if (eligibility.equals("matric")){
                    args.putString("eligibility", eligibility);
                    args.putString("total", total);
                    args.putString("metric", s_metric);
                    args.putString("inter", "-");
                }else {
                    args.putString("eligibility", eligibility);
                    args.putString("total", total);
                    args.putString("metric", s_metric);
                    args.putString("inter", c_inter);
                }
                InterestsTagsFragment fragment = new InterestsTagsFragment();
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.user_main_frame, fragment);
                fragmentTransaction1.addToBackStack("fragment");
                fragmentTransaction1.commit();

            }else {
                Toast.makeText(getContext(),"Null",Toast.LENGTH_SHORT).show();
            }

        });

    }

    //Validating data
    private boolean validate() {
        boolean valid = true;
        user_name = et_user_name.getText().toString();
        metric_marks = et_metric_marks.getText().toString();
        inter_marks = et_inter_marks.getText().toString();
        if (eligibility.equals("matric")){
            total = metric_marks;
        }else {
            total = String.valueOf((Integer.parseInt(inter_marks)+Integer.parseInt(metric_marks))/2);

            // get selected radio button from radioGroup
            if (rg_inter.getCheckedRadioButtonId() != -1) {
                int selectedId = rg_inter.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                rb_inter = view.findViewById(selectedId);
                if (rb_inter.getText().toString().equals("FSC Pre-Engineering")){
                    c_inter="engineering";
                }else if (rb_inter.getText().toString().equals("FSC Pre-medical"))
                {
                    c_inter="medical";
                }else if (rb_inter.getText().toString().equals("ICS")){
                    c_inter="ics";
                }else if (rb_inter.getText().toString().equals("I-COM")){
                    c_inter="icom";
                }else {
                    c_inter="fa";
                }
            }


            if (c_inter.isEmpty()) {
                Toast.makeText(getContext(), "Select Inter Qualification", Toast.LENGTH_SHORT).show();
                valid = false;
            }

            if (inter_marks.isEmpty()) {
                et_inter_marks.setError("Pleaase enter Marks");
                valid = false;
            } else {
                et_inter_marks.setError(null);
            }

        }

        // get selected radio button from radioGroup
        if (radioGroupGender.getCheckedRadioButtonId() != -1) {
            int selectedId = radioGroupGender.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            rb_gender = view.findViewById(selectedId);
            s_gender = rb_gender.getText().toString();
        }
        // get selected radio button from radioGroup
        if (rg_matric.getCheckedRadioButtonId() != -1) {
            int selectedId = rg_matric.getCheckedRadioButtonId();
            // find the radiobutton by returned id
            rb_metric = view.findViewById(selectedId);
            if (rb_metric.getText().toString().equals("Computer Science")){
                s_metric="cs";
            }else if (rb_metric.getText().toString().equals("Biology Science")){
                s_metric="bio";
            }else {
                s_metric="arts";
            }

        }

        if (s_gender.isEmpty()) {
            Toast.makeText(getContext(), "Please select gender", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        if (s_metric.isEmpty()) {
            Toast.makeText(getContext(), "Select Metric Qualification", Toast.LENGTH_SHORT).show();
            valid = false;
        }


        if (user_name.isEmpty()) {
            et_user_name.setError("Pleaase enter name");
            valid = false;
        } else {
            et_user_name.setError(null);
        }
        if (metric_marks.isEmpty()) {
            et_metric_marks.setError("Pleaase enter Marks");
            valid = false;
        } else {
            et_metric_marks.setError(null);
        }

        return valid;
    }
}
