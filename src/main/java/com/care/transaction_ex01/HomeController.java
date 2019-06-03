package com.care.transaction_ex01;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.care.service.TicketService;
import com.care.transaction_dao.TicketDAO;
import com.care.service.*;
import com.care.transaction_dto.TicketDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private TicketService ts;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public HomeController() {
		String configLocation = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx =
				new GenericXmlApplicationContext(configLocation);
		ts = ctx.getBean("ticketServiceImpl", TicketServiceImpl.class);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/buy_ticket")
	public String buy_teicket() {
		return "buy_ticket";
	}
	
	@RequestMapping("buy_ticket_card")
	public String buy_ticket_card(TicketDTO ticketDTO, Model model) {
		//ts = new TicketServiceImpl();
		System.out.println("ts : " + ts);
		model.addAttribute("ticketDTO", ticketDTO);
		ts.execute(model);
		return "buy_ticket_end";
	}
	
	@RequestMapping("/result")
	public String buy_ticket_card(Model model) {
		ts = new TicketResultServiceImpl();
		ts.execute(model);
		return "result_ticket";
	}
}
