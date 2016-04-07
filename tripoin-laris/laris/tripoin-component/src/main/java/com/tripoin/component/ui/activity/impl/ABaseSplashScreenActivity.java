package com.tripoin.component.ui.activity.impl;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.tripoin.common.constant.ApplicationConstant;
import com.tripoin.common.constant.GeneralConstant;

import com.tripoin.component.R;
import com.tripoin.component.image.SplashAnim;
import com.tripoin.component.image.SplashAnimParameter;
import com.tripoin.component.ui.activity.ISplashScreen;
import com.tripoin.component.ui.activity.base.ABaseActivity;
import com.tripoin.dao.impl.DAOUser;
import com.tripoin.model.ModelUser;
import com.tripoin.rest.dto.app.DTOParcelUserLogin;

import org.parceler.Parcels;

/**
 * <p>
 *     Base <code>Class</code> for Splash Screen
 * </p>
 * @param <LOGIN_ACTIVITY_CLASS> if preference ||  Login Session is expired
 * @param <HOME_ACTIVITY_CLASS> if preference ||  Login Session is still alive
 *
 * Created on 9/29/2015 : 9:00 AM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class ABaseSplashScreenActivity<LOGIN_ACTIVITY_CLASS, HOME_ACTIVITY_CLASS> extends ABaseActivity implements ISplashScreen {

    public abstract Class<LOGIN_ACTIVITY_CLASS> getLoginActivity();

    public abstract Class<HOME_ACTIVITY_CLASS> getHomeActivity();

    /**
     * @return delay time in ms
     */
    public abstract long getDelayTime();

    @Override
    public void initWidget() {
        Log.i(ApplicationConstant.LogTag.TRIPOIN_INFO, getResources().getString(R.string.start_ss));
        getSupportActionBar().hide();
        /*startAnimation();*/
        new DelayAsync().execute();
    }

    private void rotateAnimation(){
        Animation animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        Animation animFadeOut = AnimationUtils.loadAnimation(this, R.anim.abc_fade_out);

        getSplashIcon().setAnimation(animRotate);

        SplashAnimParameter splashAnimParameter = new SplashAnimParameter();
        splashAnimParameter.setActivity(this);
        splashAnimParameter.setAnimationFadeOut(animFadeOut);
        splashAnimParameter.setImageView(getSplashIcon());
        splashAnimParameter.setLoginClass(getLoginActivity());
        SplashAnim splashAnim = new SplashAnim();
        splashAnim.setParameter(splashAnimParameter);

        animRotate.setAnimationListener(splashAnim);
    }


    @Override
    public void startAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        getLinearBackground().clearAnimation();
        getLinearBackground().startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();

        getSplashIcon().clearAnimation();
        getSplashIcon().startAnimation(anim);

    }


    private class DelayAsync extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                Thread.sleep(getDelayTime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            DAOUser daoUser = new DAOUser(ABaseSplashScreenActivity.this);
            Intent intent;
            ModelUser modelUser;
            try{
                modelUser = (ModelUser) daoUser.getAllData().get(0);
                if(modelUser.getLoginStatus() == GeneralConstant.BinaryValue.ONE){
                    Log.i(ApplicationConstant.LogTag.TRIPOIN_INFO, getResources().getString(R.string.go_to_home));
                    DTOParcelUserLogin DTOUserLogin = new DTOParcelUserLogin();
                    DTOUserLogin.setUserName(modelUser.getUserName());
                    DTOUserLogin.setUserCode(modelUser.getUserCode());
                    intent = new Intent(ABaseSplashScreenActivity.this, getHomeActivity());
                    intent.putExtra(ApplicationConstant.TransferKeys.USER_LOGIN, Parcels.wrap(DTOUserLogin));
                }else{
                    Log.i(ApplicationConstant.LogTag.TRIPOIN_INFO, getResources().getString(R.string.go_to_login));
                    intent = new Intent(ABaseSplashScreenActivity.this, getLoginActivity());
                }
            }catch (Exception e){
                Log.e(ApplicationConstant.LogTag.TRIPOIN_ERROR, getResources().getString(R.string.user_is_not_found));
                modelUser = new ModelUser();
                daoUser.insertEntity(modelUser);
                intent = new Intent(ABaseSplashScreenActivity.this, getLoginActivity());
            }
            startActivity(intent);
            finish();
        }
    }

}
