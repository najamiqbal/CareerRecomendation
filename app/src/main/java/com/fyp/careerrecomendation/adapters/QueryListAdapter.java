package com.fyp.careerrecomendation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.models.QueryModel;
import com.fyp.careerrecomendation.models.UserModelClass;

import java.util.List;

public class QueryListAdapter extends RecyclerView.Adapter<QueryListAdapter.ViewHolder> {
    Context context;
    List<QueryModel> modelList;
    public QueryListAdapter(Context context, List<QueryModel> itemList) {
        this.context = context;
        this.modelList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.query_list_viewholder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final QueryModel modelClass=modelList.get(position);
        holder.tv_query.setText(modelClass.getQuery());
        holder.tv_query_ans.setText(modelClass.getQuery_ans());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_query,tv_query_ans;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_query=itemView.findViewById(R.id.tv_query);
            tv_query_ans=itemView.findViewById(R.id.tv_query_ans);
        }
    }
}
