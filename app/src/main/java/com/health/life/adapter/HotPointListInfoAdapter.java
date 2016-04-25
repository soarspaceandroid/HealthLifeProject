package com.health.life.adapter;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.health.life.R;
import com.health.life.model.bean.output.HotPointListOutput;
import com.health.life.utils.Config;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by ligang967 on 16/2/23.
 */
public class HotPointListInfoAdapter extends BaseAdapter {

    private List<HotPointListOutput.TngouEntity> tngou;

    private Context context;

    public HotPointListInfoAdapter(List<HotPointListOutput.TngouEntity> tngou, Context context) {
        this.tngou = tngou;
        this.context = context;
    }

    @Override
    public int getCount() {

        if (tngou != null) {
            return tngou.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.hotpoint_list_adater, null);
            viewHolder.cardView = (CardView)convertView.findViewById(R.id.card_parent);
            viewHolder.des = (TextView) convertView.findViewById(R.id.des);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(Build.VERSION.SDK_INT >= 21) {
            viewHolder.cardView.setElevation(15);
        }

        HotPointListOutput.TngouEntity entity = tngou.get(position);
        viewHolder.name.setText(entity.title + "");
        viewHolder.des.setText(entity.description + "");
        if(entity.img . contains("default.jpg")){
            viewHolder.image.setImageResource(R.drawable.u54);
        }else{
            Picasso.with(context)
                    .load(Config.BASE_IMAGE_URL+entity.img)
//                .placeholder(R.drawable.contact_picture_placeholder)
                    .resize(100,100)
                    .tag(context)
                    .into(viewHolder.image);
        }


        return convertView;
    }

    private static class ViewHolder {

        CardView cardView;

        TextView name;

        TextView des;

        ImageView image;

    }

    public void setData(List<HotPointListOutput.TngouEntity> tngou){
        this.tngou = tngou;
        notifyDataSetChanged();
    }
}
