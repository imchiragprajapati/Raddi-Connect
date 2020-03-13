package com.example.raddiconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class HomeFragment extends Fragment implements View.OnClickListener {
    View view;
    private CardView schedulecard, onecard, twocard, threecard, fourcard, fivecard, sixcard, sevencard;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        schedulecard = (CardView) getView().findViewById(R.id.schedule_card);
        onecard = (CardView) getView().findViewById(R.id.firstcard);
        twocard = (CardView) getView().findViewById(R.id.secondcard);
        threecard = (CardView) getView().findViewById(R.id.thirdcard);
        fourcard = (CardView) getView().findViewById(R.id.forthcard);
        fivecard = (CardView) getView().findViewById(R.id.fifthcard);
        sixcard = (CardView) getView().findViewById(R.id.sixthcard);
        sevencard = (CardView) getView().findViewById(R.id.seventhcard);

        schedulecard.setOnClickListener(this);
        onecard.setOnClickListener(this);
        twocard.setOnClickListener(this);
        threecard.setOnClickListener(this);
        fourcard.setOnClickListener(this);
        fivecard.setOnClickListener(this);
        sixcard.setOnClickListener(this);
        sevencard.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        //Intent i;
        //openDialog();

        switch (v.getId()) {

            case R.id.schedule_card:
                Intent i = new Intent(getActivity(), Schedule.class);
                startActivity(i);
                break;
            case R.id.firstcard:
                i = new Intent(getActivity(), We_purchase.class);
                startActivity(i);
                break;
            case R.id.secondcard:
                i = new Intent(getActivity(), Rate_card.class);
                startActivity(i);
                break;
            case R.id.thirdcard:
                openDialogs();
                break;
            case R.id.forthcard:
                openDialog();
                break;
            case R.id.fifthcard:
                frag();
                break;
            case R.id.sixthcard:
                i = new Intent(getActivity(), Opportunities.class);
                startActivity(i);
                break;
            case R.id.seventhcard:
                i = new Intent(getActivity(), Impact.class);
                startActivity(i);
                break;
            default:
                break;
        }


    }

    public void frag() {

        Fragment newFragment = new AboutusFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    public void openDialog() {
        Convenience_Dialog convenience_dialog = new Convenience_Dialog();
        convenience_dialog.show(getFragmentManager(), "convenience");


    }

    public void openDialogs() {
        Trust_Dialog trust_dialog = new Trust_Dialog();
        trust_dialog.show(getFragmentManager(), "trust");
    }


}
