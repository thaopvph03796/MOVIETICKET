package com.dotplays.dotcashs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import adapter.AdapterGiaodien;

public class GiaodienActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private LinearLayout linearLayout;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private AdapterGiaodien adapterGiaodich;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien);
        setResult(0);
        setActionBar();
        setTablayout();
        setViewPager();
        addEventTab();
        setLoginButton();
    }

    public void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarGiaodien);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thu chi");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.menu_icon);
    }

    public void setTablayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        linearLayout = (LinearLayout) findViewById(R.id.layoutGiaodien);
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

    public void setLoginButton() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button_giaodien);
        loginButton.setReadPermissions("user_friends");
        // Other app specific specialization
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                setResult(1);
                finish();
            }

            @Override
            public void onCancel() {
                setResult(1);
                finish();
            }

            @Override
            public void onError(FacebookException exception) {
                setResult(1);
                finish();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_giaodien, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(linearLayout)) {
                drawerLayout.closeDrawer(linearLayout);
            } else {
                drawerLayout.openDrawer(linearLayout);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
