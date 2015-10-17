package com.dotplays.dotcashs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class FacebookLogin extends Fragment {
    private CallbackManager callbackManager;
    private Activity a;
    private Handler handler = new Handler();
    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;
    private LoginButton loginButton;
    private Dialog d;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_facebook_login, container, false);
        loginFacebook(v);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        a = getActivity();
        checkInternet();
    }

    // kiểm tra kết nối internet
    public void checkInternet() {
        d = new Dialog(a);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.activity_search_internet);
        d.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        d.setCancelable(false);
        d.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                connectivityManager = (ConnectivityManager) a.getSystemService(Context.CONNECTIVITY_SERVICE);
                networkInfo = connectivityManager.getActiveNetworkInfo();
                d.hide();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (AccessToken.getCurrentAccessToken() != null) {
                        startActivity();
                    }
                } else {
                    tryConnect();
                }
            }
        }, 1500);
    }

    // login Facebook
    public void loginFacebook(View v) {
        loginButton = (LoginButton) v.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity();
                Log.e("login face", "OK");
            }

            @Override
            public void onCancel() {
                Log.e("face cancle", "OK");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.e("face error", "OK");
            }
        });
    }

    // chuyển sang giao diện chính
    public void startActivity() {
        Intent intent = new Intent(a, GiaodienActivity.class);
        startActivity(intent);
    }

    // thông báo khi không có kết nối internet
    public void tryConnect() {
        d = new Dialog(a);
        d.requestWindowFeature(Window.FEATURE_NO_TITLE);
        d.setContentView(R.layout.activity_search_again);
        d.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        d.setCancelable(false);
        d.show();
        d.findViewById(R.id.btn_main_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.hide();
                checkInternet();
            }
        });
        d.findViewById(R.id.btn_main_thoat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.finish();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == 0) {
                a.finish();
            } else {
                checkInternet();
            }
        }
    }
}