package com.health.life.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.health.life.R;
import com.health.life.model.bean.output.PicDetailOutput;
import com.health.life.utils.Config;
import com.squareup.picasso.Picasso;

/**
 * Created by gaofei on 2016/4/21.
 */

    public class PicDetailAdapter extends PagerAdapter {
        private PicDetailOutput picDetailOutput;
    private Context context;

        public PicDetailAdapter(Context context ,PicDetailOutput picDetailOutput) {
            this.picDetailOutput = picDetailOutput;
            this.context = context;
        }

        @Override
        public int getCount() {

            if (picDetailOutput.list != null && picDetailOutput.list.size() > 0) {
                return picDetailOutput.list.size();
            } else {
                return 0;
            }
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.activity_pic_detail, null);
            ImageView image =(ImageView) view.findViewById(R.id.picimage);
            Picasso.with(context)
                .load(Config.BASE_IMAGE_URL + picDetailOutput.list.get(position).src)
                    .tag(this)
                .into(image);
            container.addView(view);
            return view;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }

