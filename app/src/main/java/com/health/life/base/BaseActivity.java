package com.health.life.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.health.life.R;
import com.health.life.activity.swipeback.SwipeBackActivity;
import com.health.life.interfaces.RequestListener;
import com.health.life.utils.AbLoadDialogFragment;
import com.health.life.utils.SystemBarTintManager;
import com.health.life.view.TopTitleView;
import com.health.life.view.contextmenu.lib.ContextMenuDialogFragment;
import com.health.life.view.contextmenu.lib.MenuObject;
import com.health.life.view.contextmenu.lib.MenuParams;
import com.health.life.view.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.health.life.view.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


public abstract class BaseActivity extends SwipeBackActivity implements RequestListener , OnMenuItemClickListener, OnMenuItemLongClickListener {


    private FrameLayout content;
    public TopTitleView topTitleView = null;
    private FragmentManager fragmentManager ;
    private ContextMenuDialogFragment mMenuDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_root);
        getWindow().setLayout(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setTintColor(getResources().getColor(R.color.color_539728)); //  set status bar color
        fragmentManager = getSupportFragmentManager();
        initBaseView();
        initBaseData();

    }

    /**描述当前页面的title--便于友盟统计*/
    protected abstract String currActivityName();



    private void initBaseData() {

    }

    private void initBaseView() {
        content = (FrameLayout) findViewById(R.id.root_container);
        topTitleView = (TopTitleView) findViewById(R.id.topTitleView);
        topTitleView.getRootLeftRl().setOnClickListener(onLeftClickListener);
        topTitleView.getRootRightRl().setOnClickListener(onRightClickListener);
        topTitleView.setTitle(currActivityName());
        initMenuFragment();
    }

    private View.OnClickListener onLeftClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //go back
            finish();
        }
    };

    /***
     *  如果是文章 健康详情  显示菜单可供用户操作  default gone
     */
    public void showRightMenu(boolean show){
        topTitleView.getRootRightRl().setVisibility(show ? View.VISIBLE : View.GONE);
    }


    /**
     * 显示返回按钮  default 显示
     * @param show
     */
    public void showLeftBack(boolean show){
        topTitleView.getRootLeftRl().setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private View.OnClickListener onRightClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
        }
    };
    public TopTitleView getTopTitleView(){
        return topTitleView;
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

    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.title_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(true);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    private List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject("评论");
        send.setResource(R.drawable.icn_1);

        MenuObject like = new MenuObject("收藏");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.icn_2);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("点赞");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.icn_3));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("登录");
        addFav.setResource(R.drawable.icn_4);


        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        return menuObjects;
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {

    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {

    }
}
