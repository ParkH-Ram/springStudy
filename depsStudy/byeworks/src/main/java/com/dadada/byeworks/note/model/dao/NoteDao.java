package com.dadada.byeworks.note.model.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dadada.byeworks.note.model.vo.Note;

@Repository
public class NoteDao {
	
	public ArrayList<Note> receiveList(SqlSessionTemplate sqlSession, int no) {
		return (ArrayList)sqlSession.selectList("noteMapper.receiveList", no);
	}
	
	public ArrayList<Note> sendList(SqlSessionTemplate sqlSession, int no) {
		return (ArrayList)sqlSession.selectList("noteMapper.sendList", no);
	}
	
	public int sendNote(SqlSessionTemplate sqlSession, Note n) {
		return sqlSession.insert("noteMapper.sendNote", n);
	}
	
	public Note detailNote(SqlSessionTemplate sqlSession, int noteNo) {
		return sqlSession.selectOne("noteMapper.detailNote", noteNo);
	}
	
	public int updateReadcheck(SqlSessionTemplate sqlSession, Note n) {
		return sqlSession.update("noteMapper.updateReadcheck", n);
	}
	
	public int cancelNote(SqlSessionTemplate sqlSession, ArrayList<Integer> noteNo) {
		return sqlSession.update("noteMapper.cancelNote", noteNo);
	}
	
	public ArrayList<Note> selectList(SqlSessionTemplate sqlSession, ArrayList<Integer> noteNo) {
		return (ArrayList)sqlSession.selectList("noteMapper.selectList", noteNo);	
	}
	
	public int deleteReceive(SqlSessionTemplate sqlSession, ArrayList<Integer> noteNo) {
		return sqlSession.update("noteMapper.deleteReceive", noteNo);
	}
	
	public int deleteSend(SqlSessionTemplate sqlSession, ArrayList<Integer> noteNo) {
		return sqlSession.update("noteMapper.deleteSend", noteNo);
	}
	
	public int returnSequence(SqlSessionTemplate sqlSession) {
		return sqlSession.selectOne("noteMapper.returnSequence");
	}

}
