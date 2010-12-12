package com.cyanogenmod.gapps;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.cyanogenmod.gapps.utils.Constants;

public class DownloadActivity extends Activity {

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

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mOkButton.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                if (mGmail.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_GMAIL));
                }

                if (mChromeToPhone.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_CHROME));
                }

                if (mGoogleGoggles.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_GOGGLES));
                }

                if (mGoogleMaps.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_MAPS));
                }

                if (mGoogleSearch.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_GOOGLESEARCH));
                }

                if (mGoogleVoice.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_VOICE));
                }

                if (mStreetView.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_STREETVIEW));
                }

                if (mVoiceSearch.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_VOICESEARCH));
                }

                if (mYoutube.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_YOUTUBE));
                }
            }
        });
        mCancelButton = (Button) findViewById(R.id.main_btn_cancel);
        mCancelButton.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });
    }

    private Intent getIntent(String pname) {
        Uri uri = Uri.parse("market://details?id=" + pname);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        return intent;
    }
}
