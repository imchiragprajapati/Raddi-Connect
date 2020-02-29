package com.example.raddiconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Schedule extends AppCompatActivity implements View.OnClickListener {

    EditText date,time,phone,addrss;
    Button bt_date,bt_time,bt_submit;
    private int mYear,mMonth, mDay ,mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        FragmentManager manager =getSupportFragmentManager();
        final FragmentTransaction t = manager.beginTransaction();
        final MyPickupFragment m1 = new MyPickupFragment();

        bt_date=(Button)findViewById(R.id.btn_date);
        bt_time=(Button)findViewById(R.id.btn_time);

        date=(EditText)findViewById(R.id.txt_date);
        time=(EditText)findViewById(R.id.txt_time);

        phone=(EditText)findViewById(R.id.phno);
        addrss=(EditText)findViewById(R.id.address);
        bt_submit=(Button)findViewById(R.id.schedule);

        bt_date.setOnClickListener(this);
        bt_time.setOnClickListener(this);



        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle b1 = new Bundle();
                b1.putString("phone",phone.getText().toString());
                b1.putString("adrs",addrss.getText().toString());
                m1.setArguments(b1);
                t.add(R.id.container_frame_layout,m1);
                t.commit();



                String Full =date.getText().toString();
                date.setText("");

                String Email =time.getText().toString();
                time.setText("");

                String Mobile =phone.getText().toString();
                phone.setText("");

                String message = " Thank You for the Request your Pickup has been Scheduled !";
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        Schedule.this
                )
                        .setSmallIcon(R.drawable.ic_pick_up)
                        .setContentTitle("Raddi Connect")
                        .setContentText(message)
                        .setAutoCancel(true);

                Intent intent=new Intent(Schedule.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("message",message);

                PendingIntent pendingIntent = PendingIntent.getActivity(Schedule.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);

                NotificationManager notificationManager=(NotificationManager)getSystemService(
                        Context.NOTIFICATION_SERVICE
                );
                notificationManager.notify(0,builder.build());


                Intent i = new Intent(Schedule.this, MainActivity.class);
                startActivity(i);


                Toast.makeText(Schedule.this, " Your Date and Time has been Scheduled . !",Toast.LENGTH_SHORT).show();




            }
        });



    }

    @Override
    public  void onClick(View v)
    {

        if(v==bt_time)
        {
            final Calendar c = Calendar.getInstance();
            mHour=c.get(Calendar.HOUR_OF_DAY);
            mMinute=c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    time.setText(hourOfDay+":"+mMinute);
                }
            },mHour,mMinute,false);
            timePickerDialog.show();
        }

        if(v==bt_date)
        {
            final Calendar calendar= Calendar.getInstance();
            mYear=calendar.get(Calendar.YEAR);
            mMonth=calendar.get(Calendar.MONTH);
            mDay=calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    date.setText(dayOfMonth + "-" + (month+1) + "-" + year);
                }
            },mYear,mMonth,mDay);
            datePickerDialog.show();
        }

        final String mobile=phone.getText().toString();
        if(TextUtils.isEmpty(mobile))
        {
            Toast.makeText(Schedule.this, "Please Enter your Phone No. !",Toast.LENGTH_SHORT).show();
            return;
        }

        final String add=addrss.getText().toString();
        if(TextUtils.isEmpty(add))
        {
            Toast.makeText(Schedule.this, "Please Enter your Address. !",Toast.LENGTH_SHORT).show();
            return;
        }

    }


}
