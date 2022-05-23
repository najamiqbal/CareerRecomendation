package com.fyp.careerrecomendation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.fyp.careerrecomendation.R;

public class FAQFragment extends Fragment implements View.OnClickListener{

    View view;
    ConstraintLayout expandableView1,expandableView2,expandableView3,expandableView4,expandableView5,expandableView6,expandableView7,expandableView8,expandableView9,expandableView10;
    Button arrowBtn1,arrowBtn2,arrowBtn3,arrowBtn4,arrowBtn5,arrowBtn6,arrowBtn7,arrowBtn8,arrowBtn9,arrowBtn10;
    CardView cardView1,cardView2,cardView3,cardView4,cardView5,cardView6,cardView7,cardView8,cardView9,cardView10;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.faqs_fragment,container,false);
        initialiazation();
        return view;
    }

    private void initialiazation() {
        expandableView1 = view.findViewById(R.id.expandableView1);
        expandableView2 = view.findViewById(R.id.expandableView2);
        expandableView3 = view.findViewById(R.id.expandableView3);
        expandableView4 = view.findViewById(R.id.expandableView4);
        expandableView5 = view.findViewById(R.id.expandableView5);
        expandableView6 = view.findViewById(R.id.expandableView6);
        expandableView7 = view.findViewById(R.id.expandableView7);
        expandableView8 = view.findViewById(R.id.expandableView8);
        expandableView9 = view.findViewById(R.id.expandableView9);
        expandableView10 = view.findViewById(R.id.expandableView10);

        arrowBtn1 = view.findViewById(R.id.arrowBtn1);
        arrowBtn2 = view.findViewById(R.id.arrowBtn2);
        arrowBtn3 = view.findViewById(R.id.arrowBtn3);
        arrowBtn4 = view.findViewById(R.id.arrowBtn4);
        arrowBtn5 = view.findViewById(R.id.arrowBtn5);
        arrowBtn6 = view.findViewById(R.id.arrowBtn6);
        arrowBtn7 = view.findViewById(R.id.arrowBtn7);
        arrowBtn8 = view.findViewById(R.id.arrowBtn8);
        arrowBtn9 = view.findViewById(R.id.arrowBtn9);
        arrowBtn10 = view.findViewById(R.id.arrowBtn10);

        cardView1 = view.findViewById(R.id.cardView1);
        cardView2 = view.findViewById(R.id.cardView2);
        cardView3 = view.findViewById(R.id.cardView3);
        cardView4 = view.findViewById(R.id.cardView4);
        cardView5 = view.findViewById(R.id.cardView5);
        cardView6 = view.findViewById(R.id.cardView6);
        cardView7 = view.findViewById(R.id.cardView7);
        cardView8 = view.findViewById(R.id.cardView8);
        cardView9 = view.findViewById(R.id.cardView9);
        cardView10 = view.findViewById(R.id.cardView10);

        cardView1.setOnClickListener(this);
        cardView2.setOnClickListener(this);
        cardView3.setOnClickListener(this);
        cardView4.setOnClickListener(this);
        cardView5.setOnClickListener(this);
        cardView6.setOnClickListener(this);
        cardView7.setOnClickListener(this);
        cardView8.setOnClickListener(this);
        cardView9.setOnClickListener(this);
        cardView10.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cardView1:
                if (expandableView1.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    expandableView1.setVisibility(View.VISIBLE);
                    arrowBtn1.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView1, new AutoTransition());
                    expandableView1.setVisibility(View.GONE);
                    arrowBtn1.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                break;
            case R.id.cardView2:
                if (expandableView2.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView2.setVisibility(View.VISIBLE);
                    arrowBtn2.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView2, new AutoTransition());
                    expandableView2.setVisibility(View.GONE);
                    arrowBtn2.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                break;
            case R.id.cardView3:
                if (expandableView3.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    expandableView3.setVisibility(View.VISIBLE);
                    arrowBtn3.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView3, new AutoTransition());
                    expandableView3.setVisibility(View.GONE);
                    arrowBtn3.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                break;
            case R.id.cardView4:
                if (expandableView4.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView4, new AutoTransition());
                    expandableView4.setVisibility(View.VISIBLE);
                    arrowBtn4.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView4, new AutoTransition());
                    expandableView4.setVisibility(View.GONE);
                    arrowBtn4.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                break;
            case R.id.cardView5:
                if (expandableView5.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                    expandableView5.setVisibility(View.VISIBLE);
                    arrowBtn5.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView5, new AutoTransition());
                    expandableView5.setVisibility(View.GONE);
                    arrowBtn5.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                break;
            case R.id.cardView6:
                if (expandableView6.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView6, new AutoTransition());
                    expandableView6.setVisibility(View.VISIBLE);
                    arrowBtn6.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView6, new AutoTransition());
                    expandableView6.setVisibility(View.GONE);
                    arrowBtn6.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                break;
            case R.id.cardView7:
                if (expandableView7.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView7, new AutoTransition());
                    expandableView7.setVisibility(View.VISIBLE);
                    arrowBtn7.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView7, new AutoTransition());
                    expandableView7.setVisibility(View.GONE);
                    arrowBtn7.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                break;
            case R.id.cardView8:
                if (expandableView8.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView8, new AutoTransition());
                    expandableView8.setVisibility(View.VISIBLE);
                    arrowBtn8.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView8, new AutoTransition());
                    expandableView8.setVisibility(View.GONE);
                    arrowBtn8.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                break;
            case R.id.cardView9:
                if (expandableView9.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView9, new AutoTransition());
                    expandableView9.setVisibility(View.VISIBLE);
                    arrowBtn9.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView9, new AutoTransition());
                    expandableView9.setVisibility(View.GONE);
                    arrowBtn9.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                break;
            case R.id.cardView10:
                if (expandableView10.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView10, new AutoTransition());
                    expandableView10.setVisibility(View.VISIBLE);
                    arrowBtn10.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                } else {
                    TransitionManager.beginDelayedTransition(cardView10, new AutoTransition());
                    expandableView10.setVisibility(View.GONE);
                    arrowBtn10.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                break;

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("FAQ's");
        super.onViewCreated(view, savedInstanceState);
    }
}
