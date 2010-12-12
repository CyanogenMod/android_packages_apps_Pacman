package com.cyanogenmod.gapps;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.cyanogenmod.gapps.utils.Constants;

public class DownloadActivity extends Activity {
    private PackageManager mPM;
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

        mPM = getPackageManager();

        mGmail = (CheckBox) findViewById(R.id.gmail);
        mChromeToPhone = (CheckBox) findViewById(R.id.chrometophone);
        mGoogleGoggles = (CheckBox) findViewById(R.id.googlegoggles);
        mGoogleMaps = (CheckBox) findViewById(R.id.googlemaps);
        mGoogleSearch = (CheckBox) findViewById(R.id.googlesearch);
        mGoogleVoice = (CheckBox) findViewById(R.id.googlevoice);
        mStreetView = (CheckBox) findViewById(R.id.streetview);
        mVoiceSearch = (CheckBox) findViewById(R.id.voicesearch);
        mYoutube = (CheckBox) findViewById(R.id.youtube);

        disableInstalled();

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mCancelButton = (Button) findViewById(R.id.main_btn_cancel);

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mOkButton.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                /* Gmail */
                if (mGmail.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_GMAIL));
                }

                /* Chrome to Phone */
                if (mChromeToPhone.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_CHROME));
                }

                /* Google Goggles */
                if (mGoogleGoggles.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_GOGGLES));
                }

                /* Google Maps */
                if (mGoogleMaps.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_MAPS));
                }

                /* Google Search */
                if (mGoogleSearch.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_GOOGLESEARCH));
                }

                /* Google Voice */
                if (mGoogleVoice.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_VOICE));
                }

                /* Street View */
                if (mStreetView.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_STREETVIEW));
                }

                /* Voice Search */
                if (mVoiceSearch.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_VOICESEARCH));
                }

                /* YouTube */
                if (mYoutube.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_YOUTUBE));
                }
                
                finish();
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

    private boolean isInstalled(String pname) {
        boolean installed = false;
        ApplicationInfo appinfo = null;

        try {
            appinfo = mPM.getApplicationInfo(pname, 0);
        } catch (NameNotFoundException e) {
            appinfo = null;
        }

        if (appinfo != null) {
            installed = true;
        }

        return installed;
    }

    private void disableInstalled() {
        /* Gmail */
        mGmail.setEnabled(!isInstalled(Constants.PNAME_GMAIL));

        /* Chrome to Phone */
        mChromeToPhone.setEnabled(!isInstalled(Constants.PNAME_CHROME));

        /* Google Goggles */
        mGoogleGoggles.setEnabled(!isInstalled(Constants.PNAME_GOGGLES));

        /* Google Maps */
        mGoogleMaps.setEnabled(!isInstalled(Constants.PNAME_MAPS));

        /* Google Search */
        mGoogleSearch.setEnabled(!isInstalled(Constants.PNAME_GOOGLESEARCH));

        /* Google Voice */
        mGoogleVoice.setEnabled(!isInstalled(Constants.PNAME_VOICE));

        /* Street View */
        mStreetView.setEnabled(!isInstalled(Constants.PNAME_STREETVIEW));

        /* Voice Search */
        mVoiceSearch.setEnabled(!isInstalled(Constants.PNAME_VOICESEARCH));

        /* YouTube */
        mYoutube.setEnabled(!isInstalled(Constants.PNAME_YOUTUBE));
    }
}
