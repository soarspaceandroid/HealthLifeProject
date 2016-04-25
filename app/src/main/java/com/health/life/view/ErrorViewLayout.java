package com.health.life.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.health.life.R;
import com.hrules.trendtextview.TrendTextView;

/**
 * Created by gaofei on 2016/4/22.
 */
public class ErrorViewLayout extends RelativeLayout {
    private Context mContext;
    private TrendTextView error;
    public ErrorViewLayout(Context context) {
        super(context);
    }

    public ErrorViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();;
    }

    public ErrorViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();;
    }

    private void initView(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.error_layout , this);
        error = (TrendTextView)view.findViewById(R.id.error_text);
    }

    public  TrendTextView getTextView(){
        return error;
    }

    public void displayError(boolean display){
        if(display){
            this.getChildAt(1).setVisibility(GONE);
        }else{
            this.getChildAt(1).setVisibility(VISIBLE);
        }
        error.setVisibility(display ? View.VISIBLE : View.GONE);
    }
}
