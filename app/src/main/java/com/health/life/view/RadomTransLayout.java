package com.health.life.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Path;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.health.life.model.bean.output.HotPointClassfyOutput;
import com.health.life.utils.DensityUtil;
import com.health.life.utils.StatusBarCompat;

import java.util.Random;

/**
 * Created by gaofei on 2016/4/21.
 */
public class RadomTransLayout extends RelativeLayout {

    private HotPointClassfyOutput hotPointClassfyOutput;
    private Context context;
    private int width , height;
    private Random random;
    private boolean isFinish = true;
    private int[] colors = {Color.parseColor("#ff00ff") , Color.RED , Color.BLUE , Color.WHITE , Color.YELLOW , Color.parseColor("#539728") , Color.parseColor("#a55615")};
    private int actionBarHeight = 0;
    public RadomTransLayout(Context context) {
        super(context);
    }

    public RadomTransLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context =context;
    }

    public RadomTransLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context =context;
    }


    public void setData(HotPointClassfyOutput hotPointClassfyOutput){
        this.hotPointClassfyOutput = hotPointClassfyOutput;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics())+ StatusBarCompat.getStatusBarHeight(context);
        }
        initView();
    }

    public void initView(){
        width = context.getResources().getDisplayMetrics().widthPixels;
        height = context.getResources().getDisplayMetrics().heightPixels;
        random = new Random();
        CountDownTimer timer = new CountDownTimer(10000 , 800) {
            @Override
            public void onTick(long millisUntilFinished) {
                addView(-1);
            }

            @Override
            public void onFinish() {

            }
        };

        timer.start();

    }



    private void addView(int positions){
        if(!isFinish){
            return;
        }
        int item = 0;
        if(positions == -1){
            item = random.nextInt(hotPointClassfyOutput.tngou.size());
        }else{
            item = positions;
        }
        final int position = item;
        final int x = random.nextInt(width-250);
        final int y = random.nextInt(300);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(x, y, 0, 0);
        final TextView textView = new TextView(context);
        textView.setText(hotPointClassfyOutput.tngou.get(position).name);
        addView(textView, params);
        addViewAnimation(textView);
        textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                HotPointListActivity.showActivity((HotPointDetailActivity) context, hotPointClassfyOutput.tngou.get(position).id , textView);
            }
        });
        textView.setTransitionName("shareview3");
        textView.setTextColor(colors[random.nextInt(colors.length-1)]);
        textView.setTextSize(random.nextInt(18) + 17);
        textView.postDelayed(new Runnable() {
            @Override
            public void run() {
                animationStart(textView, x, y);
            }
        }, 500);
    }


    private void animationStart(final View view , int x , int y){
        AnimatorSet set = new AnimatorSet();
        Path path = new Path();
        path.moveTo(x, y);
        if(x < width / 2){
            path.quadTo(x+width/ 2 , y , DensityUtil.px2dip(context ,width / 2) , DensityUtil.getScreenHeight(context) - actionBarHeight);
        }else{
            path.quadTo(x - width / 2 , y , DensityUtil.px2dip(context ,width / 2) , DensityUtil.getScreenHeight(context) - actionBarHeight);
        }
        ObjectAnimator pathO = ObjectAnimator.ofFloat(view , View.X , View.Y , path);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view , "scaleY" , 1.0f , 0.0f);
        ObjectAnimator apha = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        set.setDuration(random.nextInt(15000) % (15000 - 8000 + 1000) + 8000);
        set.playTogether(pathO, scaleX, scaleY , apha);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                removeView(view);
                addView(-1);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        set.start();
    }

    private void addViewAnimation(View view){

        AnimatorSet set = new AnimatorSet();
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX",0.0f, 1.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view , "scaleY" , 0.0f, 1.0f );
        set.setDuration(500);
        set.setInterpolator(new BounceInterpolator());
        set.playTogether(scaleX, scaleY);
        set.start();

    }

    public void setEnables(boolean enable){
        isFinish = enable;
    }

}
