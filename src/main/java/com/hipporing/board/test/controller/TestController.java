package com.hipporing.board.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hipporing.board.test.service.TestService;
import com.hipporing.board.test.vo.TestVO;

@RestController
@RequestMapping(value = "/test")
public class TestController {
	
	@Autowired
	private TestService testservice;
	
	@RequestMapping(value = "/home", method = {RequestMethod.GET})
	public String home() {
	      return "/test/home";
	   }
	
	@RequestMapping(value = "/gettest/{key}", method = {RequestMethod.GET})
	public TestVO testservice(@PathVariable(name = "key", required = true) int key) {
		return testservice.getTest(key);
	}
	
	@RequestMapping(value = "/gettests", method = {RequestMethod.GET})
	public List<TestVO> testservices() {
		return testservice.getTests();
	}
	
	@RequestMapping(value = "/gettest", method = {RequestMethod.POST})
	public int insertTest(@RequestBody TestVO test) {
		return this.testservice.insertTest(test);
	}
	
	@RequestMapping(value = "/gettest/{key}", method = {RequestMethod.DELETE})
	public int deleteTest(@PathVariable(name = "key", required = true) int key) {
		return testservice.deleteTest(key);
	}
	
	@RequestMapping(value = "/gettest", method = {RequestMethod.PUT})
	public int updateTest(@RequestBody TestVO test) {
		return this.testservice.updateTest(test);
	}
}
