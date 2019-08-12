package com.lipengyun.redis.test;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lipengyun.redis.domain.User;
import com.lipengyun.rk.domain.RandomUtil;
import com.lipengyun.rk.domain.StringUtil;

/**
 * 使用Hash保存十万个对象
 * @ClassName: RedisJDKList 
 * @Description: TODO
 * @author: 犯人:李某
 * @date: 2019年8月12日 上午9:21:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class RedisHash {

	@Resource
	private RedisTemplate<String, User> redisTemplate;
	
	@Test
	public void RedisHASH_test() {
		
		Map<String, User> users = new HashMap<String, User>();
		
		for(int i=1;i<=100000;i++) {
			users.put("e"+i, new User(i, StringUtil.randomChineseString1(3), RandomUtil.random(0, 1), "139"+RandomUtil.randomString(9), RandomUtil.random(3, 20)+"@qq.com",RandomUtil.random(18, 70)));
		}
		
		long startTime = System.currentTimeMillis();
		
		redisTemplate.opsForHash().putAll("user_keys", users);
		long endTime = System.currentTimeMillis();
		
		System.out.println("采用Hash保存十万个对象耗时:"+(endTime-startTime));
	}
	
	
}
