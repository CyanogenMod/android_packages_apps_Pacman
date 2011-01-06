package com.teamdouche.pacman;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PacmanReciever extends android.content.BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent changed_intent) {
        // TODO Auto-generated method stub
        Log.v("Pacman", "Starting Pacman");
        Intent intent = new Intent();
        intent.setClass(context, StartupActivity.class);
        intent.setAction(StartupActivity.class.getName()); 
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS); 
        context.startActivity(intent);
    }

}