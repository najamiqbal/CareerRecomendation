package com.fyp.careerrecomendation.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.models.UserModelClass;

import java.util.ArrayList;
import java.util.List;

public class ConselorsListAdapter extends RecyclerView.Adapter<ConselorsListAdapter.ViewHolder> {
    Context context;
    List<UserModelClass> modelList;
    ArrayList<UserModelClass> arrayList;
    public ConselorsListAdapter(Context context, List<UserModelClass> itemList) {
        this.context = context;
        this.modelList = itemList;
        this.arrayList=new ArrayList<UserModelClass>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conselers_list_viewholder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final UserModelClass modelClass=modelList.get(position);
        holder.tv_name.setText(modelClass.getUser_name());
        holder.tv_qualification.setText(modelClass.getQualification());
        holder.tv_office_address.setText(modelClass.getBusniss_address());
        holder.tv_bio.setText(modelClass.getCounselor_bio());
        holder.btn_call.setOnClickListener(view -> {
            Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + modelClass.getUser_mobile()));
            context.startActivity(intentDial);
        });
        holder.btn_whatsapp.setOnClickListener(view -> {
            openWhatsApp(modelClass.getUser_mobile());
        });
        holder.btn_email.setOnClickListener(view -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + modelClass.getUser_email()));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ColorBlindness issue");
            context.startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_qualification,tv_office_address,tv_bio;
        Button btn_call,btn_whatsapp,btn_email;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.consultant_name);
            tv_bio=itemView.findViewById(R.id.txt_bio);
            tv_office_address=itemView.findViewById(R.id.txt_address);
            tv_qualification=itemView.findViewById(R.id.txt_study);
            btn_call=itemView.findViewById(R.id.btn_call);
            btn_email=itemView.findViewById(R.id.btn_email);
            btn_whatsapp=itemView.findViewById(R.id.btn_whatsapp);

        }
    }


    private void openWhatsApp(String number) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+92"+number + "&text="+"hello"));
        context.startActivity(intent);
    }

}
