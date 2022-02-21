package com.fyp.careerrecomendation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fyp.careerrecomendation.R;

public class UserHomeFragment extends Fragment implements View.OnClickListener{

    View view;
    CardView cardView_quiz, cardView_detectColor,cardView_doctors;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.user_home_fragment,container,false);
        initialization();
        return view;
    }

    private void initialization() {
        cardView_detectColor=view.findViewById(R.id.uni_desk_cardview);
        cardView_quiz=view.findViewById(R.id.carreer_detect_cardview);
        cardView_doctors=view.findViewById(R.id.conelors_cardview);

        cardView_quiz.setOnClickListener(this);
        cardView_doctors.setOnClickListener(this);
        cardView_detectColor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.carreer_detect_cardview:


                break;
            case R.id.conelors_cardview:
                ConselorsListFragment doctorsListFragment = new ConselorsListFragment();
                FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
                fragmentTransaction2.replace(R.id.user_main_frame, doctorsListFragment);
                fragmentTransaction2.addToBackStack("forgetpass_fragment");
                fragmentTransaction2.commit();
                break;
            case R.id.uni_desk_cardview:

                break;


        }
    }
}