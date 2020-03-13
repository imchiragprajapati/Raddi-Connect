package com.example.raddiconnect;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyPickupFragment extends Fragment  {

    private  TextView t1,t2;
    public MyPickupFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_my_pickups, container, false);

        if (getArguments()!=null)
        {
        t1=(TextView)view.findViewById(R.id.phoneid);
        t2=(TextView)view.findViewById(R.id.addressid);
        String phone=this.getArguments().getString("phone").toString();
        String address=this.getArguments().getString("add").toString();
        t1.setText("NAME : "+phone);
        t2.setText("NAME : "+address);

        }
        return  view;
    }

}