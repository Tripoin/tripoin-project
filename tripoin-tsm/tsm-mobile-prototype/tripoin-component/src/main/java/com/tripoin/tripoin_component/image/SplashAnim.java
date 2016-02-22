package com.tripoin.tripoin_component.image;

import android.content.Intent;
import android.view.animation.Animation;

import com.tripoin.tripoin_common.component.ITRIPOINComponent;

/**
 * Created on 10/8/2015 : 2:01 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class SplashAnim implements Animation.AnimationListener, ITRIPOINComponent<SplashAnimParameter> {

    private SplashAnimParameter splashAnimParameter;

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        getParameter().getImageView().startAnimation(getParameter().getAnimationFadeOut());
        getParameter().getActivity().finish();
        Intent i = new Intent(getParameter().getActivity(),getParameter().getLoginClass());
        getParameter().getActivity().startActivity(i);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    public void setParameter(SplashAnimParameter _param) {
        this.splashAnimParameter = _param;
    }

    @Override
    public SplashAnimParameter getParameter() {
        return splashAnimParameter;
    }
}
