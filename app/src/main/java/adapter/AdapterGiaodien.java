package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import fragment_page.CaidatFragment;
import fragment_page.ThongkeFragment;
import fragment_page.ThuchiFragment;

public class AdapterGiaodien extends FragmentPagerAdapter {
    private String matk;

    public AdapterGiaodien(FragmentManager fm) {
        super(fm);
        this.matk = matk;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new ThuchiFragment();
            case 1:
                return new ThongkeFragment();
            case 2:
                return new CaidatFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}