package com.health.life.base;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.animation.Animation;

import com.health.life.interfaces.RequestListener;
import com.health.life.utils.AbLoadDialogFragment;


public abstract class BaseFragment extends android.support.v4.app.Fragment implements RequestListener{


    /**
     * do the request for fragment
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        requestData();
    }

    /**
     * use to set fragment's title
     * @param transit
     * @param enter
     * @param nextAnim
     * @return
     */
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        if(enter){
            ((BaseActivity)getActivity()).setTitle(currentTitle());
        }
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    /**
     * 如果 当前的fragment不需要改变标题  ,返回 null 或是 "" ,默认重写返回 null  . 无需再管
     * @return
     */
    protected abstract String currentTitle();

    protected  abstract void  requestData();

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
        showLoadDialog(getActivity());
    }

    @Override
    public void hideProgressDialog() {
     removeDialog(getActivity());
    }
}
