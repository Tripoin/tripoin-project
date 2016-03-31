package com.tripoin.web.service;

import com.tripoin.core.dto.GeneralTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IForgotPasswordService {

    public GeneralTransferObject forgotPassword(String email);

    public GeneralTransferObject verifyForgotPassword(String username, String uuid);

}
