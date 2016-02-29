package com.tripoin.web.service.impl;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dto.EmployeeData;
import com.tripoin.core.dto.EmployeeTransferObject;
import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.ProfileData;
import com.tripoin.core.dto.ProfileTransferObject;
import com.tripoin.web.common.ICommonRest;
import com.tripoin.web.common.IStateFullRest;
import com.tripoin.web.common.WebServiceConstant;
import com.tripoin.web.service.IProfileService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Service("profileService")
public class ProfileServiceImpl implements IProfileService {

	@Autowired
	private ICommonRest commonRest;
	
	@Autowired
	private IStateFullRest stateFullRest;
	
	@Override
	public ProfileData getProfile() {
		return stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_PROFILE), ProfileTransferObject.class).getProfileDatas().get(0);
	}

	@Override
	public EmployeeData getProfileEmployee() {
		return stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_PROFILE_EMPLOYEE), EmployeeTransferObject.class).getEmployeeDatas().get(0);
	}

	@Override
	public ProfileTransferObject updateProfile(ProfileData profileData) {		
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_PROFILE_UPDATE), profileData, ProfileTransferObject.class);
	}

	@Override
	public GeneralTransferObject updatePhotoProfile(File file, Map<String, Object> data) {
		Resource resourceStar = new FileSystemResource(file);
		MultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<String, Object>();
		multipartMap.add(ParameterConstant.TRIPOIN_UPLOAD_IMAGE, resourceStar);
		for(String key : data.keySet())
			multipartMap.add(key, data.get(key));
		stateFullRest.setMultipart(true);
		GeneralTransferObject generalTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_PROFILE_IMAGE), multipartMap, GeneralTransferObject.class);		
		return generalTransferObject;
	}
}
