package com.dadada.byeworks.schedule.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadada.byeworks.schedule.model.dao.ScheduleDao;
import com.dadada.byeworks.schedule.model.vo.Schedule;

@Service("scService")
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private ScheduleDao scDao;
	
	// 내 일정 생성하기
	@Override
	public int insertSchedule(Schedule s) {
		return scDao.insertSchedule(sqlSession, s);
	}

	// 일정 조회하기
	@Override
	public ArrayList<Schedule> selectSchedule(int no) {
		return scDao.selectSchedule(sqlSession, no);
	}

	// 일정 하나만 조회하기
	@Override
	public Schedule selectOne(int schNo) {
		return scDao.selectOne(sqlSession, schNo);
	}

	@Override
	public int updateSchedule(Schedule s) {
		return scDao.updateSchedule(sqlSession, s);
	}

	@Override
	public int deleteSchedule(int schNo) {
		return scDao.deleteSchedule(sqlSession, schNo);
	}

	@Override
	public ArrayList<Schedule> selectMainList(int no) {
		return scDao.selectMainList(sqlSession, no);
	}

}
