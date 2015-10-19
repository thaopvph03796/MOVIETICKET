package com.dotplays.dotcashs;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import adapter.AdapterGiaodien;

public class GiaodienActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private TabLayout tablayout;
    private ViewPager viewPager;
    private AdapterGiaodien adapterGiaodich;
    private ImageView userimg;
    private TextView username, userid;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaodien);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setResult(0);
        setActionBar();
        setDrawerLayout();
        setTablayout();
        setViewPager();
        addEventTab();
    }

    // set Toolbar
    public void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbarGiaodien);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.menu_icon);
    }

    // set sự kiện cho drawable và thông tin người dùng
    public void setDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.nav_giaodien);
        Profile profile = Profile.getCurrentProfile();
        // tạo header cho Navigation View
        if (profile != null) {
            View v = LayoutInflater.from(this).inflate(R.layout.activity_header_navigation, null);
            userimg = (ImageView) v.findViewById(R.id.header_image);
            userid = (TextView) v.findViewById(R.id.header_id);
            username = (TextView) v.findViewById(R.id.header_name);
            userimg.setImageResource(R.mipmap.navigation_logo_2);
            userid.setText(profile.getId());
            username.setText(profile.getName());
            navigationView.addHeaderView(v);
        }
        // tạo sự kiện click cho Navigation event
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_dangxuat) {
                    LoginManager.getInstance().logOut();
                    drawerLayout.closeDrawer(navigationView);
                    setResult(1);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    // set Taplayout
    public void setTablayout() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        tablayout.addTab(tablayout.newTab().setText("Kiếm xu"));
        tablayout.addTab(tablayout.newTab().setText("Đổi quà"));
        tablayout.addTab(tablayout.newTab().setText("Tạm vậy"));
        tablayout.setSelectedTabIndicatorHeight(7);
    }

    // set ViewPager
    public void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewpagerGiaodien);
        adapterGiaodich = new AdapterGiaodien(getSupportFragmentManager());
        viewPager.setAdapter(adapterGiaodich);
    }

    // add sụ kiện cho ViewPager và Tablayout
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(navigationView);
        }
        return super.onOptionsItemSelected(item);
    }
}
