package com.fyp.careerrecomendation.fragment;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.doodle.android.chips.ChipsView;
import com.doodle.android.chips.model.Contact;
import com.fyp.careerrecomendation.R;
import com.fyp.careerrecomendation.models.TagsModel;
import com.fyp.careerrecomendation.utils.VolleyRequestsent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SoftSkillsTagsfragment extends Fragment {
    View view;
    String total, metric_with, inter_with, interests, inter_marks, eligibility;
    Button submit_btn;
    private RecyclerView mTags;
    private SoftSkillsTagsfragment.TagsAdapter mTagsAdapter;
    private ChipsView mChipsView;
    List<String> temmArray = new ArrayList<>();
    String tagsUrl = "https://devapis.tk/career-recommendation/Api.php?action=getSoftskills";

    ArrayList<TagsModel> item1 = new ArrayList<>();
    int counter = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.soft_skills_screen, container, false);
        initialization();
        return view;
    }

    private void initialization() {
        if (getArguments() != null) {

            eligibility = getArguments().getString("eligibility");
            total = getArguments().getString("total_marks");
            metric_with = getArguments().getString("metric_with");
            inter_with = getArguments().getString("inter_with");
            interests = getArguments().getString("interests");

            Log.d("VerifyData","user Data  "+eligibility+total+metric_with+inter_with);

        }

        submit_btn=view.findViewById(R.id.search_btn);
        submit_btn.setOnClickListener(view1 -> {
            String ret = "";
            if (!(temmArray.size() <3)) {
                for (int i = 0; i < temmArray.size(); i++) {
                    ret += temmArray.get(i) + ",";
                }

                DepartmentsListFragment fragment = new DepartmentsListFragment();
                Bundle args = new Bundle();
                args.putString("total_marks", total);
                args.putString("eligibility", eligibility);
                args.putString("metric_with", metric_with);
                args.putString("inter_with", inter_with);
                args.putString("interests", interests);
                args.putString("softskills", ret);
                fragment.setArguments(args);
                FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                fragmentTransaction1.replace(R.id.user_main_frame, fragment);
                fragmentTransaction1.addToBackStack("fragment");
                fragmentTransaction1.commit();

            }else {
                Toast.makeText(getContext(), "Enter atleast 3 inputs", Toast.LENGTH_SHORT).show();
            }
            Log.d("MainActivity","araay String"+ret);
        });

        GetTagsData();


        //start from here

        mTags = (RecyclerView) view.findViewById(R.id.rv_tags);
        mTags.setLayoutManager(new LinearLayoutManager(getContext()));

        mChipsView = (ChipsView) view.findViewById(R.id.cv_tags);

        mChipsView.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/FiraSans-Medium.ttf"));
        // mChipsView.useInitials(14, Typeface.createFromAsset(this.getAssets(), "fonts/FiraSans-Medium.ttf"), Color.RED);

        // change EditText config
        mChipsView.getEditText().setCursorVisible(true);

        mChipsView.setChipsValidator(new ChipsView.ChipValidator() {
            @Override
            public boolean isValid(Contact contact) {
                return true;
            }
        });

        mChipsView.setChipsListener(new ChipsView.ChipsListener() {
            @Override
            public void onChipAdded(ChipsView.Chip chip) {
                for (ChipsView.Chip chipItem : mChipsView.getChips()) {
                    Log.d("ChipList", "chip: " + chipItem.toString());
                }
            }

            @Override
            public void onChipDeleted(ChipsView.Chip chip) {

            }

            @Override
            public void onTextChanged(CharSequence text) {

            }

            @Override
            public boolean onInputNotRecognized(String text) {


                return false;
            }
        });

    }
    private void GetTagsData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, tagsUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                item1.clear();
                Log.d("Response is", response.toString());
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        //Log.d("status", "CHECK" + jsonObject.getString("status"));

                        TagsModel model = new TagsModel();
                        model.setId(jsonObject.getString("id"));
                        model.setName(jsonObject.getString("name"));
                        item1.add(model);

                    }
                    if (item1 != null) {
                        mTagsAdapter = new SoftSkillsTagsfragment.TagsAdapter(item1);
                        mTags.setAdapter(mTagsAdapter);
                    } else {
                        Toast.makeText(getContext(), "NO DATA", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    //pDialog.dismiss();
                    e.printStackTrace();
                    Toast.makeText(getContext(), "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Some Error==> "+error.toString(), Toast.LENGTH_SHORT).show();


            }
        });
        VolleyRequestsent.getInstance().addRequestQueue(stringRequest);
    }





    //Adapter Class start from here

    public class TagsAdapter extends RecyclerView.Adapter<SoftSkillsTagsfragment.TagsAdapter.CheckableContactViewHolder> {

        private String[] data = new String[]{
                "poor appetite",
                "headaches",
                "diarrhea",
                "generalized aches and pains",
                "lethargy",
                "fever",
                "weakness"
        };
        private StringBuilder builder = new StringBuilder();
        private SparseBooleanArray booleanArray = new SparseBooleanArray();
        ArrayList<TagsModel> Items;
        public String send = "";
        private List<String> filteredList = new ArrayList<>();

        public TagsAdapter(ArrayList<TagsModel> item) {
            this.Items = item;
            // Collections.addAll(item);
        }

        @Override
        public SoftSkillsTagsfragment.TagsAdapter.CheckableContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_checkable_tags, parent, false);
            return new SoftSkillsTagsfragment.TagsAdapter.CheckableContactViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(SoftSkillsTagsfragment.TagsAdapter.CheckableContactViewHolder holder, int position) {
            final TagsModel model = Items.get(position);
            Log.d("Adapter", "NAME IS " + model.getName());
            holder.name.setText(model.getName());
            holder.s_id.setText(model.getId());
            //holder.name.setText(filteredList.get(position));
        }

        @Override
        public int getItemCount() {
            return Items.size();
        }

        /*        public void filterItems(CharSequence text) {
                    item.clear();
                    if (TextUtils.isEmpty(text)) {
                        Collections.addAll(item);
                    } else {
                        for (String s : data) {
                            if (s.contains(text)) {
                                filteredList.add(s);
                            }
                        }
                    }
                    notifyDataSetChanged();
                }*/
        public void filterlist(List<TagsModel> newlist) {
            Items = new ArrayList<>();
            Items.addAll(newlist);
            notifyDataSetChanged();

        }

        @Override
        public int getItemViewType(int position) {
            return Math.abs(Items.get(position).hashCode());
        }
        public class CheckableContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            public final TextView name;
            public final CheckBox selection;
            public final TextView s_id;
            public int coonter = 0;

            public CheckableContactViewHolder(View itemView) {
                super(itemView);
                name = (TextView) itemView.findViewById(R.id.tv_contact_name);
                s_id = itemView.findViewById(R.id.s_id);
                selection = (CheckBox) itemView.findViewById(R.id.cb_contact_selection);
                selection.setOnClickListener(this);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selection.performClick();
                    }
                });
            }

            @Override
            public void onClick(View v) {
                if (booleanArray.get(getAdapterPosition())) {
                    booleanArray.put(getAdapterPosition(), false);
                } else {
                    booleanArray.put(getAdapterPosition(), true);
                }
                updateCheckedData(v, getAdapterPosition());
                String email = name.getText().toString();
                Log.d("DATA CHECK KR", "OOOOKKK" + email + s_id.getText());
                builder.append(s_id.getText() + ",");
                Log.d("counter", "check counter" + builder);
                Uri imgUrl = Math.random() > .7d ? null : Uri.parse("https://robohash.org/" + Math.abs(email.hashCode()));
                Contact contact = new Contact(null, null, null, email, imgUrl);

                if (selection.isChecked()) {
                    int valueIs = mChipsView.getChips().size();
                    Log.d("counter", "check counter" + valueIs);
                    if (valueIs < 10) {
                        boolean indelibe = Math.random() > 0.8f;
                        mChipsView.addChip(email, imgUrl, contact, indelibe);
                    } else {
                        selection.setChecked(false);
                        Toast.makeText(getContext(), "Limit completed", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    mChipsView.removeChipBy(contact);
                }
            }
        }
    }


    //Adapter class ended here



    public void updateCheckedData(View view, int position) {
        if (((CheckBox) view).isChecked()) {

            if (counter<6){
                temmArray.add(item1.get(position).getName());
                Log.d("MainActivity","ARRAY"+temmArray);
                //String url=historyModels.get(position).getUrlpath();
                //Log.d("url","thisisurl"+url);
                // parent.setLocation(historyModels.get(position).getUrlpath());
                counter++;
            }else {
                Toast.makeText(getContext(), "limit increase", Toast.LENGTH_SHORT).show();
            }
        } else {
            temmArray.remove(item1.get(position).getName());
            Log.d("MainActivity","ARRAY"+temmArray);
            counter--;
        }
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Your Soft Skills");
        super.onViewCreated(view, savedInstanceState);
    }

}
