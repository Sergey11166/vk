package com.naks.vk.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.naks.vk.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;
import com.vk.sdk.util.VKUtil;

public class StartActivity extends AppCompatActivity {

    /**
     * Scope is set of required permissions for your application
     *
     * @see <a href="https://vk.com/dev/permissions">vk.com api permissions documentation</a>
     */
    private static final String[] sMyScope = new String[]{
            VKScope.FRIENDS,
            VKScope.PHOTOS,
            VKScope.DOCS,
            VKScope.STATUS,
            VKScope.NOTES,
            VKScope.PAGES,
            VKScope.WALL,
            VKScope.GROUPS,
            VKScope.MESSAGES,
            VKScope.EMAIL,
            VKScope.NOTIFICATIONS,
            VKScope.OFFLINE
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        String[] fingerprint = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        Log.d("Fingerprint", fingerprint[0]);

        VKSdk.wakeUpSession(this, new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {
                switch (res) {
                    case LoggedOut:
                        showLogin();
                        break;
                    case LoggedIn:
                        startMainActivity();
                        break;
                    case Pending:
                        break;
                    case Unknown:
                        break;
                }
            }

            @Override
            public void onError(VKError error) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                // User passed Authorization
                startMainActivity();
            }

            @Override
            public void onError(VKError error) {
                // User didn't pass Authorization
                finish();
            }
        };

        if (!VKSdk.onActivityResult(requestCode, resultCode, data, callback)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showLogin() {
        VKSdk.login(this, sMyScope);
    }

    private void startMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
