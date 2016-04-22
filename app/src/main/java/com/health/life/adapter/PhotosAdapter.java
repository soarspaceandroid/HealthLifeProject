package com.health.life.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.health.life.activity.PicDetailActivity;
import com.health.life.activity.PicNewActivity;
import com.health.life.model.bean.output.PicNewOutput;
import com.health.life.utils.Config;
import com.health.life.utils.DensityUtil;
import com.health.life.utils.PicTransform;
import com.health.life.view.greedolayout.GreedoLayoutSizeCalculator;
import com.squareup.picasso.Picasso;

/**
 * Created by Julian Villella on 16-02-24.
 */
public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> implements GreedoLayoutSizeCalculator.SizeCalculatorDelegate {
    private static final int IMAGE_COUNT = 500; // number of images adapter will show


    private Context mContext;
    private PicNewOutput picNewOutput;


    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        public PhotoViewHolder(ImageView imageView) {
            super(imageView);
            mImageView = imageView;
        }
    }

    @Override
    public double aspectRatioForIndex(int index) {
        return 1.0;
    }

    public PhotosAdapter(Context context , PicNewOutput picNewOutput) {
        mContext = context;
        this.picNewOutput = picNewOutput;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(mContext);
        imageView.setTransitionName("shareview4");
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        return new PhotoViewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(final PhotoViewHolder holder,final int position) {
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicDetailActivity.showActivity((PicNewActivity)mContext ,picNewOutput.tngou.get(position).id ,holder.mImageView ,picNewOutput.tngou.get(position).title);
            }
        });
        Picasso.with(mContext)
                .load(Config.BASE_IMAGE_URL + picNewOutput.tngou.get(position).img)
                .transform(new PicTransform(DensityUtil.getScreenWidth(mContext)*2 /3))
                .tag(this)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return picNewOutput.tngou.size();
    }


}
