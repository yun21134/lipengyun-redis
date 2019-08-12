package com.lipengyun.redis.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
 * 使用Json保存十万个对象
 * @ClassName: RedisJDKList 
 * @Description: TODO
 * @author: 犯人:李某
 * @date: 2019年8月12日 上午9:21:57
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans2.xml")
public class RedisJSONList {

	@Resource
	private RedisTemplate<String, Serializable> redisTemplate;
	
	@Test
	public void RedisJSON_test() {
		
		List<User> users = new ArrayList<User>();
		
		for(int i=1;i<=100000;i++) {
			users.add(new User(i, StringUtil.randomChineseString1(3), RandomUtil.random(0, 1), "139"+RandomUtil.randomString(9), RandomUtil.random(3, 20)+"@qq.com",RandomUtil.random(18, 70)));
		}
		
		long startTime = System.currentTimeMillis();
		
		for (User user : users) {
			redisTemplate.opsForValue().set("u_"+user.getId(), user);
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("采用JSON保存十万个对象耗时:"+(endTime-startTime));
	}
	
	
}
