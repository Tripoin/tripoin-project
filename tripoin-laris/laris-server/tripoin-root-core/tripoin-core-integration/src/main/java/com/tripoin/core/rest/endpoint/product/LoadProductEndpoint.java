package com.tripoin.core.rest.endpoint.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.core.common.ParameterConstant;
import com.tripoin.core.common.RoleConstant;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.dto.exception.WSEndpointFault;
import com.tripoin.core.pojo.LinkedAccount;
import com.tripoin.core.pojo.ProductDetail;
import com.tripoin.core.rest.endpoint.api.facebook.PagesPostingFacebookApi;
import com.tripoin.core.rest.endpoint.base.APageableEndpoint;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.core.service.util.ISystemParameterService;
import com.tripoin.dto.response.DTOResponseListProduct;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Component("loadProductEndpoint")
public class LoadProductEndpoint extends APageableEndpoint {

    private static Logger LOGGER = LoggerFactory.getLogger(LoadProductEndpoint.class);

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Autowired
	private PagesPostingFacebookApi pagesPostingFacebookApi;
	
	@Autowired
	private ISystemParameterService systemParameterService;
	
	@Autowired
    private ServletContext context;

	private String currentUserName;
	
	private WSEndpointFault wsEndpointFault = new WSEndpointFault();

	/**
	 * <b>Sample Code:</b><br>
	 * <code>/wscontext/load/product</code><br>
	 * @param inMessage
	 * @return
	 */
	@Secured({RoleConstant.ROLE_BUYER, RoleConstant.ROLE_SELLER})
	public Message<DTOResponseListProduct> getProduct(Message<?> inMessage){
		DTOResponseListProduct dtoResponseListProduct = new DTOResponseListProduct();
		Map<String, Object> responseHeaderMap = new HashMap<String, Object>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    currentUserName = authentication.getName();
		}
		authentication = null;
		try {
			List<LinkedAccount> linkedAccounts = iGenericManagerJpa.loadObjectsJQLStatement(
					"SELECT la FROM LinkedAccount la WHERE la.profile.user.username = ?", 
					new Object[]{currentUserName}, null);
			if(!linkedAccounts.isEmpty()){
				String paramIdPages = pagesPostingFacebookApi.getPostingPageString(linkedAccounts.get(0));
				LOGGER.debug("Param : "+paramIdPages);
				List<ProductDetail> productDetails = iGenericManagerJpa.loadObjectsJQLStatement(
						"".concat(paramIdPages), null, null);
			}
			dtoResponseListProduct.setResponseCode(EResponseCode.RC_SUCCESS.getResponseCode());
			dtoResponseListProduct.setResponseMsg(ParameterConstant.RESPONSE_SUCCESS);
			dtoResponseListProduct.setResponseDesc(EResponseCode.RC_SUCCESS.toString());
        }  catch (Exception e) {
			LOGGER.error("Load List Product System Error : "+e.getMessage(), e);
			dtoResponseListProduct.setResponseCode(EResponseCode.RC_FAILURE.getResponseCode());
			dtoResponseListProduct.setResponseMsg(ParameterConstant.RESPONSE_FAILURE);
			dtoResponseListProduct.setResponseDesc(EResponseCode.RC_FAILURE.toString() + e.getMessage());
		}
		setReturnStatusAndMessage(dtoResponseListProduct, responseHeaderMap);
		Message<DTOResponseListProduct> message = new GenericMessage<DTOResponseListProduct>(dtoResponseListProduct, responseHeaderMap);
		dtoResponseListProduct = null;
		return message;	
	}

	@Override
	protected Long getTotalRowVcsTable(FilterArgument[] filterArguments, Object[] values) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getTableName() {
		return ProductDetail.TABLE_NAME;
	}
	
}
