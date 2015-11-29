package com.tripoin.tripoin_rest.bgp.impl;

import com.tripoin.tripoin_common.constant.ApplicationConstant;
import com.tripoin.tripoin_rest.bgp.IUpload;
import com.tripoin.tripoin_rest.bgp.base.ABGP;
import com.tripoin.tripoin_rest.callback.GenericCallBack;
import com.tripoin.tripoin_rest.endpoints.EPUpload;
import com.tripoin.tripoin_rest.post.IGenericPost;

import java.io.File;

import retrofit.Callback;
import retrofit.mime.TypedFile;

/**
 * Created on 10/1/2015 : 4:56 PM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class BGPUpload extends ABGP implements IUpload {

    public BGPUpload(IGenericPost iGenericPost) {
        super(iGenericPost);
    }

    @Override
    protected Object doInBackground(Object[] params) {
        TypedFile typedFile = new TypedFile(
                ApplicationConstant.Rest.MULTIPART,
                new File(getFilePath())
        );

        EPUpload epUpload = (EPUpload) restGenerator.createService(getEPClass());
        epUpload.upload(typedFile, getDescription(), getCallBack());
        return null;
    }

    @Override
    public Callback getCallBack() {
        return new GenericCallBack(iGenericPost);
    }

    @Override
    public Class getEPClass() {
        return EPUpload.class;
    }

}
