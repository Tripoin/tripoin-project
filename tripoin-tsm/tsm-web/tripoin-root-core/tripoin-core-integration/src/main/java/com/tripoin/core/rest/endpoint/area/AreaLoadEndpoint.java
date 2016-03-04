package com.tripoin.core.rest.endpoint.area;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.AreaTransferObject;
import com.tripoin.core.dto.AreaTransferObject.EnumFieldArea;
import com.tripoin.core.pojo.Area;
import com.tripoin.core.rest.endpoint.base.APageableEndpoint;
import com.tripoin.core.service.IGenericManagerJpa;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("areaLoadEndpoint")
public class AreaLoadEndpoint extends APageableEndpoint<AreaData> {

    private static Logger LOGGER = LoggerFactory.getLogger(AreaLoadEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/area/load/all</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_ANONYMOUS_SECURE})
	public Message<AreaTransferObject> loadAllAreas(Message<?> inMessage){	
		AreaTransferObject areaTransferObject = new AreaTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		try{
			List<Area> areaList = iGenericManagerJpa.loadObjects(Area.class);
			List<AreaData> areaDatas = new ArrayList<AreaData>();
			if(areaList != null){
				for(Area area : areaList)
					areaDatas.add(new AreaData(area));
				areaTransferObject.setAreaDatas(areaDatas);
				areaList = null;
				areaDatas = null;
			}
			areaTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			areaTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			areaTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());			
		}catch (Exception e){
			LOGGER.error("Load All Area System Error : "+e.getLocalizedMessage(), e);
			areaTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			areaTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			areaTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getLocalizedMessage());
		}		
		setReturnStatusAndMessage(areaTransferObject, responseHeaderMap);
		Message<AreaTransferObject> message = new GenericMessage<AreaTransferObject>(areaTransferObject, responseHeaderMap);
		areaTransferObject = null;
		return message;		
	}

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/area/load/paging</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<AreaTransferObject> loadAreaPaging(Message<AreaTransferObject> inMessage){	
		AreaTransferObject areaTransferObject = new AreaTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		try{
			List<Area> areaList = iGenericManagerJpa.loadObjectsFilterArgument(Area.class, getFilterArguments(), getValues(), null, getPageTransferObject(inMessage.getPayload(), inMessage.getPayload().getFindAreaData()));
			List<AreaData> areaDatas = new ArrayList<AreaData>();
			if(areaList != null){
				for(int i=areaList.size()-1; i>=0; i--)
					areaDatas.add(new AreaData(areaList.get(i)));					
				areaTransferObject.setAreaDatas(areaDatas);
				areaList = null;
				areaDatas = null;
			}
			areaTransferObject.setPositionPage(getPositionPage());
			areaTransferObject.setRowPerPage(getRowPerPage());
			areaTransferObject.setTotalPage(getTotalPage());
			areaTransferObject.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			areaTransferObject.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			areaTransferObject.setResponseDesc(EResponseCode.RC_SUCCESS.toString());			
		}catch (Exception e){
			LOGGER.error("Load Paging Area System Error : "+e.getLocalizedMessage(), e);
			areaTransferObject.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			areaTransferObject.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			areaTransferObject.setResponseDesc(EResponseCode.RC_FAILURE.toString()+e.getLocalizedMessage());
		}		
		setReturnStatusAndMessage(areaTransferObject, responseHeaderMap);
		Message<AreaTransferObject> message = new GenericMessage<AreaTransferObject>(areaTransferObject, responseHeaderMap);
		areaTransferObject = null;
		return message;		
	}

	@Override
	protected Long getTotalRowVcsTable() throws Exception {
		return ((BigInteger)iGenericManagerJpa.getObjectSQLNative("SELECT COUNT(area_id) FROM mst_area WHERE area_name LIKE :".concat(EnumFieldArea.NAME_AREA.toString()), getFilterArguments(), getValues())).longValue();
	}

	@Override
	protected String getTableName() {
		return Area.TABLE_NAME;
	}
	
}
