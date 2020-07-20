package com.hipporing.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hipporing.board.test.service.TestService;
import com.hipporing.board.test.vo.TestVO;

@SpringBootTest
class BoardApplicationTests {

	@Autowired
	private TestService testservice;
	@Test
	void contextLoads() {
		int key = 1;
		TestVO test = this.testservice.getTest(key);

		System.out.println(test.toString());
	}

}
