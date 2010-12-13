package com.cyanogenmod.gapps;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class DownloadActivity extends Activity {
    private PackageManager mPM;

    private Button mOkButton;
    private Button mSelectAll;
    private Button mCancelButton;

    private String[] mGappPackages;
    private String[] mGappNames;
    private List<CheckBox> mGappCheckBoxes;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle(R.string.activity_title);

        mPM = getPackageManager();
        
        Resources res = getResources();
        mGappPackages = res.getStringArray(R.array.gapp_packages);
        mGappNames = res.getStringArray(R.array.gapp_names);
        mGappCheckBoxes = new ArrayList<CheckBox>();

        LinearLayout gappContainer = (LinearLayout) findViewById(R.id.gapp_container);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < mGappPackages.length; i++) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(mGappNames[i]);
            cb.setLayoutParams(layoutParams);

            gappContainer.addView(cb);
            mGappCheckBoxes.add(cb);
        }

        disableInstalled();

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mSelectAll = (Button) findViewById(R.id.main_btn_selectall);
        mCancelButton = (Button) findViewById(R.id.main_btn_cancel);

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mOkButton.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                for (int i = 0, s = mGappCheckBoxes.size(); i < s; i++) {
                    if (mGappCheckBoxes.get(i).isChecked())
                        startActivity(getIntent(mGappPackages[i]));
                }

                finish();
            }
        });
        mSelectAll = (Button) findViewById(R.id.main_btn_selectall);
        mSelectAll.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                for (int i = 0, s = mGappCheckBoxes.size(); i < s; i++) {
                    if (!mGappCheckBoxes.get(i).isChecked() && mGappCheckBoxes.get(i).isEnabled()) {
                        mGappCheckBoxes.get(i).setChecked(true);
                    }
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
        for (int i = 0, s = mGappCheckBoxes.size(); i < s; i++) {
            mGappCheckBoxes.get(i).setEnabled(!isInstalled(mGappPackages[i]));
        }
    }
}
