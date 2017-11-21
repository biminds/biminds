package com.biminds.test;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.biminds.framework.util.HashUtils;



/**
 * 
 * 
 * @author bieskei
 * @date 2017年10月29日 上午11:52:01
 * @since 1.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-context.xml"})
@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
public class TestSS {

	
	
	public static void main(String []args){
		String saltValue = HashUtils.createRandomSalt();
		System.out.println(saltValue);
		System.out.println(HashUtils.computeSaltedHash("111111", saltValue));
	}
	
	@Test
	public void getUUID(){
		System.out.println(UUID.randomUUID().toString());
	}
}
