package com.tripoin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.dto.response.DTOResponseCallbackFacebook;
import com.tripoin.web.common.EWebSessionConstant;
import com.tripoin.web.service.ICallbackAPIService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@RequestMapping("/api/secret/*")
@Controller
public class CallbackApiController {

	@Autowired
	private ICallbackAPIService callbackAPIService;
	
	@RequestMapping(value="/facebook**", method={RequestMethod.GET},
			params={"code", "state"})
	public String doCallbackTokenFacebook(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletRequest request){
		DTOResponseCallbackFacebook dtoResponseCallbackFacebook = callbackAPIService.callbackTokenFacebook(code, state);
		if(EResponseCode.RC_SUCCESS.getResponseCode().equals(dtoResponseCallbackFacebook.getResponseCode())){
			request.getSession().setAttribute(EWebSessionConstant.SESSION_API_FACEBOOK_DATA.toString(), dtoResponseCallbackFacebook);
			request.setAttribute(EWebSessionConstant.SESSION_API_FACEBOOK_DATA.toString(), dtoResponseCallbackFacebook);
			return "redirect:/laris";
		}else{
			return "redirect:/laris#error";
		}
	}
	
}
