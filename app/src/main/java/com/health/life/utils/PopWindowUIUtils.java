package com.health.life.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.health.life.R;
import com.health.life.interfaces.CommentPostListener;

/**
 * Created by gaofei on 2016/4/25.
 */
public class PopWindowUIUtils {


    /**
     * PopulWindow底部选择框
     *
     * @param mActivity
     */
    public static void commentPop(final Activity mActivity,final String title , final CommentPostListener listener) {
        final View popView = LayoutInflater.from(mActivity.getBaseContext()).inflate(R.layout.comment_layout, null);
        final PopupWindow popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        ((TextView)popView.findViewById(R.id.title)).setText(title);
        final RelativeLayout popParent = (RelativeLayout) popView.findViewById(R.id.pop_layout);
        CardView submit = (CardView) popView.findViewById(R.id.submit_comment);
        final EditText text = (EditText)popView.findViewById(R.id.comment_content);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doAnimationUpToBottom(popView, popParent, popView.getHeight(), popWindow);
                if (null != listener) {
                    listener.submit(text.getText().toString());
                }
            }
        });



        popView.setFocusable(true);
        popView.setFocusableInTouchMode(true);
        popWindow.setFocusable(true);
//        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);
//        popWindow.setAnimationStyle(R.style.PopupAnimation);
        popWindow.showAtLocation(popView, Gravity.BOTTOM, 0, 0);

        doAnimationBottomToUp(popView, popParent, popView.getHeight());

        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                doAnimationUpToBottom(popView, popParent, popView.getHeight(), popWindow);
            }
        });
        popView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    doAnimationUpToBottom(popView, popParent, popView.getHeight(), popWindow);
                    return true;
                }
                return false;
            }
        });
        popView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = popView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        doAnimationUpToBottom(popView, popParent, popView.getHeight(), popWindow);
                    }
                }
                return true;
            }
        });
    }




    /**
     * bottom to up animation
     *
     * @param viewBack
     * @param view
     * @param height
     */
    public static void doAnimationBottomToUp(View viewBack, View view, int height) {
        AnimatorSet set = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();

            int temHeight = 0;
            if (height == 0) {
                temHeight = (int) view.getResources().getDimension(R.dimen.dp_400);
            } else {
                temHeight = height;
            }
            ObjectAnimator animatorTrans = ObjectAnimator.ofFloat(view, "translationY", temHeight, 0.0f);
            ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(viewBack, "alpha", 0.0f, 1.0f);
            set.playTogether(animatorAlpha, animatorTrans);
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            set.setDuration(300);
            set.start();
        }

    }

    /**
     * top ti bottom animation
     *
     * @param viewBack
     * @param view
     * @param height
     * @param popWindow
     */
    public static void doAnimationUpToBottom(View viewBack, View view, int height, final PopupWindow popWindow) {
        AnimatorSet set = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            set = new AnimatorSet();

            int temHeight = 0;
            if (height == 0) {
                temHeight = (int) view.getResources().getDimension(R.dimen.dp_400);
            } else {
                temHeight = height;
            }
            ObjectAnimator animatorTrans = ObjectAnimator.ofFloat(view, "translationY", 0.0f, temHeight);
            ObjectAnimator animatorAlpha = ObjectAnimator.ofFloat(viewBack, "alpha", 1.0f, 0.0f);
            set.playTogether(animatorAlpha, animatorTrans);
            set.setInterpolator(new DecelerateInterpolator());
            set.setDuration(300);
            set.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    popWindow.dismiss();
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
    }


}
