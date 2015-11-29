package com.tripoin.tsm_mobile.activity;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tripoin.tripoin_component.ui.activity.impl.ABaseSplashScreenActivity;
import com.tripoin.tsm_mobile.R;

import butterknife.InjectView;

public class ActivitySplashScreen extends ABaseSplashScreenActivity {

    @InjectView(R.id.icTripoinLogo)
    ImageView imageTripoin;

    @InjectView(R.id.linearBackground)
    LinearLayout linearBackground;


    @Override
    public Class getLoginActivity() {
        return ActivityLogin.class;
    }

    @Override
    public Class getHomeActivity() {
        return ActivityHome.class;
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
        return imageTripoin;
    }

    @Override
    public LinearLayout getLinearBackground() {
        return linearBackground;
    }
}
