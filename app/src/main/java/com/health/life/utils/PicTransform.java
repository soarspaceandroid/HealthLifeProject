package com.health.life.utils;

import android.graphics.Bitmap;

import com.squareup.picasso.Transformation;

/**
 * Created by gaofei on 2016/4/22.
 */
public class PicTransform implements Transformation {

    private int imageWidth ;

    public PicTransform(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    @Override
        public Bitmap transform(Bitmap source) {
            int targetWidth = imageWidth;

            double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
            int targetHeight = (int) (targetWidth * aspectRatio);
            Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
            if (result != source) {
                // Same bitmap is returned if sizes are the same
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "transformation" + " desiredWidth";
        }
}
