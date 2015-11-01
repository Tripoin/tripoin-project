package com.tripoin.web.service;

import com.tripoin.core.dto.UserData;
import com.tripoin.core.dto.UserTransferObject;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
public interface IUserService {

    public UserData getUser();
    
    public UserTransferObject updateUser(UserData userData);

}
