package com.tripoin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Controller
public class ForgotPasswordController {

	@RequestMapping(value="/forgotpassword", method={RequestMethod.GET}, params={"user", "uuid"})
	public String doResetPassword(@RequestParam("user") String user, @RequestParam("uuid") String uuid){
		System.out.println(user+" "+uuid);
		return "redirect:/laris";
	}
}
