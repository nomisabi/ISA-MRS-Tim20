package com.example.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.System_manager;
import com.example.service.SystemManagerService;

@Controller
public class SystemManagerContoller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SystemManagerService smService;
	
	@PostMapping(value = "/signUp")
	public ModelAndView signUp(@Valid System_manager sm, BindingResult result) throws Exception {
		logger.info("> signUp");
		if (result.hasErrors()) {
			return new ModelAndView("signUpMan", "formErrors", result.getAllErrors());
		}
		smService.signUP(sm);
		logger.info("< signUp");
		
		return new ModelAndView("redirect:/home");
	}
	
	@PostMapping(value = "/createManager")
	public ModelAndView createManager(@Valid System_manager sm, Restaurant r, BindingResult result) throws Exception {
		logger.info("> createManager");
		if (result.hasErrors()) {
			return new ModelAndView("createManager", "formErrors", result.getAllErrors());
		}
		smService.createManager(sm, r);
		logger.info("< createManager");
		
		return new ModelAndView("redirect:/home");
	}
}
