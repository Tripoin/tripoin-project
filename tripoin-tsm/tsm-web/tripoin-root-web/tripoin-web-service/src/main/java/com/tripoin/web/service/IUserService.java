package com.tripoin.web.service;

import com.tripoin.core.dto.GeneralTransferObject;
import com.tripoin.core.dto.UserData;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IUserService {

    public UserData getUser();
    
    public GeneralTransferObject updateUser(String password);

}
