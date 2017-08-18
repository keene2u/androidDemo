/*
 * Copyright 2017 (C) CodePlay Studio. All rights reserved.
 *
 * All source code within this app is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package my.com.codeplay.android_demo.viewgroups;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import my.com.codeplay.android_demo.R;

public class GridViewActivity extends AppCompatActivity {
    ////detect in list, which appslist in phone
    private List<ResolveInfo> appsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        ////load the apps
        loadApps();

        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new AppsAdapter());
    }

    private void loadApps() {
        ////create intent, and set the action main n launcher, so can view at application drawer, user can see
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        ////if those app got category launcher n action, then will display as below
        appsList = getPackageManager().queryIntentActivities(mainIntent, 0);
    }

    private class AppsAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (appsList==null || appsList.isEmpty())
                return 0;
            return appsList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView==null) {
                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_gridview, parent, false);

                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.icon);
                holder.text  = (TextView) convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ResolveInfo appInfo = appsList.get(position);
            holder.image.setImageDrawable(appInfo.activityInfo.loadIcon(getPackageManager()));
            holder.text.setText(appInfo.activityInfo.loadLabel(getPackageManager()));

            return convertView;
        }
    }


}
