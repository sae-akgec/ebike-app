package in.saeakgec.ebike.fragment.main.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import in.saeakgec.ebike.fragment.main.HistoryFragment;
import in.saeakgec.ebike.fragment.main.MainFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new MainFragment();
        } else if (position == 1) {
            fragment = new HistoryFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0) {
            title = "Bikes";
        } else if (position == 1) {
            title = "History";
        }

        return title;
    }
}