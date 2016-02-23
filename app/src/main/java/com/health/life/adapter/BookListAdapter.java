package com.health.life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.health.life.R;
import com.health.life.model.bean.BookListInfo;
import com.health.life.utils.Config;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by ligang967 on 16/2/23.
 */
public class BookListAdapter extends BaseAdapter {

    private List<BookListInfo.TngouEntity> tngou;

    private Context context;

    public BookListAdapter(List<BookListInfo.TngouEntity> tngou, Context context) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.booklist_adater, null);
            viewHolder.des = (TextView) convertView.findViewById(R.id.des);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BookListInfo.TngouEntity entity = tngou.get(position);

        viewHolder.name.setText(entity.getTitle() + "");

        viewHolder.des.setText(entity.getDescription() + "");

        Picasso.with(context)
                .load(Config.BASE_IMAGE_URL+entity.getImg())
//                .placeholder(R.drawable.contact_picture_placeholder)
                .resize(100,100)
                .tag(context)
                .into(viewHolder.image);

        return convertView;
    }

    private static class ViewHolder {

        TextView name;

        TextView des;

        ImageView image;

    }
}
