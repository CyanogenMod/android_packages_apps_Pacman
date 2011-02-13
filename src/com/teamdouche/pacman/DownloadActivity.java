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

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;

public class DownloadActivity extends Activity {
    private PackageManager mPM;

    private Button mOkButton;
    private Button mSelectAll;
    private Button mCancelButton;
    private ListView mListView;
    private PackageAdapter mAdapter;
    private static Class StringClass = R.string.class;

    private class Package {
        public Package(String namespace) {
            Namespace = namespace;
            
            String resourceName = Namespace.replace('.', '_');
            Field field;
            try {
                field = StringClass.getField(resourceName);
                int resourceId = (Integer)field.get(null);
                Name = getResources().getString(resourceId);
                AlreadyInstalled = isInstalled(Namespace);
            }
            catch (Exception e) {
                Name = Namespace;
            }
        }
        public String Name;
        public String Namespace;
        public boolean Install;
        public boolean AlreadyInstalled;
    }
    
    private class PackageAdapter extends ArrayAdapter<Package> {
        private LayoutInflater mInflater;
        
        public PackageAdapter(Context context) {
            super(context, R.layout.app_package);
            
            mInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        }
        
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final Package pkg = getItem(position);
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.app_package, null);
            }
            CheckBox view = (CheckBox)convertView.findViewById(R.id.checkbox);
            view.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    pkg.Install = isChecked;
                }
            });
            
            view.setEnabled(!pkg.AlreadyInstalled);
            view.setChecked(pkg.AlreadyInstalled || pkg.Install);
            view.setText(pkg.Name);            
            return view;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setTitle(R.string.activity_title);

        mPM = getPackageManager();

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mSelectAll = (Button) findViewById(R.id.main_btn_selectall);
        mCancelButton = (Button) findViewById(R.id.main_btn_cancel);
        mListView = (ListView) findViewById(R.id.listview);
        mAdapter = new PackageAdapter(this);
        for (String p: getResources().getStringArray(R.array.app_packages)) {
            Package pkg = new Package(p);
            mAdapter.add(pkg);
        }
        
        mListView.setAdapter(mAdapter);

        mOkButton = (Button) findViewById(R.id.main_btn_ok);
        mOkButton.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                for (int i = mAdapter.getCount() - 1; i >= 0 ; i--) {
                    Package pkg = mAdapter.getItem(i);
                    if (pkg.Install && !pkg.AlreadyInstalled)
                        startActivity(getMarketIntent(pkg.Namespace));
                }
                finish();
            }
        });
        mSelectAll = (Button) findViewById(R.id.main_btn_selectall);
        mSelectAll.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                for (int i = 0; i < mAdapter.getCount(); i++) {
                    Package pkg = mAdapter.getItem(i);
                    pkg.Install = true;
                }
                mAdapter.notifyDataSetChanged();
            }
        });
        mCancelButton = (Button) findViewById(R.id.main_btn_cancel);
        mCancelButton.setOnClickListener(new OnClickListener(){
            public void onClick(View view){
                finish();
            }
        });
    }

    private Intent getMarketIntent(String pname) {
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
}
