package com.fyp.careerrecomendation.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctors_list_viewholder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.dr_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "123456"));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "ColorBlindness issue");
                context.startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
            }
        });

        holder.dr_mobile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "123456"));
                context.startActivity(intentDial);
            }
        });
        holder.dr_whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWhatsApp("123456");
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dr_name,dr_spec,dr_address,dr_email,dr_mobile,dr_whatsapp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dr_name=itemView.findViewById(R.id.txt_report_id);
            dr_address=itemView.findViewById(R.id.txt_draddress);
            dr_spec=itemView.findViewById(R.id.txt_dr_specialization);
            dr_email=itemView.findViewById(R.id.btn_dr_email);
            dr_mobile=itemView.findViewById(R.id.btn_dr_call);
            dr_whatsapp=itemView.findViewById(R.id.btn_dr_whatsapp);
        }
    }

    private void openWhatsApp(String number) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+"+92"+number + "&text="+"hello"));
        context.startActivity(intent);
    }
}
