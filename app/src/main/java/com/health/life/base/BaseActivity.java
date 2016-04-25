package com.health.life.base;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.health.life.R;
import com.health.life.activity.LoginActivity;
import com.health.life.activity.swipeback.SwipeBackActivity;
import com.health.life.interfaces.RequestListener;
import com.health.life.utils.AbLoadDialogFragment;
import com.health.life.utils.Config;
import com.health.life.utils.SharePreferenceUtil;
import com.health.life.utils.StatusBarCompat;
import com.health.life.view.AppBar;
import com.health.life.view.ErrorViewLayout;
import com.health.life.view.contextmenu.lib.ContextMenuDialogFragment;
import com.health.life.view.contextmenu.lib.MenuObject;
import com.health.life.view.contextmenu.lib.MenuParams;
import com.health.life.view.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.health.life.view.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivity extends SwipeBackActivity implements RequestListener , OnMenuItemClickListener, OnMenuItemLongClickListener {


    private ErrorViewLayout content;
    private AppBar appBar;
    private FragmentManager fragmentManager ;
    private ContextMenuDialogFragment mMenuDialogFragment;
    public SharePreferenceUtil share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_root);
        fragmentManager = getSupportFragmentManager();
        initBaseView();
        initBaseData();

    }

    /**set title*/
    protected abstract String currActivityName();


    /**
     * 控制back按钮的显示和隐藏  或是自定义左边的相关内容的隐藏
     * @param enable
     */
    public void controlBack(boolean enable){
        appBar.getLeftRoot().setVisibility(enable ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 控制menu按钮的显示和隐藏  或是自定义右边的相关内容的隐藏
     * @param enable
     */
    public void controlMenu(boolean enable){
        appBar.getRightRoot().setVisibility(enable ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * 请求数据
     * @return
     */
    protected abstract void requestData();


    private void initBaseData() {
        share = new SharePreferenceUtil(this);
    }

    private void initBaseView() {
        content = (ErrorViewLayout) findViewById(R.id.root_container);
        appBar = (AppBar)findViewById(R.id.app_bar);
        controlAppBar(appBar);
        setAppBarHeight();
        appBar.setSupportActionBar(this);
        StatusBarCompat.compat(this);
        appBar.setBackImage(R.mipmap.back);
        appBar.setImageBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        appBar.setTitle(currActivityName());
        initMenuFragment();
        appBar.setRightImage(R.mipmap.menu);
        appBar.setImageRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
            }
        });
    }


    public void setTitle(String title){
        appBar.setTitle(title);
    }

    /**
     * set app bar height
     */
    private void setAppBarHeight(){
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        ViewGroup.LayoutParams params = appBar.getLayoutParams();
        params.height  = StatusBarCompat.getStatusBarHeight(this) + actionBarHeight;
        appBar.setLayoutParams(params);
    }

    /**
     *  control appbar ,  如果需要修改appbar 请重写该方法
     * @param appbar
     */
    public void controlAppBar(AppBar appbar){

    }

    /**
     * get appbar
     * @return
     */
    public AppBar getAppBar(){
        return appBar;
    }


    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, content);
    }

    @Override
    public void setContentView(View view) {
        if (view == null) {
            return;
        }

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        content.addView(view, layoutParams);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        content.addView(view, params);
    }

    @Override
    protected void onStart() {
        requestData();
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }



    /**
     * dialog tag
     */
    private static String mDialogTag = "dialog";

    /**
     * 描述：显示加载框.
     *
     * @param context               the context
     */
    public static void showLoadDialog(Context context) {
        FragmentActivity activity = (FragmentActivity) context;
        AbLoadDialogFragment newFragment = AbLoadDialogFragment.newInstance();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        // 指定一个系统转场动画
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        newFragment.show(ft, mDialogTag);
    }

    /**
     * 描述：移除Fragment.
     *
     * @param context the context
     */
    public static void removeDialog(Context context) {
        try {
            FragmentActivity activity = (FragmentActivity) context;
            FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
            // 指定一个系统转场动画
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
            Fragment prev = activity.getFragmentManager().findFragmentByTag(mDialogTag);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.addToBackStack(null);
            ft.commit();
        } catch (Exception e) {
            //可能有Activity已经被销毁的异常
            e.printStackTrace();
        }
    }

    @Override
    public void showProgressDialog() {
        showLoadDialog(this);
    }

    @Override
    public void hideProgressDialog() {
        removeDialog(this);
    }

    /**
     * 右上角菜单初始化
     */
    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        }
        menuParams.setActionBarSize(actionBarHeight);
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(true);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }
    /**
     * 右上角菜单数据
     */
    private List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject("评论");
        send.setResource(R.drawable.icn_1);

        MenuObject like = new MenuObject("收藏");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.icn_2);
        like.setBitmap(b);


        MenuObject addFav = new MenuObject("登录");
        addFav.setResource(R.drawable.icn_4);


        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFav);
        return menuObjects;
    }

    /**
     *
     * 右上角菜单点击事件
     *
     * @param clickedView
     * @param position
     */
    @Override
    public void onMenuItemClick(View clickedView, int position) {

        switch (position){
            case 1:
                if(!isLogin()){
                    goLogin(Config.LOGIN_RESULT);
                }else{
                    commentRequest();//评论请求
                }
                break;
            case 2:
                if(!isLogin()){
                    goLogin(Config.LOGIN_RESULT);
                }else{
                    collectionRequest();//收藏请求
                }

                break;


            case 3:
                //登录
                LoginActivity.showActivity(BaseActivity.this);
                break;
        }

    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {

    }


    /**
     * 请求错误信息显示
     * @param meg
     */
    public void errorDisplay(String meg) {
        content.displayError(true);
        content.getTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestData();
            }
        });
        content.getTextView().animateText("Fuck the error : "+meg +" , you can click to load data again");
    }

    @Override
    public void errorHide() {
        content.displayError(false);
    }


    /**
     * 调到登录
     * @param requestCode
     */
    public void goLogin(int requestCode){
        LoginActivity.showActivityForResult(this, requestCode);
    }


    /**
     * 登录结果
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Config.LOGIN_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                loginResult(requestCode, true);
            } else {
                loginResult(requestCode, false);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void loginResult(int requestCode , boolean isLoginOk){};

    /**
     * is  login
     * @return
     */
    public boolean isLogin(){
        return !TextUtils.isEmpty(share.getValueString(Config.LOGIN_KEY_TOKEN , ""));
    }

    /**
     * 收藏接口
     */
    public void collectionRequest(){

    }

    /**
     * 评论接口
     */
    public void commentRequest(){

    }
}
