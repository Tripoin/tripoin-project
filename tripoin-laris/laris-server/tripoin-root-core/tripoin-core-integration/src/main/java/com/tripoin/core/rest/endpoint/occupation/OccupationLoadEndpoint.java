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

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.PageArgument;
import com.tripoin.core.dto.GeneralPagingTransferObject;
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
public class OccupationLoadEndpoint extends APageableEndpoint {

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/occupation/load/all</code><br>
	 * @param inMessage
	 * @return
	 */
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
			occupationTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			occupationTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
		}catch (Exception e){
			LOGGER.error("Load All Occupation System Error : "+e.getLocalizedMessage(), e);
			occupationTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			occupationTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getLocalizedMessage());
		}		
		setReturnStatusAndMessage(occupationTransferObject, responseHeaderMap);
		Message<OccupationTransferObject> message = new GenericMessage<OccupationTransferObject>(occupationTransferObject, responseHeaderMap);
		occupationTransferObject = null;
		return message;		
	}

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/occupation/load/paging</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<OccupationTransferObject> loadOccupationPaging(Message<GeneralPagingTransferObject> inMessage){	
		OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		try{
			GeneralPagingTransferObject generalPagingTransferObject = inMessage.getPayload();
			if(generalPagingTransferObject != null){
				PageArgument pageArgument = getPageTransferObject(generalPagingTransferObject, generalPagingTransferObject.getParameterData());
				List<Occupation> occupationList = iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, getFilterArguments(), getValues(), null, pageArgument);
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
				occupationTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
				occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
				occupationTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
				generalPagingTransferObject = null;
			}
		}catch (Exception e){
			LOGGER.error("Load Paging Occupation System Error : "+e.getLocalizedMessage(), e);
			occupationTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			occupationTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			occupationTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getLocalizedMessage());
		}		
		setReturnStatusAndMessage(occupationTransferObject, responseHeaderMap);
		Message<OccupationTransferObject> message = new GenericMessage<OccupationTransferObject>(occupationTransferObject, responseHeaderMap);
		occupationTransferObject = null;
		return message;		
	}

	@Override
	protected Long getTotalRowVcsTable(FilterArgument[] filterArguments, Object[] values) throws Exception {
		return ((BigInteger)iGenericManagerJpa.getObjectSQLNative("SELECT COUNT(occupation_id) FROM mst_occupation WHERE occupation_name LIKE :".concat(EnumFieldOccupation.NAME_OCCUPATION.toString()), filterArguments, values)).longValue();
	}

	@Override
	protected String getTableName() {
		return Occupation.TABLE_NAME;
	}
	
}
