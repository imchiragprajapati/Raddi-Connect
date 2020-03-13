package com.example.raddiconnect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Schedule extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText phone,addrss,city;
    EditText date,time;
    Button bt_date,bt_time,bt_submit;

    LocationManager locationManager;
    LocationListener locationListener;

    private int mYear,mMonth, mDay ,mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);


        bt_date=(Button)findViewById(R.id.btn_date);
        bt_time=(Button)findViewById(R.id.btn_time);

        date=(EditText)findViewById(R.id.txt_date);
        time=(EditText)findViewById(R.id.txt_time);

        phone=findViewById(R.id.phno);
        addrss=findViewById(R.id.address);
        city=findViewById(R.id.city);

        bt_submit=(Button)findViewById(R.id.schedule);

        bt_date.setOnClickListener(this);
        bt_time.setOnClickListener(this);

        locationManager=(LocationManager) this.getSystemService(LOCATION_SERVICE);
        boolean isGPS_enabled =locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if(isGPS_enabled)
        {
            locationListener= new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double longitude = location.getLongitude();
                    double latitude = location.getLatitude();

                    try
                    {
                        Geocoder geocoder = new Geocoder(Schedule.this, Locale.getDefault());
                        List<Address>addressList = geocoder.getFromLocation(latitude,longitude,1);

                        addrss.setText(addressList.get(0).getAddressLine(0));
                        city.setText(addressList.get(0).getAdminArea());
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };
        }

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
        }




        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendData();

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
            {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,locationListener);
                addrss.setText("Getting Current Location");
                city.setText("Getting Current Location");

            }
        }
        else
        {
            addrss.setText("Access not Granted");
            city.setText("Access not Granted");
        }
    }

    private void sendData(){
        //PACK DATA IN A BUNDLE
        Bundle bundle = new Bundle();
        bundle.putString("phone", phone.getText().toString());
        bundle.putString("add", addrss.getText().toString());

        //PASS OVER THE BUNDLE TO OUR FRAGMENT
        MyPickupFragment myFragment = new MyPickupFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.commit();
        myFragment.setArguments(bundle);
        phone.setText("");



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
