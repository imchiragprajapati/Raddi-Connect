package com.example.raddiconnect;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileFragment extends Fragment {

    View view;

    EditText txt_fullname,txt_username,txt_email,txt_password;
    Button btn_register;
    RadioButton radioGenderMale,radioGenderFemale;
    String gender="";
    FirebaseAuth firebaseAuth;

    DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }







    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        txt_fullname=(EditText)getView().findViewById(R.id.fullname);
        txt_username=(EditText)getView().findViewById(R.id.user_name);
        txt_email=(EditText)getView().findViewById(R.id.email);
        txt_password=(EditText)getView().findViewById(R.id.password);

            btn_register=(Button) getView().findViewById(R.id.signin);

            radioGenderMale=(RadioButton) getView().findViewById(R.id.radio_male);
            radioGenderFemale=(RadioButton) getView().findViewById(R.id.radio_female);

            databaseReference=FirebaseDatabase.getInstance().getReference("User");
            firebaseAuth = FirebaseAuth.getInstance();




            btn_register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String fullName=txt_fullname.getText().toString();
                    final String userName=txt_username.getText().toString();
                    final String Email=txt_email.getText().toString();
                    String password=txt_password.getText().toString();

                    if(radioGenderMale.isChecked())
                    {
                        gender="Male";
                    }
                    if(radioGenderFemale.isChecked())
                    {
                        gender="Female";
                    }

                    if(TextUtils.isEmpty(fullName))
                    {
                        Toast.makeText(getActivity(), "Please Enter your Name !",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(userName))
                    {
                        Toast.makeText(getActivity(), "Please Enter Username !",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(Email))
                    {
                        Toast.makeText(getActivity(), "Please Enter your Email !",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(TextUtils.isEmpty(password))
                    {
                        Toast.makeText(getActivity(), "Please Enter your Password !",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(password.length()<6){
                        Toast.makeText(getActivity(), "Please Enter Password more than 6 letters !",Toast.LENGTH_SHORT).show();
                    }

                    firebaseAuth.createUserWithEmailAndPassword(Email, password)
                            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        user information = new user(
                                                fullName,
                                                userName,
                                                Email,
                                                gender
                                        );

                                        FirebaseDatabase.getInstance().getReference("User")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(getActivity(), "Registration complete !",Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getActivity(),MainActivity.class));

                                            }
                                        });

                                    } else {

                                        Toast.makeText(getActivity(), "Something went wrong!",Toast.LENGTH_SHORT).show();


                                    }

                                    // ...
                                }
                            });
                }
            });
    }

}
