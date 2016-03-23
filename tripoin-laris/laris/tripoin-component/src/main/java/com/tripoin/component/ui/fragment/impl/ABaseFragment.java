package com.tripoin.component.ui.fragment.impl;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tripoin.common.constant.ApplicationConstant;
import com.tripoin.common.constant.GeneralConstant;
import com.tripoin.component.ComponentConstant;
import com.tripoin.component.app.base.ATRIPOINApplication;
import com.tripoin.component.ui.activity.IComponentInjector;
import com.tripoin.component.ui.fragment.INavigationDrawerFragment;
import com.tripoin.dao.DAOComponent;
import com.tripoin.util.network.DaggerNetworkComponent;
import com.tripoin.util.network.NetworkComponent;
import com.tripoin.util.network.NetworkModule;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created on 4/25/2015 : 11:20 PM.
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class ABaseFragment extends Fragment implements INavigationDrawerFragment, IComponentInjector {

    protected Typeface typeface;
    protected List<TextView> textViews;
    protected List<Button> buttons;
    protected List<EditText> editTexts;
    protected View rootView = null;

    protected NetworkComponent networkComponent;
    protected DAOComponent daoComponent;

    protected ABaseFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try{
            rootView = inflater.inflate( getViewLayoutId(), container, false );
        }catch ( Exception e ){
            if ( container != null ){
                container.removeView( rootView );
            }
            try{
                rootView = inflater.inflate(getViewLayoutId(), container, false);
            }catch (InflateException ie){
                e.printStackTrace();
            }
        }

        ButterKnife.bind(this, rootView);
        networkComponent = ((ATRIPOINApplication)getActivity().getApplication()).getNetworkComponent();
        injectNetworkComponent(networkComponent);
        daoComponent = ((ATRIPOINApplication)getActivity().getApplication()).getDaoComponent();
        injectDAOComponent(daoComponent);

        initializeFragment();

        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return rootView;
    }

    @Override
    public void injectDAOComponent(DAOComponent p_DAOComponet) {

    }

    @Override
    public void injectNetworkComponent(NetworkComponent p_NetworkComponent) {

    }

    @Override
    public void onStart() {
        super.onStart();
        onLowMemory();
    }

    private void initializeFragment(){
        try{
            String fragmentTitle = getFragmentTitle();
            if (fragmentTitle == null){
               fragmentTitle = GeneralConstant.Punctuation.EMPTY;
            }
            getActivity().setTitle(fragmentTitle);
            getActivity().setTitleColor(android.R.color.white);
            initWidget();
        }catch ( Exception e ){
            e.printStackTrace();
        }

        try {
            typeface = Typeface.createFromAsset(getActivity().getAssets(), initAssetName()[0]);
            if( getTextViews() != null )
                assignContentTypeFace( getTextViews() );

            typeface = Typeface.createFromAsset(getActivity().getAssets(), initAssetName()[1]);
            if( getEditTexts() != null )
                assignEditTextTypeFace(getEditTexts());

            typeface = Typeface.createFromAsset(getActivity().getAssets(), initAssetName()[2]);
            if( getButtons() != null )
                assignButtonTypeFace( getButtons() );
        }catch (Exception e){
            Log.w(ApplicationConstant.LogTag.TRIPOIN_WARNING, "No TypeFace Assignment found");
        }

        //release unused objects
        textViews = null;
        editTexts = null;
        buttons = null;
    }

    private void assignEditTextTypeFace(List<EditText> p_EditTexts){
        for(EditText editText: p_EditTexts){
            editText.setTypeface(typeface);
        }
    }

    private void assignContentTypeFace(List<TextView> p_TextViews){
        for ( TextView tv: p_TextViews ){
            tv.setTypeface(typeface);
        }
    }

    private void assignButtonTypeFace(List<Button> p_Buttons){
        for ( Button button : p_Buttons ){
            button.setTypeface(typeface);
        }
    }
    /**
     * Initiate asset names ( font ) which will be used in that activity or Fragment
     * @return String[]
     */
    public String[] initAssetName() {
        return new String[]{
                ComponentConstant.fonts.ROBOT_LIGHT,
                ComponentConstant.fonts.ROBOT_LIGHT_ITALIC,
                ComponentConstant.fonts.ROBOT_BOLD
        };
    }

    @Override
    public List<TextView> getTextViews() {
        return null;
    }

    @Override
    public List<EditText> getEditTexts() {
        return null;
    }

    @Override
    public List<Button> getButtons() {
        return null;
    }

    @Override
    public void gotoNextFragment(int p_ContainerId, Fragment p_FragmentView){
        FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(p_ContainerId, p_FragmentView).commit();
    }

    @Override
    public void gotoPreviousFragment(int p_ContainerId, Fragment p_FragmentView) {
        FragmentManager mFragmentManager = getActivity().getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(p_ContainerId, p_FragmentView).commit();
    }

    @Override
    public void goToMainView(String p_ExtraKey, String p_ExtraContent) {
        /*Not implemented yet*/
    }

    @Override
    public void exitApplication() {
        /*Not implemented yet*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
