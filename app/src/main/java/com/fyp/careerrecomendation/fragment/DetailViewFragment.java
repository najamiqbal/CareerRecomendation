package com.fyp.careerrecomendation.fragment;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.utils.AppConstants;

public class DetailViewFragment extends Fragment {
    View view;
    String point="";
   // TextView tv_info;
    WebView tv_info;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.info_detail_fragment,container,false);
        if (getArguments()!=null){
            point=getArguments().getString("point");
        }
        initialization();
        return view;
    }

    private void initialization() {
        tv_info=view.findViewById(R.id.webview);
        if (point.equals("Academics")){
            //tv_info.setText(Html.fromHtml(AppConstants.exam_policy));
            tv_info.getSettings().setJavaScriptEnabled(true);
            tv_info.loadDataWithBaseURL(null, AppConstants.exam_policy, "text/html", "utf-8", null);
        }else if (point.equals("library")){
           // tv_info.setText(Html.fromHtml(AppConstants.library_policy));
            tv_info.getSettings().setJavaScriptEnabled(true);
            tv_info.loadDataWithBaseURL(null, AppConstants.library_policy, "text/html", "utf-8", null);
        }
        else if (point.equals("internship")){
            //tv_info.setText(Html.fromHtml(AppConstants.internship));
            tv_info.getSettings().setJavaScriptEnabled(true);
            tv_info.loadDataWithBaseURL(null, AppConstants.internship, "text/html", "utf-8", null);
        }else if (point.equals("short_course")){
          //  tv_info.setText(Html.fromHtml(AppConstants.fee_policy));
            tv_info.getSettings().setJavaScriptEnabled(true);
            tv_info.loadDataWithBaseURL(null, AppConstants.short_course, "text/html", "utf-8", null);
        }else if (point.equals("scholarship")){
          //  tv_info.setText(Html.fromHtml(AppConstants.rules));
            tv_info.getSettings().setJavaScriptEnabled(true);
            tv_info.loadDataWithBaseURL(null, AppConstants.scholarship, "text/html", "utf-8", null);
        }else {
           // tv_info.setText(Html.fromHtml(point));
            tv_info.getSettings().setJavaScriptEnabled(true);
            tv_info.loadDataWithBaseURL(null, point, "text/html", "utf-8", null);
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Details");
        super.onViewCreated(view, savedInstanceState);
    }
}
