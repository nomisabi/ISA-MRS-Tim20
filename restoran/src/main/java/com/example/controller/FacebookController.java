package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/connect/facebook")
public class FacebookController {
	@Autowired
    private Facebook facebook;
    @Autowired
	private ConnectionRepository connectionRepository;

   
    @GetMapping
    public String helloFacebook(Model model) {
    	System.out.println(connectionRepository.findPrimaryConnection(Facebook.class));
        return "redirect:/#home";
    }

}
