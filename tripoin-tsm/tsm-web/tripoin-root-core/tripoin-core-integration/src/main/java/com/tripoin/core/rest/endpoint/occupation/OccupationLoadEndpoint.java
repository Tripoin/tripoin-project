package com.tripoin.core.rest.endpoint.occupation;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.rest.endpoint.base.APageableEndpoint;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("occupationLoadEndpoint")
public class OccupationLoadEndpoint extends APageableEndpoint<OccupationData, Occupation> {

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	@Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<OccupationTransferObject> loadOccupation(Message<OccupationData> inMessage){	
		OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		try{
        	OccupationData occupationDataPayload = inMessage.getPayload();
        	FilterArgument[] filterArguments = new FilterArgument[] { 
    				new FilterArgument("code", ECommonOperator.EQUALS) 
    		};
    		List<Occupation> occupationList = iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, filterArguments, new Object[] { occupationDataPayload.getCode() }, null, null);
			List<OccupationData> occupationDatas = new ArrayList<OccupationData>();
			if(occupationList != null){
				for(Occupation occupation : occupationList)
					occupationDatas.add(new OccupationData(occupation));
				occupationTransferObject.setOccupationDatas(occupationDatas);
				occupationList = null;
				occupationDatas = null;
			}
			occupationTransferObject.setResponseCode("0");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			occupationTransferObject.setResponseDesc("Load Occupation Data Success");			
		}catch (Exception e){
			LOGGER.error("Load All Occupation System Error : "+e.getLocalizedMessage(), e);
			occupationTransferObject.setResponseCode("1");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			occupationTransferObject.setResponseDesc("Load Occupation System Error : "+e.getLocalizedMessage());
		}		
		setReturnStatusAndMessage(occupationTransferObject, responseHeaderMap);
		Message<OccupationTransferObject> message = new GenericMessage<OccupationTransferObject>(occupationTransferObject, responseHeaderMap);
		occupationTransferObject = null;
		return message;		
	}

	@Secured({RoleConstant.ROLE_ANONYMOUS_SECURE})
	public Message<OccupationTransferObject> loadAllOccupations(Message<?> inMessage){	
		OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		try{
			List<Occupation> occupationList = iGenericManagerJpa.loadObjects(Occupation.class);
			List<OccupationData> occupationDatas = new ArrayList<OccupationData>();
			if(occupationList != null){
				for(Occupation occupation : occupationList)
					occupationDatas.add(new OccupationData(occupation));
				occupationTransferObject.setOccupationDatas(occupationDatas);
				occupationList = null;
				occupationDatas = null;
			}
			occupationTransferObject.setResponseCode("0");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			occupationTransferObject.setResponseDesc("Load All Occupation Data Success");
		}catch (Exception e){
			LOGGER.error("Load All Occupation System Error : "+e.getLocalizedMessage(), e);
			occupationTransferObject.setResponseCode("1");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			occupationTransferObject.setResponseDesc("Load All Occupation System Error : "+e.getLocalizedMessage());
		}		
		setReturnStatusAndMessage(occupationTransferObject, responseHeaderMap);
		Message<OccupationTransferObject> message = new GenericMessage<OccupationTransferObject>(occupationTransferObject, responseHeaderMap);
		occupationTransferObject = null;
		return message;		
	}

	@Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<OccupationTransferObject> loadOccupationPaging(Message<OccupationTransferObject> inMessage){	
		OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		try{
			List<Occupation> occupationList = iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, getFilterArguments(), getValues(), null, getPageTransferObject(inMessage.getPayload(), occupationTransferObject.getFindOccupationData()));
			List<OccupationData> occupationDatas = new ArrayList<OccupationData>();
			if(occupationList != null){
				for(int i=occupationList.size()-1; i>=0; i--)
					occupationDatas.add(new OccupationData(occupationList.get(i)));
				occupationTransferObject.setOccupationDatas(occupationDatas);
				occupationList = null;
				occupationDatas = null;
			}
			occupationTransferObject.setPositionPage(getPositionPage());
			occupationTransferObject.setRowPerPage(getRowPerPage());
			occupationTransferObject.setTotalPage(getTotalPage());
			occupationTransferObject.setResponseCode("0");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			occupationTransferObject.setResponseDesc("Load Paging Occupation Data Success");
		}catch (Exception e){
			LOGGER.error("Load Paging Occupation System Error : "+e.getLocalizedMessage(), e);
			occupationTransferObject.setResponseCode("1");
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			occupationTransferObject.setResponseDesc("Load Paging Occupation System Error : "+e.getLocalizedMessage());
		}		
		setReturnStatusAndMessage(occupationTransferObject, responseHeaderMap);
		Message<OccupationTransferObject> message = new GenericMessage<OccupationTransferObject>(occupationTransferObject, responseHeaderMap);
		occupationTransferObject = null;
		return message;		
	}

	@Override
	protected Long getTotalRowVcsTable() throws Exception {
		return ((BigInteger)iGenericManagerJpa.getObjectSQLNative("SELECT COUNT(occupation_id) FROM mst_occupation WHERE occupation_name LIKE :".concat(EnumFieldOccupation.NAME_OCCUPATION.toString()), getFilterArguments(), getValues())).longValue();
	}

	@Override
	protected String getTableName() {
		return Occupation.TABLE_NAME;
	}
	
}
