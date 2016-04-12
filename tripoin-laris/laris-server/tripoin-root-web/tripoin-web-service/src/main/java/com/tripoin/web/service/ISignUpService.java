package com.tripoin.web.service;

import com.tripoin.dto.app.CustomerData;
import com.tripoin.dto.app.FacebookProfileData;
import com.tripoin.dto.app.GeneralTransferObject;
import com.tripoin.dto.request.DTORequestSignUpFacebook;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface ISignUpService {

    public GeneralTransferObject registerWithFacebook(DTORequestSignUpFacebook<FacebookProfileData, CustomerData> dtoRequestSignUp);

}
