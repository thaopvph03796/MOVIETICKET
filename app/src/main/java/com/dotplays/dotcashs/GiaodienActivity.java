package com.dotplays.dotcashs;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import adapter.AdapterGiaodien;

public class GiaodienActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private AdapterGiaodien adapterGiaodich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien);
        setResult(0);
        setActionBar();
        setTablayout();
        setViewPager();
        addEventTab();
    }

    public void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarGiaodien);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thu chi");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.menus_icon);
    }

    public void setTablayout() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        tablayout.addTab(tablayout.newTab().setIcon(R.mipmap.money_white));
        tablayout.addTab(tablayout.newTab().setIcon(R.mipmap.statistical_grey));
        tablayout.addTab(tablayout.newTab().setIcon(R.mipmap.setting_grey));
    }

    public void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpagerGiaodien);
        adapterGiaodich = new AdapterGiaodien(getSupportFragmentManager());
        viewPager.setAdapter(adapterGiaodich);
    }

    public void addEventTab() {
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getSupportActionBar().setTitle("Thu chi");
                        tablayout.getTabAt(0).setIcon(R.mipmap.money_white);
                        viewPager.setCurrentItem(0);
                        break;
                    case 1:
                        getSupportActionBar().setTitle("Thống kê");
                        tablayout.getTabAt(1).setIcon(R.mipmap.statistical_white);
                        viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        getSupportActionBar().setTitle("Cài đặt");
                        tablayout.getTabAt(2).setIcon(R.mipmap.setting_white);
                        viewPager.setCurrentItem(2);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        tablayout.getTabAt(0).setIcon(R.mipmap.money_grey);
                        break;
                    case 1:
                        tablayout.getTabAt(1).setIcon(R.mipmap.statistical_grey);
                        break;
                    case 2:
                        tablayout.getTabAt(2).setIcon(R.mipmap.setting_grey);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giaodien, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
