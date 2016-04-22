package com.health.life.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.health.life.R;
import com.health.life.model.bean.output.PicInfoOutput;
import com.health.life.utils.Config;
import com.health.life.utils.DensityUtil;
import com.health.life.utils.PicTransform;
import com.jpardogo.listbuddies.lib.adapters.CircularLoopAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by gaofei on 2016/4/21.
 */
public class CircularAdapter extends CircularLoopAdapter {
    private static final String TAG = CircularAdapter.class.getSimpleName();

    private List<PicInfoOutput.TngouEntity> list;
    private Context mContext;
    private int mRowHeight;

    public CircularAdapter(Context context, int rowHeight, List<PicInfoOutput.TngouEntity> list) {
        mContext = context;
        mRowHeight = rowHeight;
        this.list = list;
    }

    @Override
    public PicInfoOutput.TngouEntity getItem(int position) {
        return list.get(getCircularPosition(position));
    }

    @Override
    protected int getCircularCount() {
        return list.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.picinfo_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.image.setMinimumHeight(mRowHeight);
        holder.image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.text.setText(getItem(position).title);
        Picasso.with(mContext).load(Config.BASE_IMAGE_URL + getItem(position).img).transform(new PicTransform(DensityUtil.getScreenWidth(mContext)*2/ 3)).into(holder.image);

        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView text;

        public ViewHolder(View convertView) {
            image = (ImageView) convertView.findViewById(R.id.image);
            text =(TextView)convertView.findViewById(R.id.text);
        }
    }
}