package com.tripoin.laris.activity;

import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.Bind;
import com.tripoin.component.ui.activity.impl.ABaseSplashScreenActivity;
import com.tripoin.laris.R;

public class ActivitySplashScreen extends ABaseSplashScreenActivity {

    @Bind(R.id.imgLogo)
    ImageView imgLogo;

    @Bind(R.id.linearBackground)
    LinearLayout linearBackground;


    @Override
    public Class getLoginActivity() {
        return ActivityLogin.class;
    }

    @Override
    public Class getHomeActivity() {
        return ActivityHomeBottomBar.class;
    }

    @Override
    public long getDelayTime() {
        return 3000;
    }

    @Override
    public int getViewLayoutId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    public ImageView getSplashIcon() {
        return imgLogo;
    }

    @Override
    public LinearLayout getLinearBackground() {
        return linearBackground;
    }
}
