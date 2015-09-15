package com.um.ehrprivacy.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.client.MongoCollection;
import com.um.ehrprivacy.dao.ConnectionDB;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	/**
	 *  Handle the login operation.
	 *  	1. Doctor login.
	 *  	2. Patient login.
	 *  
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String handleLogin(String username, String password, Model model){
		
		MongoCollection<Document> collection = ConnectionDB.getCollection("db", "ehealthdata");
		
		if(username.equals("admin") && password.equals("123")){
			model.addAttribute("username", username);
			return "success";
		}else {
			return "home";
		}
	}
	
	/* Logout */
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String handleLogout(Model model) {
		return "home";
	}
}
