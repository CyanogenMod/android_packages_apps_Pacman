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
    private CheckBox mFacebook;
    private CheckBox mGoogleGoggles;
    private CheckBox mGoogleMaps;
    private CheckBox mGoogleSearch;
    private CheckBox mGoogleVoice;
    private CheckBox mKickback;
    private CheckBox mSoundback;
    private CheckBox mStreetView;
    private CheckBox mTalkback;
    private CheckBox mTwitter;
    private CheckBox mVoiceSearch;
    private CheckBox mYoutube;

    private Button mOkButton;
    private Button mSelectAll;
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
        mFacebook = (CheckBox) findViewById(R.id.facebook);
        mGoogleGoggles = (CheckBox) findViewById(R.id.googlegoggles);
        mGoogleMaps = (CheckBox) findViewById(R.id.googlemaps);
        mGoogleSearch = (CheckBox) findViewById(R.id.googlesearch);
        mGoogleVoice = (CheckBox) findViewById(R.id.googlevoice);
        mKickback= (CheckBox) findViewById(R.id.kickback);
        mSoundback= (CheckBox) findViewById(R.id.soundback);
        mStreetView = (CheckBox) findViewById(R.id.streetview);
        mTalkback= (CheckBox) findViewById(R.id.talkback);
        mTwitter= (CheckBox) findViewById(R.id.twitter);
        mVoiceSearch = (CheckBox) findViewById(R.id.voicesearch);
        mYoutube = (CheckBox) findViewById(R.id.youtube);

        disableInstalled();

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mSelectAll = (Button) findViewById(R.id.main_btn_selectall);
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

                /* Facebook */
                if (mFacebook.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_FACEBOOK));
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

                /* Kickback */
                if (mKickback.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_KICKBACK));
                }

                /* Soundback */
                if (mSoundback.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_SOUNDBACK));
                }

                /* Street View */
                if (mStreetView.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_STREETVIEW));
                }

                /* Talkback */
                if (mTalkback.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_TALKBACK));
                }

                /* Twitter */
                if (mTalkback.isChecked()) {
                    startActivity(getIntent(Constants.PNAME_TWITTER));
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
        mSelectAll = (Button) findViewById(R.id.main_btn_selectall);
        mSelectAll.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                if(!mGmail.isChecked() && mGmail.isEnabled())
                    mGmail.setChecked(true);
                if(!mChromeToPhone.isChecked() && mChromeToPhone.isEnabled())
                    mChromeToPhone.setChecked(true);
                if(!mFacebook.isChecked() && mFacebook.isEnabled())
                    mFacebook.setChecked(true);
                if(!mGoogleGoggles.isChecked() && mGoogleGoggles.isEnabled())
                    mGoogleGoggles.setChecked(true);
                if(!mGoogleMaps.isChecked() && mGoogleMaps.isEnabled())
                    mGoogleMaps.setChecked(true);
                if(!mGoogleSearch.isChecked() && mGoogleSearch.isEnabled())
                    mGoogleSearch.setChecked(true);
                if(!mGoogleVoice.isChecked() && mGoogleVoice.isEnabled())
                    mGoogleVoice.setChecked(true);
                if(!mKickback.isChecked() && mKickback.isEnabled())
                    mKickback.setChecked(true);
                if(!mSoundback.isChecked() && mSoundback.isEnabled())
                    mSoundback.setChecked(true);
                if(!mStreetView.isChecked() && mStreetView.isEnabled())
                    mStreetView.setChecked(true);
                if(!mTalkback.isChecked() && mTalkback.isEnabled())
                    mTalkback.setChecked(true);
                if(!mTwitter.isChecked() && mTwitter.isEnabled())
                    mTwitter.setChecked(true);
                if(!mVoiceSearch.isChecked() && mVoiceSearch.isEnabled())
                    mVoiceSearch.setChecked(true);
                if(!mYoutube.isChecked() && mYoutube.isEnabled())
                    mYoutube.setChecked(true);
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

        /* Facebook */
        mFacebook.setEnabled(!isInstalled(Constants.PNAME_FACEBOOK));

        /* Google Goggles */
        mGoogleGoggles.setEnabled(!isInstalled(Constants.PNAME_GOGGLES));

        /* Google Maps */
        mGoogleMaps.setEnabled(!isInstalled(Constants.PNAME_MAPS));

        /* Google Search */
        mGoogleSearch.setEnabled(!isInstalled(Constants.PNAME_GOOGLESEARCH));

        /* Google Voice */
        mGoogleVoice.setEnabled(!isInstalled(Constants.PNAME_VOICE));

        /* Kickback */
        mKickback.setEnabled(!isInstalled(Constants.PNAME_KICKBACK));

        /* Soundback */
        mSoundback.setEnabled(!isInstalled(Constants.PNAME_SOUNDBACK));

        /* Street View */
        mStreetView.setEnabled(!isInstalled(Constants.PNAME_STREETVIEW));

        /* Talkback */
        mTalkback.setEnabled(!isInstalled(Constants.PNAME_TALKBACK));

        /* Twitter */
        mTwitter.setEnabled(!isInstalled(Constants.PNAME_TWITTER));

        /* Voice Search */
        mVoiceSearch.setEnabled(!isInstalled(Constants.PNAME_VOICESEARCH));

        /* YouTube */
        mYoutube.setEnabled(!isInstalled(Constants.PNAME_YOUTUBE));
    }
}
