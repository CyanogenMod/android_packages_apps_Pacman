/*
 * Copyright (C) 2011 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.teamdouche.pacman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class StartupActivity extends Activity {
    private AlertDialog alertDialog;
    private int versionNo = 0;
    private Intent starterIntent = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PackageInfo pInfo = null;
        try{
            pInfo = getApplicationContext().getPackageManager().getPackageInfo("com.android.vending",PackageManager.GET_META_DATA);
        } catch (NameNotFoundException e) {
            // Market package name changed
            pInfo = null;
        }
        if(pInfo != null) {
            versionNo = pInfo.versionCode;
        }

        Log.v("Pacman", "Market version is " + versionNo);
        // Set up the warning
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(R.string.alert_dialog_title);
        alertDialog.setMessage(getResources().getString(R.string.alert_dialog_message));
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.v("Pacman", "Starting Pacman Download Activity");
                if (versionNo != 0) {
                    if (versionNo < 8006027) {
                        starterIntent = new Intent(getApplicationContext(), DownloadActivity.class);
                    } else {
                        starterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:Google%20Inc."));
                    }
                    startActivityForResult(starterIntent, 0);
                    return;
                } else {
                    finish();
                }
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        alertDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // remove this activity from the package manager.
        PackageManager pm = getPackageManager();
        pm.setApplicationEnabledSetting("com.teamdouche.pacman", PackageManager.COMPONENT_ENABLED_STATE_DISABLED, 0);
        finish();
    }
}
