package com.tripoin.core.rest.endpoint.base;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dao.filter.PageArgument;
import com.tripoin.core.pojo.VersionControlSystemTable;
import com.tripoin.core.rest.endpoint.XReturnStatus;
import com.tripoin.core.service.util.IVersionControlSystemTableService;
import com.tripoin.dto.app.GeneralPagingTransferObject;

/**
* @author <a href="ridla.fadilah@gmail.com">Ridla Fadilah</a>
*/
public abstract class APageableEndpoint extends XReturnStatus {
	
    protected static Logger LOGGER = LoggerFactory.getLogger(APageableEndpoint.class);

	@Autowired
	private IVersionControlSystemTableService iVersionControlSystemTableService;
	
	private VersionControlSystemTable versionControlSystemTable;
	private Integer positionPage;
	private Integer rowPerPage;
	private Integer totalPage;
	private Integer minRow;
	private Integer maxRow;
	private Object[] values;
	private FilterArgument[] filterArguments;

	protected PageArgument getPageTransferObject(GeneralPagingTransferObject payloadData, Map<String, Object> findData) {
		values = null;
		filterArguments = null;
		if(payloadData != null){			
			try {					
				if(findData != null){
					filterArguments = new FilterArgument[findData.size()];
					int i = 0;
					values = new Object[findData.size()];
					for(String key : findData.keySet()){
						values[i] = findData.get(key);
						filterArguments[i] = new FilterArgument(key, ECommonOperator.LIKE_BOTH_SIDE);
						i++;
					}
					versionControlSystemTable = new VersionControlSystemTable();
					versionControlSystemTable.setTotalRow(getTotalRowVcsTable(filterArguments, values));						
				}else versionControlSystemTable = iVersionControlSystemTableService.loadValue(getTableName());						
				positionPage = payloadData.getPositionPage();
				rowPerPage = payloadData.getRowPerPage();		
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
	        if(minRow < 0){
	        	maxRow = rowPerPage + minRow;
	        	minRow = 0;
	        }else
	        	maxRow = rowPerPage;
		}
		return new PageArgument(minRow, maxRow);
	}
	
	protected abstract Long getTotalRowVcsTable(FilterArgument[] filterArguments, Object[] values) throws Exception;
	
	protected abstract String getTableName();

	public Integer getPositionPage() {
		return positionPage;
	}

	public void setPositionPage(Integer positionPage) {
		this.positionPage = positionPage;
	}

	public Integer getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(Integer rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getMaxRow() {
		return maxRow;
	}

	public void setMaxRow(Integer maxRow) {
		this.maxRow = maxRow;
	}

	public Integer getMinRow() {
		return minRow;
	}

	public void setMinRow(Integer minRow) {
		this.minRow = minRow;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public FilterArgument[] getFilterArguments() {
		return filterArguments;
	}

	public void setFilterArguments(FilterArgument[] filterArguments) {
		this.filterArguments = filterArguments;
	}

}
