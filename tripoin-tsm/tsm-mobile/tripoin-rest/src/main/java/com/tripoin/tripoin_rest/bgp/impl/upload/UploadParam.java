package com.tripoin.tripoin_rest.bgp.impl.upload;

import com.tripoin.tripoin_rest.bgp.IUpload;

/**
 * Created on 10/15/2015 : 9:33 AM.
 *
 * @author <a href="fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
public class UploadParam implements IUpload {

    private String filePath;
    private String description;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getFilePath() {
        return this.filePath;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
