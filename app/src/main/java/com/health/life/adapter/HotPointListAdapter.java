package com.health.life.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.health.life.fragment.HotPointListFragment;
import com.health.life.model.bean.output.HotPointClassfyOutput;

import java.util.List;

/**
 * Created by ligang967 on 16/2/23.
 */
public class HotPointListAdapter extends FragmentPagerAdapter{

    private List<HotPointClassfyOutput.TngouEntity> titles;
    private FragmentManager fm ;
    private boolean isRefreshData = false;

    public HotPointListAdapter(FragmentManager fm, List<HotPointClassfyOutput.TngouEntity> titles) {
        super(fm);
        this.titles = titles;
        this.fm =fm;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if(titles.size() == 0){
            return "";
        }
        return titles.get(position).name;
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        String title = titles.get(position).name;
        int id=titles.get(position).id;
        HotPointListFragment fragment = HotPointListFragment.getInstance(title, id);
        return fragment;
    }


    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {

        if (this.titles!=null) {
            return this.titles.size();
        }else {
            return 0;
        }
    }



    public void setData(List<HotPointClassfyOutput.TngouEntity> titles){
        this.titles = titles;
        notifyDataSetChanged();
    }
}
