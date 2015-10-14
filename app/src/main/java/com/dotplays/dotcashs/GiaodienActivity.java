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
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
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
    private TextView username;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien);
        setResult(0);
        setActionBar();
        setDrawerLayout();
        setTablayout();
        setViewPager();
        addEventTab();
        setLogoutButton();
    }

    public void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarGiaodien);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Thu chi");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.menu_icon);
    }

    public void setDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        linearLayout = (LinearLayout) findViewById(R.id.layoutGiaodien);
//        username = (TextView) findViewById(R.id.txt_giaodien_username);
//        String name = getIntent().getStringExtra("name");
//        username.setText(name);
    }

    public void setTablayout() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        tablayout.addTab(tablayout.newTab().setText("Kiếm xu"));
        tablayout.addTab(tablayout.newTab().setText("Đổi quà"));
        tablayout.addTab(tablayout.newTab().setText("Tạm vậy"));
        tablayout.setSelectedTabIndicatorHeight(7);
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
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void setLogoutButton() {
        findViewById(R.id.btn_giaodien_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                setResult(1);
                finish();
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
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(linearLayout)) {
                drawerLayout.closeDrawer(linearLayout);
            } else {
                drawerLayout.openDrawer(linearLayout);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
