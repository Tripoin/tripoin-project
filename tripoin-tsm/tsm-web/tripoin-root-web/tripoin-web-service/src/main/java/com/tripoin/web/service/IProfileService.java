package com.tripoin.web.service;

import java.io.File;
import java.util.Map;

import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.ProfileData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IProfileService {

    public ProfileData getProfile();

    public EmployeeData getProfileEmployee();
    
    public GeneralTransferObject updateProfile(GeneralTransferObject dataTransferObject);

	public GeneralTransferObject updatePhotoProfile(File file, Map<String, Object> data);

}
