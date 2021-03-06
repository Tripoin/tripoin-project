package com.tripoin.component.ui.activity.base;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tripoin.common.bus.BusProvider;
import com.tripoin.common.constant.ApplicationConstant;
import com.tripoin.component.ComponentConstant;
import com.tripoin.component.R;
import com.tripoin.component.app.base.ATRIPOINApplication;
import com.tripoin.component.ui.activity.IActivity;
import com.tripoin.component.ui.activity.IComponentInjector;
import com.tripoin.component.ui.activity.impl.NavigatorActivity;
import com.tripoin.dao.DAOComponent;
import com.tripoin.util.network.NetworkComponent;

import java.io.Serializable;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Achmad Fauzi on 5/7/2015 : 11:13 AM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class ABaseActivity extends AppCompatActivity implements IActivity, IComponentInjector {

    protected Typeface typeface;
    protected List<TextView> textViews;
    protected List<EditText> editTexts;
    protected List<Button> buttons;
    protected NavigatorActivity navigatorActivity;

    protected NetworkComponent networkComponent;
    protected DAOComponent daoComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewLayoutId());
        setupTypeFace();

        ButterKnife.bind(this);

        daoComponent = ((ATRIPOINApplication)getApplication()).getDaoComponent();
        injectDAOComponent(daoComponent);
        Log.i(ApplicationConstant.LogTag.TRIPOIN_INFO, "Successful inject ".concat(DAOComponent.class.getName()));

        networkComponent = ((ATRIPOINApplication) getApplication()).getNetworkComponent();
        injectNetworkComponent(networkComponent);
        Log.i(ApplicationConstant.LogTag.TRIPOIN_INFO, "Successful inject ".concat(NetworkComponent.class.getName()));

        navigatorActivity = new NavigatorActivity();
        navigatorActivity.setParameter(this);
        initWidget();
    }


    @Override
    public void injectDAOComponent(DAOComponent p_DAOComponet) {
        /*Not defined yet*/
    }

    @Override
    public void injectNetworkComponent(NetworkComponent p_NetworkComponent) {
        /*Not defined yet*/
    }

    @Override
    protected void onStart() {
        super.onStart();
        onLowMemory();
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        BusProvider.getInstance().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void gotoNextActivity(Class p_Clazz, String p_ExtraKey, String p_ExtraContent) {
        navigatorActivity.gotoNextActivity(p_Clazz, p_ExtraKey, p_ExtraContent);
    }

    @Override
    public void gotoNextActivity(Class p_Clazz, String p_ExtraKey, Serializable p_ExtraContent) {
        navigatorActivity.gotoNextActivity(p_Clazz, p_ExtraKey, p_ExtraContent);
    }

    @Override
    public void gotoNextActivity(Class p_Clazz, String p_ExtraKey, Parcelable p_ExtraContent) {
        navigatorActivity.gotoNextActivity(p_Clazz, p_ExtraKey, p_ExtraContent);
    }

    @Override
    public void exitApplication() {
        navigatorActivity.exitApplication();
    }


    @Override
    public void setupTypeFace() {
        try{
            typeface = Typeface.createFromAsset( getAssets(), initFontAssets()[0] );
            if(getTextViews().size()>0 || getTextViews() != null){
                assignTextViewTypeFace(getTextViews());
            }
            typeface = Typeface.createFromAsset( getAssets(), initFontAssets()[1] );
            if(getEditTexts().size()>0 || getEditTexts() != null){
                assignEditTextTypeFace(getEditTexts());
            }
            typeface = Typeface.createFromAsset( getAssets(), initFontAssets()[3] );
            if(getButtons().size()>0 || getButtons() != null){
                assignButtonTypeFace(getButtons());
            }
        }catch (Exception e){
            Log.w(ApplicationConstant.LogTag.TRIPOIN_WARNING, getResources().getString(R.string.no_type_face));
        }
        //release unused objects
        textViews = null;
        editTexts = null;
        buttons = null;
    }

    public List<TextView> getTextViews(){
        return null;
    }

    public List<EditText> getEditTexts(){
        return null;
    }

    public List<Button> getButtons(){
        return null;
    }

    @Override
    public String[] initFontAssets() {
        return new String[]{
                ComponentConstant.fonts.ROBOT_LIGHT,
                ComponentConstant.fonts.ROBOT_LIGHT_ITALIC,
                ComponentConstant.fonts.ROBOT_BOLD,
                ComponentConstant.fonts.HELVETICA
        };
    }


    private void assignTextViewTypeFace( List<TextView> textViews ){
        for ( TextView tv: textViews ){
            tv.setTypeface(typeface);
        }
    }

    private void assignButtonTypeFace( List<Button> buttons ){
        for( Button button: buttons){
            button.setTypeface(typeface);
        }
    }

    private void assignEditTextTypeFace(List<EditText> editTexts){
        for(EditText editText: editTexts){
            editText.setTypeface(typeface);
        }
    }

    @Override
    public void goToMainView(String p_ExtraKey, String p_ExtraContent) {
        /*Not defined yet*/
    }

    @Override
    public void hideStatusBar() {
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}
