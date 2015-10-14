package com.dotplays.dotcashs;

import android.content.Intent;
import android.content.pm.PackageInstaller;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class FacebookLogin extends Fragment {
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private Profile profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        checkFacebookLogged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_login_facebook, container, false);
        loginFacebook(v);
        return v;
    }

    public void checkFacebookLogged() {
        AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                AccessToken accessToken = AccessToken.getCurrentAccessToken();
                if (accessToken != null) {
                    startActivity();
                }
            }
        };
    }

    public void loginFacebook(View v) {
        loginButton = (LoginButton) v.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                startActivity();
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }

    public void startActivity() {
//        final String[] name = new String[0];
//        ProfileTracker profiletracker = new ProfileTracker() {
//            @Override
//            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
//                name[0] = currentProfile.getName();
//            }
//        };
        Intent intent = new Intent(getActivity(), GiaodienActivity.class);
//        intent.putExtra("name", name[0]);
//        Toast.makeText(getContext(), name[0], Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2) {
            if (resultCode == 0) {
                getActivity().finish();
            }
        }
    }
}
