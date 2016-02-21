package com.tripoin.core.rest.endpoint.area;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.AreaData;
import com.tripoin.core.dto.AreaTransferObject;
import com.tripoin.core.pojo.Area;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.IVersionControlSystemTableService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("areaDeleteEndpoint")
public class AreaDeleteEndpoint extends XReturnStatus {

	private static Logger LOGGER = LoggerFactory
			.getLogger(AreaDeleteEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;

	@Autowired
	private IVersionControlSystemTableService versionControlSystemTableService;

	@Autowired
	@Qualifier(value = "transactionManager")
	private PlatformTransactionManager transactionManager;

	@Autowired
	@Qualifier(value = "web-async-task-executor")
	private ThreadPoolTaskExecutor taskExecutor;

	@Secured({ RoleConstant.ROLE_NATIONALSALESMANAGER, RoleConstant.ROLE_ADMIN })
	public Message<AreaTransferObject> deleteArea(
			Message<AreaTransferObject> inMessage) {
		AreaTransferObject areaTransferObject = new AreaTransferObject();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();

		try {
			List<AreaData> areaDataPayloadList = inMessage.getPayload()
					.getAreaDatas();
			FilterArgument[] filterArguments = new FilterArgument[] { new FilterArgument(
					"code", ECommonOperator.EQUALS) };
			List<Area> areaList;
			List<AreaData> areaDataAlreadyExsistList = new ArrayList<AreaData>();
			for (AreaData areaData : areaDataPayloadList) {
				if (areaData.getCode() != null && areaData.getId() == null) {
					areaList = iGenericManagerJpa.loadObjectsFilterArgument(
							Area.class, filterArguments,
							new Object[] { areaData.getCode() }, null, null);
					areaData.setId(areaList.get(0).getId());
				}

				iGenericManagerJpa.deleteObject(new Area(areaData));

			}
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					final TransactionTemplate transactionTemplate = new TransactionTemplate(
							transactionManager);
					transactionTemplate
							.execute(new TransactionCallback<Object>() {
								@Override
								public Object doInTransaction(
										TransactionStatus arg0) {
									try {
										versionControlSystemTableService
												.insertValueAndSync(
														Area.TABLE_NAME,
														new Long(1),
														"Table of "
																.concat(Area.TABLE_NAME));
									} catch (Exception e) {
										LOGGER.error(
												"Delete Area System Error : "
														+ e.getLocalizedMessage(),
												e);
									}
									return null;
								}
							});
				}
			});
			if (areaDataAlreadyExsistList.size() > 0) {
				areaTransferObject.setAreaDatas(areaDataAlreadyExsistList);
				areaTransferObject.setResponseCode("2");
				areaTransferObject
						.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
				areaTransferObject
						.setResponseDesc("Delete Area Data Failure, Some Area Data already being used");
			} else {
				areaTransferObject.setResponseCode("0");
				areaTransferObject
						.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
				areaTransferObject.setResponseDesc("Delete Area Data Success");
			}
		} catch (Exception e) {
			LOGGER.error(
					"Delete Area System Error : " + e.getLocalizedMessage(), e);
			areaTransferObject.setResponseCode("1");
			areaTransferObject
					.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			areaTransferObject.setResponseDesc("Delete Area System Error : "
					+ e.getLocalizedMessage());
		}

		setReturnStatusAndMessage(areaTransferObject, responseHeaderMap);
		Message<AreaTransferObject> message = new GenericMessage<AreaTransferObject>(
				areaTransferObject, responseHeaderMap);
		return message;
	}

}
