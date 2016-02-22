package com.tripoin.tripoin_rest.bgp.base;

import android.app.Dialog;
import android.util.Log;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_rest.post.IGenericPost;

import retrofit.RetrofitError;

/**
 * Created on 10/16/2015 : 3:20 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class DialogGenericPost implements IGenericPost{

    private Dialog dialog;

    public DialogGenericPost(Dialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void onPostSuccess(Object o) {
        Log.i(ApplicationConstant.Log.TRIPOIN_INFO, "Closing Dialog");
        closeDialog();
    }

    @Override
    public void onPostFailure(RetrofitError retrofitError) {
        closeDialog();
    }

    private void closeDialog(){
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }
}
