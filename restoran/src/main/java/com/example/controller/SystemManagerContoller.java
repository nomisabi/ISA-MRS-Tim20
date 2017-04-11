package com.example.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Manager;
import com.example.domain.Restaurant;
import com.example.domain.System_manager;
import com.example.service.SystemManagerService;

@Controller
@RequestMapping("/sysman")
public class SystemManagerContoller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SystemManagerService smService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView reg() {
	     return new ModelAndView("signUpMan", "sysman", new System_manager());
	}
	
	@PostMapping(value = "/signUpMan")
	public ModelAndView signUp(@Valid System_manager sm, BindingResult result) throws Exception {
		logger.info("> signUp: "+sm.getEmail()+", "+ sm.getPassword());
		if (result.hasErrors()) {
			return new ModelAndView("signUpMan", "formErrors", result.getAllErrors());
		}
		smService.signUP(sm);
		logger.info("< signUp");
		
		return new ModelAndView("signUpMan", "sysman", new System_manager());
	}
	
	@PostMapping(value = "/createManager")
	public ModelAndView createManager(@Valid Manager m, Restaurant r, BindingResult result) throws Exception {
		logger.info("> createManager");
		if (result.hasErrors()) {
			return new ModelAndView("createManager", "formErrors", result.getAllErrors());
		}
		smService.createManager(m, r);
		logger.info("< createManager");
		
		return new ModelAndView("createManager", "manager", new Manager());
	}
}
