package tw.com.ispan.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tw.com.ispan.domain.CustomerBean;

@SpringBootTest
public class CustomerServiceTests {
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testLogin() {
		CustomerBean login = customerService.login("Alex", "A");
		System.out.println("login="+login);			
	}
	@Test
	public void testChangePassword() {
		boolean change = customerService.changePassword(
				"Ellen", "E", "EEE");
		System.out.println("change="+change);			
	}
}
