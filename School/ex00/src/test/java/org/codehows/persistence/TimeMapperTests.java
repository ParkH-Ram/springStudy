package org.codehows.persistence;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.codehows.mapper.TimeMapper;

import lombok.extern.log4j.Log4j2;


// 인터페이스로 테스트 진행
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class TimeMapperTests {
	
	@Autowired
	private TimeMapper timeMapper;
	
	@Test
	public void testTime1() {
		log.info(timeMapper.getTime());
	}
	
	@Test
	public void testTime2() {
		log.info(timeMapper.getTime2());
	}
	
	
	
	
	
/*	@Setter(onMethod_ = @Autowired)
	private TimeMapper timeMapper;
	
	@Test
	public void testGetTime() {
		log.info(timeMapper.getClass().getName());
		log.info(timeMapper.getTime());*/
	
}
