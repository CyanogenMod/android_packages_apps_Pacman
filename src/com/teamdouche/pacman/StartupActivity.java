package com.teamdouche.pacman;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class StartupActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v("Pacman", "Starting Pacman Download Activity");
        Intent starterIntent = new Intent(this, DownloadActivity.class);
        startActivityForResult(starterIntent, 0);
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
