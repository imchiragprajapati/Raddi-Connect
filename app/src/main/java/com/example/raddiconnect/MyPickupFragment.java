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

    TextView t1,t2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_my_pickups, container, false);




        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        Bundle b1 = getArguments();
        if(b1!= null)
        {
            t1=(TextView)view.findViewById(R.id.text1);
            t2=(TextView)view.findViewById(R.id.text2);

            Integer phone=b1.getInt("phone");
            String address=b1.getString("add");
            t1.setText(phone);
            t2.setText(address);
        }
    }
}
