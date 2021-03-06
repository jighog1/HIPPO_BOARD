package com.hipporing.board.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hipporing.board.core.base.BaseController;
import com.hipporing.board.test.service.LoginService;
import com.hipporing.board.test.service.TestService;
import com.hipporing.board.test.vo.LoginVO;
import com.hipporing.board.test.vo.TestVO;

@Controller
@RequestMapping(value="")
public class WebController extends BaseController {

	
	@Autowired
	private TestService testService;
	
	@Autowired
	private LoginService loginService;
		
	@RequestMapping(value = "/",  method = {RequestMethod.GET})
	public String index(Model model) {
	
		//model.addAttribute("test", "web");
		
		List<TestVO> tests = this.testService.getTests();
		
		model.addAttribute("tests", tests);
		
		this.log.trace("TRACE");
		this.log.debug("DEBUG");
		this.log.info("INFO");
		this.log.warn("WARN");
		this.log.error("ERROR");
	/*	
		for(TestVO test : tests) {
			   System.out.println(test.getParam1());
			   System.out.println(test.getParam2());
			   System.out.println(test.getParam3());
		}
	*/	
		return "web/index";
	}
	
	@RequestMapping(value = "/write", method = {RequestMethod.GET})
	public String write() {
		return "web/write";
	}
	
	@RequestMapping(value = "/write", method = {RequestMethod.POST})
	public String postWrite(TestVO test
			, HttpServletRequest reg) {
		
		HttpSession session = reg.getSession();
		String id = (String) session.getAttribute("id");
				
		test.setRegId(id);
		
		this.log.debug(test.toString());
		//System.out.println(test.toString());
		
		this.testService.insertTest(test);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/detail/{key}", method = {RequestMethod.GET})
	public String detail(@PathVariable(name = "key", required = true) int key
			, Model model
			, HttpServletRequest reg) {
		
		HttpSession session = reg.getSession();
		String id = (String) session.getAttribute("id");
		
		model.addAttribute("userId", id);
		
		TestVO test = testService.getTest(key);
		model.addAttribute("test", test);
		
		return "web/detail";		
	}
	
	@RequestMapping(value = "delete", method = {RequestMethod.POST})
	public String delete(TestVO test) {

		testService.deleteTest(test.getKey());
		return "redirect:/";
	}
	
	@RequestMapping(value = "/modify/{key}", method = {RequestMethod.GET})
	public String modify(@PathVariable(name = "key", required = true) int key, Model model) {
		
		TestVO test = testService.getTest(key);
		model.addAttribute("test", test);
		
		return "web/modify";		
	}

	@RequestMapping(value = "update", method = {RequestMethod.POST})
	public String update(TestVO test) {

		System.out.println("updatetest: " + test.toString());
		
		testService.updateTest(test);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET})
	public String login() {
		
		return "web/login";
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public String postLogin(LoginVO login
			,HttpServletRequest req) {
		
		if(this.loginService.checkLogin(login)) {
			HttpSession session = req.getSession();
			session.setAttribute("id", login.getId());
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method = {RequestMethod.GET})
	public String logout(HttpServletRequest req) {
		
		HttpSession session = req.getSession();
		session.invalidate();
		
		return "redirect:/";
	}
}
