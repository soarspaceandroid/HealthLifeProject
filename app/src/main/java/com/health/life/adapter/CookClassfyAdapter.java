package com.health.life.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.health.life.fragment.CookDisplayFragment;
import com.health.life.model.bean.output.CookClassfyOutput;

import java.util.List;

/**
 * Created by ligang967 on 16/2/23.
 */
public class CookClassfyAdapter extends FragmentPagerAdapter{

    private List<CookClassfyOutput.TngouEntity> titles;

    public CookClassfyAdapter(FragmentManager fm, List<CookClassfyOutput.TngouEntity> titles) {
        super(fm);
        this.titles = titles;
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
        CookDisplayFragment fragment = CookDisplayFragment.getInstance(title, id);
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

}
