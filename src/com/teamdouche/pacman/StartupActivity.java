package com.teamdouche.pacman;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

public class StartupActivity extends Activity {
    private AlertDialog alertDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up the warning
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(R.string.alert_dialog_title);
        alertDialog.setMessage(getResources().getString(R.string.alert_dialog_message));
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.v("Pacman", "Starting Pacman Download Activity");
                Intent starterIntent = new Intent(getApplicationContext(), DownloadActivity.class);
                startActivityForResult(starterIntent, 0);
                return;
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
