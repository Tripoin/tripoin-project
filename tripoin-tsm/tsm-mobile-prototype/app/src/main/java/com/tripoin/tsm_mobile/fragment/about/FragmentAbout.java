package com.tripoin.tsm_mobile.fragment.about;


import android.app.Dialog;
import android.content.Context;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_common.constant.GeneralConstant;
import com.tripoin.tripoin_component.ui.fragment.impl.ANavigationDrawerFragment;
import com.tripoin.tripoin_rest.bgp.impl.BGPUpload;
import com.tripoin.tripoin_rest.bgp.impl.upload.UploadParam;
import com.tripoin.tripoin_rest.post.IPostUpload;
import com.tripoin.tripoin_rest.util.TRIPOINHUDProgressDialog;
import com.tripoin.tsm_mobile.R;

import butterknife.OnClick;
import retrofit.RetrofitError;

/**
 * Created on 9/28/2015 : 9:17 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class FragmentAbout extends ANavigationDrawerFragment implements IPostUpload {

    @Override
    public String getFragmentTitle() {
        return ApplicationConstant.FragmentInfo.Title.ABOUT;
    }

    @Override
    public void initWidget() {

    }

    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_about;
    }

    @OnClick(R.id.btSend)
    public void sendImage(){
        final TRIPOINHUDProgressDialog tripoinhudProgressDialog = TRIPOINHUDProgressDialog.show(
                getActivity(),
                GeneralConstant.Punctuation.EMPTY,
                false,
                null
        );
        final UploadParam uploadParam = new UploadParam();
        uploadParam.setFilePath("");
        uploadParam.setDescription("File Example Description");
        new BGPUpload(this) {
            @Override
            public String getFilePath() {
                return uploadParam.getFilePath();
            }

            @Override
            public String getDescription() {
                return uploadParam.getDescription();
            }

            @Override
            public Context getContext() {
                return FragmentAbout.this.getActivity();
            }

            @Override
            public Dialog getProgressDialog() {
                return tripoinhudProgressDialog;
            }
        }.execute();
    }

    @Override
    public void onPostSuccess(Object o) {

    }

    @Override
    public void onPostFailure(RetrofitError retrofitError) {

    }
}
