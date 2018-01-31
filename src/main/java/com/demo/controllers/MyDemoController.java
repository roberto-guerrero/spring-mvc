package com.demo.controllers;

import java.io.FileOutputStream;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.demo.model.Account;

@Controller
@SessionAttributes("aNewAccount")
public class MyDemoController {
	//Samples:
	//http://localhost:8080/springMVCDemo/getQuote?userName=John
	//http://localhost:8080/springMVCDemo/validateAccount
	//http://localhost:8080/springMVCDemo/account
	//http://localhost:8080/springMVCDemo/myForm
	
	
	/*** SIMPLE PAGE USING SPRING - Enabling the ModelAttribute we expect a parameter in the URL ***/
	
	private String[] quotes = {"Spring MVC","MVC Training","Spring","MVC"};
	
	@RequestMapping(value="/getQuote")
	public String getRandomQuote(Model model) {
		
		int rand = new Random().nextInt(quotes.length);
		String randomeQuote = quotes[rand];
		
		model.addAttribute("randomQuote",randomeQuote);
		
		return "quote";
	}
	
	//@ModelAttribute
	public void setUserDetails(@RequestParam("userName") String userName, Model model) {
		model.addAttribute("userName", userName);
		
		String userRole = "Undefined";
		
		if (userName.equals("Andy")) {
			userRole = "Student";
		} else if (userName.equals("John")) {
			userRole = "Teacher";
		} else if (userName.equals("Allana")) {
			userRole = "Dean";
		}
		
		model.addAttribute("userRole", userRole);
		
		System.out.println("Model updated with user information.");
	}
	
	
	/*** FIELDS VALIDATIONS ***/
	
	@RequestMapping(value="/validateAccount")
	public String createAccount(@Valid @ModelAttribute("aValidAccount") Account account, BindingResult result) {
		
		if (result.hasErrors()) {
			System.out.println("Form has errors.");
			return "validateAccount";
			
		}
		
		System.out.println(account.getFirstname() + " " + account.getLastname() + " " + account.getAge() + " " + account.getAddress() + " " + account.getEmail());
		
		return "validateAccount";
	}
	
	
	/*** ADD ACCOUNT PAGE, SAVE USING POST METHOD, DISPLAYING ACCOUNT CREATED, REDIRECTING AND ADDING MODEL ***/
	
	@RequestMapping(value="/account")
	public String addAccount(@ModelAttribute("aNewAccount") Account account) {
		System.out.println(account.toString())
		;
		return "account";
	}
	
	@RequestMapping(value="/accountCreated", method=RequestMethod.POST)
	public String performCreate(@ModelAttribute("aNewAccount") Account account) {
		
		System.out.println("Creating User..." + account.toString());
		
		return "redirect:accConfirm";
	}
	
	@RequestMapping(value="/accConfirm")
	public String accountConfirmation(@ModelAttribute("aNewAccount") Account account) {
		
		System.out.println("Account Confirmed:" + account.toString());
		
		return "accountCreated";
	}
	
	@ModelAttribute
	public void addAccountToModel(Model model) {
		if (!model.containsAttribute("aNewAccount")) {
			System.out.println("Adding aNewAccount to Model.");
			Account a = new Account();
			model.addAttribute("aNewAccount", a);
		}
	}
	
	
	/*** UPLOADING FILES WITH SPRING ***/
	
	@RequestMapping(value="/myForm")
	public String myForm() {
		return "myForm";
	}
	
	@RequestMapping(value="/handleForm")
	public String handleForm(@RequestParam("file") MultipartFile file) {
		
		try {
			if (!file.isEmpty()) {
				byte[] bytes = file.getBytes();
				FileOutputStream fos = new FileOutputStream("C:\\Users\\r.guerrero.flores\\Desktop\\myFile.jpg");
				fos.write(bytes);
				fos.close();
				System.out.println("File saved Successfully.");
			}
		} catch (Exception e) {
			System.out.println("No file saved. Error.");
		}
		
		return "operationComplete";
	}
	
	
	/*** WORKING WITH COOKIES ***/
	
	@RequestMapping(value="/addCookie")
	public String addCookie(HttpServletResponse response) {
		//Add a random cookie
		response.addCookie(new Cookie("myRandomCookie", "aCookieIAdded"));
		System.out.println("Cookie added.");
		
		return "demoPage";
	}
	
	@RequestMapping(value="/getCookie")
	public String getCookie(@CookieValue("myRandomCookie") String myCookie) {
		System.out.println("Cookie retrieved: " + myCookie);
		
		return "demoPage";
	}
	
	
	
	/*** @REQUESTMAPPING - PARAMS AND HEADERS VALIDATION ***/
	
	//http://localhost:8080/springmvc/pickVehicle?owner=Roberto
	
	//params="owner" - Will work if owner param is in the url
	//params="!owner" - Will work only if owner param is not present in the url.
	//params="owner=Roberto" - Will work if owner param and Roberto value is present in the url.
	//headers="X_HEADER" - Will work only if a header with name X_HEADER is present.
	//headers="X_HEADER=123" - Will work only if a header with name X_HEADER is present and the value is 123.
	
	private String[] vehicle = {"Car","Motorcycle","Ariplane","Train"};
	
	@RequestMapping(value="/getVehicle", params="owner=Roberto", headers="X_HEADER=123")
	public String getRandomVehicle(Model model) {
		
		int rand = new Random().nextInt(vehicle.length);
		String randomeVehicle = vehicle[rand];
		
		model.addAttribute("randomVehicle",randomeVehicle);
		
		return "vehicle";
	}
	
	
	/*** ACCESSING REQUEST PARAMETERS ***/
	
	@RequestMapping(value="/getCity")
	public String getRandomCity(@RequestParam("country") String country, Model model) {
		
		String city = "";
		
		if (country.equals("Italy")) {
			city = "Venice";
		} else if (country.equals("US")) {
			city = "New York";
		} else if (country.equals("Canada")) {
			city = "Niagara";
		}
		
		model.addAttribute("country",country);
		model.addAttribute("city",city);
		
		return "city";
	}
}
