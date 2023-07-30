package com.jdc.demo.controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("home")
public class HomeController {

	/**
	 * URL is '/home'
	 * @return
	 */
	@GetMapping
	public String showMonths(ModelMap model) {
		
		model.put("title", "Year is %s".formatted(Year.now()));
		
		model.put("list", Month.values());
		
		// View will be '/views/home.jsp'
		return "home";
	}
	
	/**
	 * URL is '/home/JANUARY'
	 * @param month
	 * @return
	 */
	@GetMapping("{month}")
	public String showDays(
			@PathVariable Month month, 
			@RequestParam(required = false) DayOfWeek day, ModelMap model) {
		
		var selectedMonth = Year.now().atMonth(month);
		model.put("title", selectedMonth);
		
		model.put("weekDays", DayOfWeek.values());
		
		var list = new ArrayList<LocalDate>();
		var target = 1;
		
		while(target <= selectedMonth.lengthOfMonth()) {
			var dayOfMonth = selectedMonth.atDay(target ++);
			if(null == day || day.equals(dayOfMonth.getDayOfWeek())) {
				list.add(dayOfMonth);
			}
		}
		
		model.put("list", list);
		
		return "days";
	}
}
