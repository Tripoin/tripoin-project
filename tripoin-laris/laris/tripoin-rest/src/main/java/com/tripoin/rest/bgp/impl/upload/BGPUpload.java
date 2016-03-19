package com.tripoin.rest.bgp.impl.upload;

import com.tripoin.common.constant.ApplicationConstant;
import com.tripoin.rest.bgp.IUpload;
import com.tripoin.rest.bgp.base.ABGP;
import com.tripoin.rest.callback.GenericCallBack;
import com.tripoin.rest.endpoints.EPUpload;
import com.tripoin.rest.post.IGenericPost;

import java.io.File;

import retrofit.Callback;
import retrofit.mime.TypedFile;

/**
 * Created on 10/1/2015 : 4:56 PM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public abstract class BGPUpload extends ABGP implements IUpload {

    public BGPUpload(IGenericPost p_IGenericPost) {
        super(p_IGenericPost);
    }

    @Override
    protected Object doInBackground(Object[] p_MultipleParams) {
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
