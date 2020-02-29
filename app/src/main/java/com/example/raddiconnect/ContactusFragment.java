package com.example.raddiconnect;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContactusFragment extends Fragment {

    View view;

    EditText txt_fullname,txt_email,txt_mobile,txt_subject,txt_message;
    Button btn_submit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_contactus, container, false);
        return view;
   }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        txt_fullname = (EditText) getView().findViewById(R.id.fullname);
        txt_email = (EditText) getView().findViewById(R.id.email);
        txt_mobile = (EditText) getView().findViewById(R.id.mobile);
        txt_subject = (EditText) getView().findViewById(R.id.subject);
        txt_message = (EditText) getView().findViewById(R.id.msg);

        btn_submit = (Button) getView().findViewById(R.id.submit);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fullname=txt_fullname.getText().toString();
                final String email=txt_email.getText().toString();
                final String mobile=txt_mobile.getText().toString();
                final String subject=txt_subject.getText().toString();
                final String msg=txt_message.getText().toString();



                if(TextUtils.isEmpty(fullname))
                {
                    Toast.makeText(getActivity(), "Please Enter your Name !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(getActivity(), "Please Enter  Email !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mobile))
                {
                    Toast.makeText(getActivity(), "Please Enter your Mobile no !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(subject))
                {
                    Toast.makeText(getActivity(), "Please Enter your Subject!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(msg))
                {
                    Toast.makeText(getActivity(), "Please Enter your Message !",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(mobile.length()<=9){
                    Toast.makeText(getActivity(), "Mobile no. should have length 10 !",Toast.LENGTH_SHORT).show();
                }
                if(mobile.length()>=9){
                    Toast.makeText(getActivity(), "Mobile no. should have length 10 !",Toast.LENGTH_SHORT).show();
                }

                openDialog();


                String Full =txt_fullname.getText().toString();
                txt_fullname.setText("");

                String Email =txt_email.getText().toString();
                txt_email.setText("");

                String Mobile =txt_mobile.getText().toString();
                txt_mobile.setText("");

                String Subject =txt_subject.getText().toString();
                txt_subject.setText("");

                String MSG =txt_message.getText().toString();
                txt_message.setText("");



            }
            public void openDialog()
            {
                Contactus_Dialog contactus_dialog= new Contactus_Dialog();
                contactus_dialog.show(getFragmentManager(),"contactus");
            }
        });
    }

}
