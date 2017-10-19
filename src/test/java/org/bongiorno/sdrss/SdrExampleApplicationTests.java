package org.bongiorno.sdrss;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class SdrExampleApplicationTests {

	@Test
	public void contextLoads() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String pass = encoder.encode("password");
		System.out.println(pass);
	}

}
