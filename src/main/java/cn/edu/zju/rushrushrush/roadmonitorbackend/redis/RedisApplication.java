package cn.edu.zju.rushrushrush.roadmonitorbackend.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@Import({ RedisConfig.class })
public class RedisApplication {
	
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(RedisApplication.class)
			.web(WebApplicationType.NONE)
			.run(args);
		
		RedisTest redisTest = context.getBean(RedisTest.class);
		
		RedisTestUser user = new RedisTestUser();
		user.setUserName( "testUser" );
		user.setPhoneNum(  "123456" );
		user.setPassword( "testPassword" );
		redisTest.send( "tttt", "159999" );
		redisTest.send( "user1", user );
		RedisTestUser o = ( RedisTestUser )redisTest.get( "user1" );
		System.out.println( o.getUserName() + "\n" + o.getPassword() + "\n" + o.getPhoneNum() );
		System.out.println( redisTest.get( "tttt" ) );
		
		context.close();
	}

}