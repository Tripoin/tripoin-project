package com.tripoin.web.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.tripoin.core.common.ParameterConstant;
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
		ProfileTransferObject profileTransferObject = stateFullRest.get(commonRest.getUrl(WebServiceConstant.HTTP_PROFILE), ProfileTransferObject.class);
		return profileTransferObject.getProfileDatas().get(0);
	}

	@Override
	public ProfileTransferObject updateProfile(ProfileData profileData) {		
		return stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_PROFILE_UPDATE), profileData, ProfileTransferObject.class);
	}

	@Override
	public GeneralTransferObject updatePhotoProfile(File file, Object[] data) {
		Resource resourceStar = new FileSystemResource(file);
		MultiValueMap<String, Object> multipartMap = new LinkedMultiValueMap<String, Object>();
		multipartMap.add(ParameterConstant.TRIPOIN_UPLOAD_IMAGE, resourceStar);
		stateFullRest.setMultipart(true);
		GeneralTransferObject generalTransferObject = stateFullRest.post(commonRest.getUrl(WebServiceConstant.HTTP_PROFILE_IMAGE), multipartMap, GeneralTransferObject.class);		
		return generalTransferObject;
	}

}
