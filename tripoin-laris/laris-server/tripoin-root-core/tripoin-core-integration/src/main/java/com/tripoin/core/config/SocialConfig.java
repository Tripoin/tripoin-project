package com.tripoin.core.config;

import org.scribe.builder.api.FacebookApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;

import com.tripoin.core.common.APIConstant;
import com.tripoin.core.dao.filter.ECommonOperator;
import com.tripoin.core.dao.filter.FilterArgument;
import com.tripoin.core.pojo.APIType;
import com.tripoin.core.service.IGenericManagerJpa;
import com.tripoin.util.api.facebook.OAuthServiceConfig;
import com.tripoin.util.api.facebook.OAuthServiceProvider;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@ImportResource("/WEB-INF/config/core-integration-config.xml")
@Configuration
public class SocialConfig {

	@Autowired
	private IGenericManagerJpa iGenericManagerJpa;
	
	@Bean(name="connectionFactoryRegistry")
    public ConnectionFactoryLocator connectionFactoryLocator() {
        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		try {
			FilterArgument[] filterArgument = new FilterArgument[]{
					new FilterArgument("code", ECommonOperator.EQUALS)	
				};
			APIType apiType = iGenericManagerJpa.loadObjectsFilterArgument(APIType.class, filterArgument, 
					new Object[]{APIConstant.FACEBOOK.getOperator()}, null, null).get(0);
			registry.addConnectionFactory(new FacebookConnectionFactory(
        		apiType.getIdentifier(),
        		apiType.getSecret(), "tripoin"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return registry;
    }
	
	@Bean(name="facebookServiceConfig")
	public OAuthServiceConfig<FacebookApi> facebookServiceConfig(){
		OAuthServiceConfig<FacebookApi> facebookServiceConfig = null;
		try {
			FilterArgument[] filterArgument = new FilterArgument[]{
					new FilterArgument("code", ECommonOperator.EQUALS)	
				};
			APIType apiType = iGenericManagerJpa.loadObjectsFilterArgument(APIType.class, filterArgument, 
					new Object[]{APIConstant.FACEBOOK.getOperator()}, null, null).get(0);
			facebookServiceConfig = new OAuthServiceConfig<FacebookApi>(
					apiType.getIdentifier(), apiType.getSecret(), apiType.getAdditional(), FacebookApi.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return facebookServiceConfig;
	}
	
	@Bean(name="facebookServiceProvider")
	public OAuthServiceProvider<FacebookApi> facebookServiceProvider(){ 
		return new OAuthServiceProvider<FacebookApi>(facebookServiceConfig());
	}
	
}
