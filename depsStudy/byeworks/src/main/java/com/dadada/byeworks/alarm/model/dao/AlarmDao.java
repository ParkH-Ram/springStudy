package com.dadada.byeworks.alarm.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dadada.byeworks.alarm.model.vo.Alarm;

@Repository
public class AlarmDao {
	
	public ArrayList<Alarm> selectNoteList(SqlSessionTemplate sqlSession, int no) {
		return (ArrayList)sqlSession.selectList("alarmMapper.selectNoteList", no);
	}
	
	public int deleteAllNote(SqlSessionTemplate sqlSession, int no) {
		return sqlSession.update("alarmMapper.deleteAllNote", no);
	}

	public int deleteOneNote(SqlSessionTemplate sqlSession, int no) {
		return sqlSession.update("alarmMapper.deleteOneNote", no);
	}
	
	public int insertAlarm(SqlSessionTemplate sqlSession, Alarm a) {
		return sqlSession.insert("alarmMapper.insertAlarm", a);
	}
	
	public int updateRead(SqlSessionTemplate sqlSession, int no) {
		return sqlSession.update("alarmMapper.updateRead", no);
	}
	
	public ArrayList<Alarm> showAlarm(SqlSessionTemplate sqlSession, int no) {
		return (ArrayList)sqlSession.selectList("alarmMapper.showAlarm", no);
	}
	
	public int countAlarm(SqlSessionTemplate sqlSession, int no) {
		return sqlSession.selectOne("alarmMapper.countAlarm", no);
	}
	
	public int readAlarm(SqlSessionTemplate sqlSession, int alarmNo) {
		return sqlSession.update("alarmMapper.readAlarm", alarmNo);
	}
}
