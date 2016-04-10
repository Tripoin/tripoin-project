package com.tripoin.web.service;

import com.tripoin.dto.response.DTOResponseCallbackFacebook;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface ICallbackAPIService {

    public DTOResponseCallbackFacebook callbackTokenFacebook(String code, String state);

}
