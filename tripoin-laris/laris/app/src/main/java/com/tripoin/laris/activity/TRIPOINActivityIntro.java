package com.tripoin.laris.activity;

import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.tripoin.common.constant.GeneralConstant;
import com.tripoin.component.ui.activity.impl.NavigatorActivity;
import com.tripoin.laris.R;
import com.tripoin.laris.fragment.intro.FragmentIntro1;
import com.tripoin.laris.fragment.intro.FragmentIntro2;
import com.tripoin.laris.fragment.intro.FragmentIntro3;

/**
 * Created on 10/13/2015 : 11:06 AM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class TRIPOINActivityIntro extends AppIntro2 {

    @Override
    public void init(Bundle bundle) {

        addSlide(new FragmentIntro1());
        addSlide(new FragmentIntro2());
        addSlide(new FragmentIntro3());

        showDoneButton(true);

        setFadeAnimation();

        setVibrate(true);
        setVibrateIntensity(30);
    }

    private void generateInstantIntro(){
        String titleIntro1 = getResources().getString(R.string.title_intro_1);
        String titleIntro2 = getResources().getString(R.string.title_intro_2);
        String titleIntro3 = getResources().getString(R.string.title_intro_3);
        String contentIntro1 = getResources().getString(R.string.content_intro_1);
        String contentIntro2 = getResources().getString(R.string.content_intro_2);
        String contentIntro3 = getResources().getString(R.string.content_intro_3);

        addSlide(AppIntroFragment.newInstance(titleIntro1, contentIntro1, R.drawable.ic_logo, R.color.base_color));
        addSlide(AppIntroFragment.newInstance(titleIntro2, contentIntro2, R.drawable.ic_logo, R.color.base_color));
        addSlide(AppIntroFragment.newInstance(titleIntro3, contentIntro3, R.drawable.ic_logo, R.color.base_color));
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
