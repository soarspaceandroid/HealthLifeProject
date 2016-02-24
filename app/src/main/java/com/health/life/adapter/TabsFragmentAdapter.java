package com.health.life.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.TextUtils;

import com.camnter.easyslidingtabs.widget.EasySlidingTabs;
import com.health.life.model.bean.BookKindBean;
import com.health.life.fragment.FirstFragment;

import java.util.List;

/**
 * Created by ligang967 on 16/2/23.
 */
public class TabsFragmentAdapter extends FragmentPagerAdapter implements EasySlidingTabs.TabsTitleInterface {

    private List<BookKindBean> titles;

    public TabsFragmentAdapter(FragmentManager fm, List<BookKindBean> titles) {
        super(fm);
        this.titles = titles;
    }

    @Override
    public SpannableString getTabTitle(int position) {
        CharSequence title = this.getPageTitle(position);
        if (TextUtils.isEmpty(title)) return new SpannableString("");
        SpannableString spannableString = new SpannableString(title);
        return spannableString;
    }

    /**
     * This method may be called by the ViewPager to obtain a title string
     * to describe the specified page. This method may return null
     * indicating no title for this page. The default implementation returns
     * null.
     *
     * @param position The position of the title requested
     * @return A title for the requested page
     */
    @Override
    public CharSequence getPageTitle(int position) {
        if (position < titles.size()) {
            return titles.get(position).getName();
        } else {
            return "";
        }
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {

        String title = titles.get(position).getName();

        int id=titles.get(position).getId();

        Fragment fragment = FirstFragment.getInstance(title,id);

        return fragment;
    }

    @Override
    public int getTabDrawableBottom(int position) {
        return 0;
    }

    @Override
    public int getTabDrawableLeft(int position) {
        return 0;
    }

    @Override
    public int getTabDrawableRight(int position) {
        return 0;
    }

    @Override
    public int getTabDrawableTop(int position) {
        return 0;
    }


    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return this.titles.size();
    }

}
