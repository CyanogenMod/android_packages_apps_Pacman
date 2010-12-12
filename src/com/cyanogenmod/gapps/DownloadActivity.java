package com.cyanogenmod.gapps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class DownloadActivity extends Activity {

    private String[] mApps;
    private CheckBox mGmail;
    private CheckBox mChromeToPhone;
    private CheckBox mGoogleGoggles;
    private CheckBox mGoogleMaps;
    private CheckBox mGoogleSearch;
    private CheckBox mGoogleVoice;
    private CheckBox mStreetView;
    private CheckBox mVoiceSearch;
    private CheckBox mYoutube;

    private Button mOkButton;
    private Button mCancelButton;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle(R.string.activity_title);

        //		Set the length of the array to the number of checkboxes initialized;
        mApps = new String[9];

        mGmail = (CheckBox) findViewById(R.id.gmail);
        mChromeToPhone = (CheckBox) findViewById(R.id.chrometophone);
        mGoogleGoggles = (CheckBox) findViewById(R.id.googlegoggles);
        mGoogleMaps = (CheckBox) findViewById(R.id.googlemaps);
        mGoogleSearch = (CheckBox) findViewById(R.id.googlesearch);
        mGoogleVoice = (CheckBox) findViewById(R.id.googlevoice);
        mStreetView = (CheckBox) findViewById(R.id.streetview);
        mVoiceSearch = (CheckBox) findViewById(R.id.voicesearch);
        mYoutube = (CheckBox) findViewById(R.id.youtube);

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mCancelButton = (Button) findViewById(R.id.main_btn_cancel);

        mGmail.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    mApps[0] = "market://details?id=com.google.android.gm";
                } else {
                    mApps[0] = null;
                }
            }
        });
        mChromeToPhone.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    mApps[1] = "market://details?id=com.google.android.apps.chrometophone";
                } else {
                    mApps[1] = null;
                }
            }
        });
        mGoogleGoggles.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    mApps[2] = "market://details?id=com.google.android.apps.unveil";
                } else {
                    mApps[2] = null;
                }
            }
        });
        mGoogleMaps.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    mApps[3] = "market://details?id=com.google.android.apps.maps";
                } else {
                    mApps[3] = null;
                }
            }
        });
        mGoogleSearch.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    mApps[4] = "market://details?id=com.google.android.googlequicksearchbox";
                } else {
                    mApps[4] = null;
                }
            }
        });
        mGoogleVoice.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    mApps[5] = "market://details?id=com.google.android.apps.googlevoice";
                } else {
                    mApps[5] = null;
                }
            }
        });
        mStreetView.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    mApps[6] = "market://details?id=com.google.android.street";
                } else {
                    mApps[6] = null;
                }
            }
        });
        mVoiceSearch.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    mApps[7] = "market://details?id=com.google.android.voicesearch";
                } else {
                    mApps[7] = null;
                }
            }
        });
        mYoutube.setOnCheckedChangeListener(new OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
                if(isChecked){
                    mApps[8] = "market://details?id=com.google.android.youtube";
                } else {
                    mApps[8] = null;
                }
            }
        });

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mOkButton.setOnClickListener(new OnClickListener(){
            public void onClick(View arg0){
                for(int k = 0; k < mApps.length; k++){
                    if(mApps[k] != null) {
                        Uri uri = Uri.parse(mApps[k]);
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    }
                };
            }
        });
        mCancelButton = (Button) findViewById(R.id.main_btn_cancel);
        mCancelButton.setOnClickListener(new OnClickListener(){
            public void onClick(View arg0){
                for(int k = 0; k < mApps.length; k++){
                    mApps[k] = null;
                }
                finish();
            }
        });
    }
}
