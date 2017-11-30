package ngliaxl.myguidepageindicator;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

class HomeAdapter extends FragmentPagerAdapter {


    private List<Fragment> viewList;


    public HomeAdapter(FragmentManager fm) {
        super(fm);
        this.viewList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return viewList.get(position);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    void setData(@Nullable List<Fragment> list) {
        this.viewList.clear();
        if (list != null && !list.isEmpty()) {
            this.viewList.addAll(list);
        }

        notifyDataSetChanged();
    }

    @NonNull
    List<Fragment> getData() {
        if (viewList == null) {
            viewList = new ArrayList<>();
        }

        return viewList;
    }
}