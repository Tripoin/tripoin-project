package com.tripoin.tsm_mobile.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.tripoin.tripoin_common.constant.GeneralConstant;
import com.tripoin.tripoin_component.ui.activity.impl.NavigatorActivity;
import com.tripoin.tsm_mobile.R;

/**
 * Created on 10/13/2015 : 11:06 AM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class ActivityIntroTSM extends AppIntro {

    @Override
    public void init(Bundle bundle) {
        addSlide(AppIntroFragment.newInstance("Test Intro", "Testing intro", R.drawable.ic_tsm_logo, R.color.base_color));
        addSlide(AppIntroFragment.newInstance("Test Intro2", "Testing intro2", R.drawable.ic_tripoin_logo, R.color.base_color));

        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        setVibrate(true);
        setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed() {
        goToLoginActivity();
        Toast.makeText(this, getResources().getString(R.string.skip), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDonePressed() {
        goToLoginActivity();
    }

    private void goToLoginActivity(){
        NavigatorActivity navigatorActivity = new NavigatorActivity();
        navigatorActivity.setParameter(this);
        navigatorActivity.gotoNextActivity(
                ActivityLogin.class,
                GeneralConstant.Punctuation.EMPTY,
                GeneralConstant.Punctuation.EMPTY
        );
    }
}
