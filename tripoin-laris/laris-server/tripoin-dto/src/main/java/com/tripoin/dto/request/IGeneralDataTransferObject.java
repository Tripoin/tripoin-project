package com.tripoin.dto.request;

import java.util.List;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
public interface IGeneralDataTransferObject<T> {

	public List<T> getDatas();
	
}