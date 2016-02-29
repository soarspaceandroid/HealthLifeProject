package com.health.life.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.health.life.R;


/**
 *@author:郁俊耀
 *@data:2015-8-25 上午10:25:54
 */
public class TopTitleView extends RelativeLayout {
	private Context mContext;
	private RelativeLayout rootRl = null;
	private TextView rootTitleTv = null;
	private RelativeLayout rootLeftRl = null;
	private RelativeLayout rootRightRl = null;
	private ImageView leftImgIv = null;
	private ImageView rightImgIv = null;
	private TextView rightTextTv = null;

	public TopTitleView(Context context) {
		super(context);
		initView(context);
	}
	
	public TopTitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}
	
	public TopTitleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView(context);
	}
	
	/**初始化View*/
	@SuppressLint("NewApi")
	private void initView(Context context){
		mContext = context;
		if(isInEditMode()){
			TextView textView = new TextView(context);
			textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
			textView.setText("Common Top Title View");
			textView.setTextColor(getResources().getColor(R.color.color_e7343a));
			addView(textView);
		}else{
			View layout = LayoutInflater.from(mContext).inflate(R.layout.activity_root_title, this, true);
			rootTitleTv = (TextView) layout.findViewById(R.id.root_title);
			rootRl = (RelativeLayout) layout.findViewById(R.id.root_rl);
			rootLeftRl = (RelativeLayout) layout.findViewById(R.id.root_left_rl);
			rootRightRl = (RelativeLayout) layout.findViewById(R.id.root_right_rl);
			leftImgIv = (ImageView) layout.findViewById(R.id.left_img);
			rightImgIv = (ImageView) layout.findViewById(R.id.right_img);
			rightTextTv = (TextView) layout.findViewById(R.id.right_text);
			
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
				rootRl.setFitsSystemWindows(true);
	        }
		}
	}	
	
	public RelativeLayout getRootRl(){
		return rootRl;
	}
	
	/**设置title背景*/
	public void setTitleImageResource(int resourseID){
		rootRl.setBackgroundResource(resourseID);
	}
	
	/**设置标题内容*/
	public void setTitle(String title){
		rootTitleTv.setText(title);
	}
	public void setTitle(int resourseID){
		rootTitleTv.setText(mContext.getResources().getString(resourseID));
	}
	
	/**设置左边内容*/
	public void setLeftImageResource(int resourseID){
		leftImgIv.setImageResource(resourseID);
		leftImgIv.setVisibility(View.VISIBLE);
	}
	public void setLeftImageDrawable(Drawable drawable) {
		leftImgIv.setImageDrawable(drawable);
		leftImgIv.setVisibility(View.VISIBLE);
	}
	
	/**设置右边文字*/
	public void setRightText(int resourseID){
		rightTextTv.setText(mContext.getResources().getString(resourseID));
		rightTextTv.setVisibility(View.VISIBLE);
		rightImgIv.setVisibility(View.GONE);
	}
	public void setRightText(String rightContent){
		rightTextTv.setText(rightContent);
		rightTextTv.setVisibility(View.VISIBLE);
		rightImgIv.setVisibility(View.GONE);
	}
	/**设置右边图片*/
	public void setRightImageResource(int resourseID){
		rightImgIv.setImageResource(resourseID);
		rightImgIv.setVisibility(View.VISIBLE);
		rightTextTv.setVisibility(View.GONE);
	}
	public void setRightImageDrawable(Drawable drawable){
		rightImgIv.setImageDrawable(drawable);
		rightImgIv.setVisibility(View.VISIBLE);
		rightTextTv.setVisibility(View.GONE);
	}

	public RelativeLayout getRootLeftRl() {
		return rootLeftRl;
	}

	public void setRootLeftRl(RelativeLayout rootLeftRl) {
		this.rootLeftRl = rootLeftRl;
	}

	public RelativeLayout getRootRightRl() {
		return rootRightRl;
	}

	public TextView getRightTv(){
		return rightTextTv;
	}

	public void setRootRightRl(RelativeLayout rootRightRl) {
		this.rootRightRl = rootRightRl;
	}

	public ImageView getLeftImgIv() {
		return leftImgIv;
	}

	public void setLeftImgIv(ImageView leftImgIv) {
		this.leftImgIv = leftImgIv;
	}
	
}
