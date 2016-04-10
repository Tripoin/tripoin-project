package com.tripoin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:ridla.fadilah@gmail.com">Ridla Fadilah</a>
 */
@Controller
public class HomePageController {

	@RequestMapping("/")
    public String redirectHomePageApp() {
        return "redirect:/laris/";
    }
	
}
