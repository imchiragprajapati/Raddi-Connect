package com.example.raddiconnect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class Introduction extends AppIntro {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addSlide(AppIntroFragment.newInstance("Step 1", "Login to your account", R.drawable.intro1, ContextCompat.getColor(getApplicationContext(),R.color.green1)));

        addSlide(AppIntroFragment.newInstance("Step 2", "Check Scrap Rates", R.drawable.intro2, ContextCompat.getColor(getApplicationContext(),R.color.green2)));

        addSlide(AppIntroFragment.newInstance("Step 3", "Schedule a Scrap Pickup", R.drawable.intro3, ContextCompat.getColor(getApplicationContext(),R.color.green3)));

        addSlide(AppIntroFragment.newInstance("Step 4", "Our scrap collector will come for scrap pickup", R.drawable.intro4, ContextCompat.getColor(getApplicationContext(),R.color.green4)));

        setFadeAnimation();

        sharedPreferences=getApplicationContext().getSharedPreferences("MyPreferences",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        if(sharedPreferences!=null)
        {
            boolean checkShared  = sharedPreferences.getBoolean("checkStated",false);
            if(checkShared==true)
            {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        }
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.

        startActivity(new Intent(getApplicationContext(),Splashscreen.class));
        editor.putBoolean("checkStated",false).commit();
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        startActivity(new Intent(getApplicationContext(),Splashscreen.class));
        editor.putBoolean("checkStated",true).commit();
        finish();
    }
}
