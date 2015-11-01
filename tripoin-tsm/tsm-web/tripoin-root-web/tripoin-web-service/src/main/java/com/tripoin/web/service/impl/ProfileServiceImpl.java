package com.tripoin.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
