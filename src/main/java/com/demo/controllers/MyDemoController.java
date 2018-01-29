package com.demo.controllers;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.model.Account;

@Controller
public class MyDemoController {
	//Sample: http://localhost:8080/springMVCDemo/getQuote.html
	
	private String[] quotes = {"Spring MVC","MVC Training","Spring","MVC"};
	
	@RequestMapping(value="/getQuote")
	public String getRandomQuote(Model model) {
		
		int rand = new Random().nextInt(quotes.length);
		String randomeQuote = quotes[rand];
		
		model.addAttribute("randomQuote",randomeQuote);
		
		return "quote";
	}
	
	@RequestMapping(value="/createAccount")
	public String createAccount(@Valid @ModelAttribute("aNewAccount") Account account, BindingResult result) {
		
		if (result.hasErrors()) {
			System.out.println("Form has errors.");
			return "createAccount";
			
		}
		
		System.out.println(account.getFirstname() + " " + account.getLastname() + " " + account.getAge() + " " + account.getAddress() + " " + account.getEmail());
		
		return "createAccount";
	}
}
