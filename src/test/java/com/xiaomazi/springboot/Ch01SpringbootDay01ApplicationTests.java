package com.xiaomazi.springboot;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Ch01SpringbootDay01ApplicationTests {

	@Autowired
	private StringEncryptor stringEncryptor;


	@Test
	void contextLoads() {
		//加密
		String username = stringEncryptor.encrypt("root");
		System.out.println("加密username: " + username);
		//解密
		String decUsername = stringEncryptor.decrypt(username);
		System.out.println("解密username: " + decUsername);

		//加密
		String password = stringEncryptor.encrypt("123456");
		System.out.println("password: " + password);
		//解密
		String decPassword = stringEncryptor.decrypt(password);
		System.out.println("解密password: " + decPassword);
	}

}
