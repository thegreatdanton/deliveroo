package com.example.deliveroo;

import com.example.deliveroo.cron.parser.exceptions.InvalidCronFieldException;
import com.example.deliveroo.cron.parser.models.CronExpression;
import com.example.deliveroo.cron.parser.service.CronParser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
class DeliverooApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void test1(){
		CronParser cronParser = new CronParser();
		String input = "0 0 1,2,3,15 * 1-5 /usr/bin/find";
		try{
			CronExpression cronExpression = cronParser.parseExpression(input);
			System.out.println(cronExpression.toString());
		} catch (Exception | InvalidCronFieldException exception){
			fail();
		}
	}

	@Test
	void test2(){
		CronParser cronParser = new CronParser();
		String input = "0 0 1,2,3,15 *  /usr/bin/find";
		try{
			CronExpression cronExpression = cronParser.parseExpression(input);
			System.out.println(cronExpression.toString());
		} catch (Exception | InvalidCronFieldException exception){
			Assert.isTrue(exception instanceof InvalidCronFieldException);
		}
	}

}
