package com.tripoin.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tripoin.core.common.EResponseCode;
import com.tripoin.dto.app.GeneralTransferObject;
import com.tripoin.web.service.IForgotPasswordService;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Controller
public class ForgotPasswordController {

	@Autowired
	private IForgotPasswordService forgotPasswordService;
	
	@RequestMapping(value="/forgotpassword", method={RequestMethod.GET}, params={"user", "uuid"})
	public String doVerifyResetPassword(@RequestParam("user") String username, @RequestParam("uuid") String uuid){
		GeneralTransferObject generalTransferObject = forgotPasswordService.verifyForgotPassword(username, uuid);
		if(EResponseCode.RC_SUCCESS.getResponseCode().equals(generalTransferObject.getResponseCode())){
			return "redirect:/laris";
		}else if(EResponseCode.RC_URL_EXPIRED.getResponseCode().equals(generalTransferObject.getResponseCode())){
			return "redirect:/laris";
		}
		return "redirect:/laris";
	}
}
