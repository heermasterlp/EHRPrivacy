package com.um.ehrprivacy.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mongodb.client.MongoCollection;
import com.um.ehrprivacy.dao.ConnectionDB;
import com.um.ehrprivacy.utils.HandleLoginOperation;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("userId")
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
	public String handleLogin(HttpServletRequest request, Model model, HttpSession session){
		
//		MongoCollection<Document> collection = ConnectionDB.getCollection("10.119.180.42", 27017, "EhrPrivacy", "DoctorIndex");
		
		// 1. Get doctor id and password from requests.
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		session.setAttribute("userId", username);
		
		// 2. Search the node informations of doctor in the Doctor Index collections.
		if(HandleLoginOperation.verifyLegitimacyOfUser(username, password)){
			model.addAttribute("username", username);
			return "success";
		}else{
			return "home";
		}
		// 3. Verify the password based on node informations.
		
		// 4. If correct, doctor can login ,or not failed.
//		if(username.equals("admin") && password.equals("123")){
//			model.addAttribute("username", username);
//			return "success";
//		}else {
//			return "home";
//		}
	}
	
	/* Logout */
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String handleLogout(Model model) {
		return "home";
	}
}
