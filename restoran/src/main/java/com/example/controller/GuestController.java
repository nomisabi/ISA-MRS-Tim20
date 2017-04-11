package com.example.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.Guest;
import com.example.service.GuestService;

@Controller
@RequestMapping("/guests")

public class GuestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GuestService guestService;
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String getNew(Model model) {
		model.addAttribute("guest", new Guest());
		return "createGuest";
	}
	
	@PostMapping(value = "/create")
	public ModelAndView createGuest(@Valid Guest guest, BindingResult result) throws Exception {
		logger.info("> addGuest");
		if (result.hasErrors()) {
			return new ModelAndView("createGuest", "formErrors", result.getAllErrors());
		}
		guestService.signUp(guest);
		logger.info("< addGuest");
		
		return new ModelAndView("redirect:/guests/new");
	}

}
