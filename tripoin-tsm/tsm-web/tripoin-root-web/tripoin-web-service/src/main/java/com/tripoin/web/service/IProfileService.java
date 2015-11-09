package com.tripoin.web.service;

import java.io.File;

import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.ProfileData;
import com.tripoin.core.dto.ProfileTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IProfileService {

    public ProfileData getProfile();
    
    public ProfileTransferObject updateProfile(ProfileData profileData);

	public GeneralTransferObject updatePhotoProfile(File file, Object[] data);

}
