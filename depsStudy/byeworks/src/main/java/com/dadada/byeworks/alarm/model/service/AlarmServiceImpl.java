package com.dadada.byeworks.alarm.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadada.byeworks.alarm.model.dao.AlarmDao;
import com.dadada.byeworks.alarm.model.vo.Alarm;

@Service("alService")
public class AlarmServiceImpl implements AlarmService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private AlarmDao alDao;

	@Override
	public ArrayList<Alarm> selectNoteList(int no) {
		return alDao.selectNoteList(sqlSession, no);
	}

	@Override
	public int deleteAllNote(int no) {
		return alDao.deleteAllNote(sqlSession, no);
	}

	@Override
	public int deleteOneNote(int no) {
		return alDao.deleteOneNote(sqlSession, no);
	}

	@Override
	public int insertAlarm(Alarm a) {
		return alDao.insertAlarm(sqlSession, a);
	}

	@Override
	public int updateRead(int no) {
		return alDao.updateRead(sqlSession, no);
	}

	@Override
	public ArrayList<Alarm> showAlarm(int no) {
		return alDao.showAlarm(sqlSession, no);
	}

	@Override
	public int countAlarm(int no) {
		return alDao.countAlarm(sqlSession, no);
	}

	@Override
	public int readAlarm(int alarmNo) {
		return alDao.readAlarm(sqlSession, alarmNo);
	}

}
