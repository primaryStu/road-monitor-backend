package cn.edu.zju.rushrushrush.roadmonitorbackend.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisTest {
	
	@Autowired
	private RedisTemplate redisTemplate;

	public void send( String key, Object val ) {
        this.redisTemplate.opsForValue().set( key, val );
	}
	
	public Object get( String key ) {
		return this.redisTemplate.opsForValue().get( key );
	}
}
