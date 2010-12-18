package com.teamdouche.pacman;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
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

    private String[] mAppPackages;
    private String[] mAppNames;
    private List<CheckBox> mAppCheckBoxes;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle(R.string.activity_title);

        mPM = getPackageManager();
        
        Resources res = getResources();
        mAppPackages = res.getStringArray(R.array.app_packages);
        mAppNames = res.getStringArray(R.array.app_names);
        mAppCheckBoxes = new ArrayList<CheckBox>();

        LinearLayout appContainer = (LinearLayout) findViewById(R.id.app_container);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < mAppPackages.length; i++) {
            CheckBox cb = new CheckBox(getApplicationContext());
            cb.setText(mAppNames[i]);
            cb.setLayoutParams(layoutParams);

            appContainer.addView(cb);
            mAppCheckBoxes.add(cb);
        }

        disableInstalled();

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mSelectAll = (Button) findViewById(R.id.main_btn_selectall);
        mCancelButton = (Button) findViewById(R.id.main_btn_cancel);

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mOkButton.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                for (int i = 0, s = mAppCheckBoxes.size(); i < s; i++) {
                    if (mAppCheckBoxes.get(i).isChecked())
                        startActivity(getIntent(mAppPackages[i]));
                }
                ComponentName name = new ComponentName("com.teamdouche.pacman", "StartupActivity");
                mPM.setComponentEnabledSetting(name, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 0);
            }
        });
        mSelectAll = (Button) findViewById(R.id.main_btn_selectall);
        mSelectAll.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                for (int i = 0, s = mAppCheckBoxes.size(); i < s; i++) {
                    if (!mAppCheckBoxes.get(i).isChecked() && mAppCheckBoxes.get(i).isEnabled()) {
                        mAppCheckBoxes.get(i).setChecked(true);
                    }
                }
            }
        });
        mCancelButton = (Button) findViewById(R.id.main_btn_cancel);
        mCancelButton.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                ComponentName name = new ComponentName("com.teamdouche.pacman", "StartupActivity");
                mPM.setComponentEnabledSetting(name, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 0);
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
        for (int i = 0, s = mAppCheckBoxes.size(); i < s; i++) {
            mAppCheckBoxes.get(i).setEnabled(!isInstalled(mAppPackages[i]));
        }
    }
}
