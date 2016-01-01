package com.tripoin.core.rest.endpoint.occupation;

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

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.PageArgument;
import com.tripoin.core.dto.OccupationData;
import com.tripoin.core.dto.OccupationTransferObject;
import com.tripoin.core.dto.OccupationTransferObject.EnumFieldOccupation;
import com.tripoin.core.pojo.Occupation;
import com.tripoin.core.pojo.VersionControlSystemTable;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.IVersionControlSystemTableService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("occupationLoadEndpoint")
public class OccupationLoadEndpoint extends XReturnStatus {

    private static Logger LOGGER = LoggerFactory.getLogger(OccupationLoadEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private IVersionControlSystemTableService iVersionControlSystemTableService;
	
	private VersionControlSystemTable versionControlSystemTable;
	private Integer positionPage;
	private Integer rowPerPage;
	private Integer totalPage;
	private Integer minRow;
	private Integer maxRow;

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
		return message;		
	}

	@Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
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
		return message;		
	}

	@Secured({RoleConstant.ROLE_SALESMANAGER, RoleConstant.ROLE_ADMIN})
	public Message<OccupationTransferObject> loadOccupationPaging(Message<OccupationTransferObject> inMessage){	
		OccupationTransferObject occupationTransferObject = new OccupationTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();		
		
		try{			
			Object[] values = null;
			FilterArgument[] filterArguments = null;
			if(inMessage.getPayload() != null){
				occupationTransferObject = inMessage.getPayload();				
				try {					
					if(occupationTransferObject.getFindOccupationData() != null){
						filterArguments = new FilterArgument[]{
							new FilterArgument(EnumFieldOccupation.NAME_OCCUPATION.toString(), ECommonOperator.LIKE_BOTH_SIDE)
						};
						values = new Object[]{occupationTransferObject.getFindOccupationData().get(EnumFieldOccupation.NAME_OCCUPATION.toString())};
						versionControlSystemTable = new VersionControlSystemTable();
						versionControlSystemTable.setTotalRow(((BigInteger)iGenericManagerJpa.getObjectSQLNative("SELECT COUNT(occupation_id) FROM mst_occupation WHERE occupation_name LIKE :".concat(EnumFieldOccupation.NAME_OCCUPATION.toString()), filterArguments, values)).longValue());						
					}else versionControlSystemTable = iVersionControlSystemTableService.loadValue(Occupation.TABLE_NAME);						
					positionPage = occupationTransferObject.getPositionPage();
					rowPerPage = occupationTransferObject.getRowPerPage();		
					if(rowPerPage == null || rowPerPage == 0) rowPerPage = ParameterConstant.ROW_PER_PAGE;
					totalPage = new Double(versionControlSystemTable.getTotalRow()/rowPerPage).intValue();
				} catch (Exception e) {
					LOGGER.error("Load Paging Occupation System Error : "+e.getLocalizedMessage(), e);
					rowPerPage = ParameterConstant.ROW_PER_PAGE;
					versionControlSystemTable = new VersionControlSystemTable();
					versionControlSystemTable.setTotalRow(new Long(rowPerPage));
					totalPage = 0;
				}
				if(positionPage == null || positionPage <= 0) positionPage = 1;
				if(versionControlSystemTable.getTotalRow()%rowPerPage>0)totalPage++;	
				if(positionPage > totalPage) positionPage = totalPage;				
		        minRow = versionControlSystemTable.getTotalRow().intValue() - (positionPage * rowPerPage);
		        maxRow = minRow + rowPerPage;
		        occupationTransferObject.setPositionPage(positionPage);
		        occupationTransferObject.setRowPerPage(rowPerPage);
		        occupationTransferObject.setTotalPage(totalPage);
			}
			List<Occupation> occupationList = iGenericManagerJpa.loadObjectsFilterArgument(Occupation.class, filterArguments, values, null, new PageArgument(minRow, maxRow));
			List<OccupationData> occupationDatas = new ArrayList<OccupationData>();
			if(occupationList != null){
				for(int i=occupationList.size()-1; i>=0; i--)
					occupationDatas.add(new OccupationData(occupationList.get(i)));					
				occupationTransferObject.setOccupationDatas(occupationDatas);
			}
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
		versionControlSystemTable = null;
		return message;		
	}
	
}
