package com.fyp.careerrecomendation.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.fragment.DetailViewFragment;
import com.fyp.careerrecomendation.models.DepartmentsModel;

import java.util.List;

public class DepartmentsListAdapter extends RecyclerView.Adapter<DepartmentsListAdapter.ViewHolder> {
    Context context;
    List<DepartmentsModel> modelList;
    public DepartmentsListAdapter(Context context, List<DepartmentsModel> itemList) {
        this.context = context;
        this.modelList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.departments_list_viewholder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DepartmentsModel model=modelList.get(position);
        holder.tv_department.setText(model.getPoint_name());
        holder.tv_department.setOnClickListener(view -> {

            FragmentManager manager = ((AppCompatActivity)
                    context).getSupportFragmentManager();
            DetailViewFragment DetailFragment=new DetailViewFragment();
            Bundle args = new Bundle();
            args.putString("point", model.getDes());
            DetailFragment.setArguments(args);
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R .id.user_main_frame,DetailFragment );
            fragmentTransaction.addToBackStack("added");
            fragmentTransaction.commit();
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_department;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_department=itemView.findViewById(R.id.txt_department_name);
        }
    }
}
