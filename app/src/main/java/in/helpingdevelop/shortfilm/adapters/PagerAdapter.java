package in.helpingdevelop.shortfilm.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import in.helpingdevelop.shortfilm.fragments.NowPlayingFragment;
import in.helpingdevelop.shortfilm.fragments.PreviousFilmsFragment;
import in.helpingdevelop.shortfilm.fragments.RunningFilmsFragment;
import in.helpingdevelop.shortfilm.fragments.UpcomingFilmsFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int mNumTabs;
    public PagerAdapter(FragmentManager fm, int mNumTabs) {
        super(fm);
        this.mNumTabs = mNumTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return  new PreviousFilmsFragment();
            case 1:
                return new RunningFilmsFragment();
            case 2:
                return new UpcomingFilmsFragment();
//                return new NowPlayingFragment();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumTabs;
    }
}
