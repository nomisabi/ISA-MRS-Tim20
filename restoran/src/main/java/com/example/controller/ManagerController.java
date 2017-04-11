package com.example.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Manager;
import com.example.service.ManagerService;


public class ManagerController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ManagerService mService;
	
	@PostMapping(value = "/addManager")
	public ModelAndView createManager(@Valid Manager m, BindingResult result) throws Exception {
		logger.info("> addManager");
		if (result.hasErrors()) {
			return new ModelAndView("createManager", "formErrors", result.getAllErrors());
		}
		mService.createManager(m);
		logger.info("< addManager");
		
		return new ModelAndView("redirect:/home");
	}
}
