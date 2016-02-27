package com.tripoin.xwp.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 11/9/15 6:09 PM.
 *
 * @author <a href="mailto:achmad.fauzi@sigma.co.id">Achmad Fauzi</a>
 */
@RestController
public class ControllerIndex {

    @RequestMapping(value = "/")
    public String index(){
        return "<br />" +
                "<h1>Hello, XWP Rest Application</h1>" +
                "<h3>Version 1.0.0</h3>";
    }
}
