package com.hipporing.board.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hipporing.board.test.service.TestService;
import com.hipporing.board.test.vo.TestVO;

@Controller
@RequestMapping(value="")
public class WebController {
	
	@Autowired
	private TestService testService;
		
	@RequestMapping(value = "/",  method = {RequestMethod.GET})
	public String index(Model model) {
	
		//model.addAttribute("test", "web");
		
		List<TestVO> tests = this.testService.getTests();
		
		model.addAttribute("tests", tests);
		
		for(TestVO test : tests) {
			   System.out.println(test.getParam1());
			   System.out.println(test.getParam2());
			   System.out.println(test.getParam3());
		}
		
		return "web/index";
	}
	
	@RequestMapping(value = "/write", method = {RequestMethod.GET})
	public String write() {
		return "web/write";
	}
	
	@RequestMapping(value = "/write", method = {RequestMethod.POST})
	public String postWrite(TestVO test) {
		System.out.println(test.toString());
		this.testService.insertTest(test);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/detail/{key}", method = {RequestMethod.GET})
	public String detail(@PathVariable(name = "key", required = true) int key, Model model) {
		
		TestVO test = testService.getTest(key);
		model.addAttribute("test", test);
		
		return "web/detail";		
	}
	
	@RequestMapping(value = "delete", method = {RequestMethod.POST})
	public String delete(TestVO test) {
		testService.deleteTest(test.getKey());
		return "redirect:/";
	}
}
