package com.drama.jdi;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		AbstractApplicationContext ctx =  new GenericXmlApplicationContext("classpath:controllerCTX.xml");
		
		ctx.getEnvironment().getPropertySources();
//		TempBean tpBean = (TempBean) ctx.getBean("tpbean");
		TempBean tpBean = ctx.getBean("tpbean", TempBean.class);
		logger.info("age : " + tpBean.getAge() + ", name : " + tpBean.getName());
		
		return "home";
	}
	
	@RequestMapping("/test")
	public void home(Model model, @RequestParam(value="name", required=false) String name) {
//		return "hi";
		System.out.println("Name : " + name);
	}
	
}
