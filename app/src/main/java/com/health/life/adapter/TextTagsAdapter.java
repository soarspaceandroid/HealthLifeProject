package com.health.life.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.health.life.interfaces.TagsOnclickListener;
import com.health.life.model.bean.output.PicClassfyOutput;
import com.moxun.tagcloudlib.view.TagsAdapter;

/**
 * Created by gaofei on 2016/4/22.
 */
public class TextTagsAdapter extends TagsAdapter {

    private PicClassfyOutput picClassfyOutput ;
    private TagsOnclickListener listener;


    public TextTagsAdapter(@NonNull PicClassfyOutput picClassfyOutput , TagsOnclickListener ls) {
        this.picClassfyOutput = picClassfyOutput;
        this.picClassfyOutput.tngou.addAll(picClassfyOutput.tngou);
        this.listener = ls;
    }

    @Override
    public int getCount() {
        return picClassfyOutput.tngou.size();
    }

    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        final TextView tv = new TextView(context);
        Log.e("soar" , "test --- "+position+ "   "+picClassfyOutput.tngou.get(position).name);
        tv.setText(picClassfyOutput.tngou.get(position).name);
        tv.setGravity(Gravity.CENTER);
        tv.setTransitionName("shareview3");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position , tv);
            }
        });
        return tv;
    }

    @Override
    public Object getItem(int position) {
        return picClassfyOutput.tngou.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position % 7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        ((TextView) view).setTextColor(themeColor);
    }
}
