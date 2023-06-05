package com.dadada.byeworks.note.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadada.byeworks.note.model.dao.NoteDao;
import com.dadada.byeworks.note.model.vo.Note;

@Service("ntService")
public class NoteServiceImpl implements NoteService{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private NoteDao ntDao;

	@Override
	public ArrayList<Note> receiveList(int no) {
		return ntDao.receiveList(sqlSession, no);
	}

	@Override
	public ArrayList<Note> sendList(int no) {
		return ntDao.sendList(sqlSession, no);
	}

	@Override
	public int sendNote(Note n) {
		return ntDao.sendNote(sqlSession, n);
	}

	@Override
	public Note detailNote(int noteNo) {
		return ntDao.detailNote(sqlSession, noteNo);
	}

	@Override
	public int updateReadcheck(Note n) {
		return ntDao.updateReadcheck(sqlSession, n);
	}

	@Override
	public int cancelNote(ArrayList<Integer> noteNo) {
		return ntDao.cancelNote(sqlSession, noteNo);
	}

	@Override
	public ArrayList<Note> selectList(ArrayList<Integer> intList) {
		return ntDao.selectList(sqlSession, intList);
	}

	@Override
	public int deleteNote(ArrayList<Integer> noteNo, String classStr) {
		if(classStr.equals("receive")) { // 받은 쪽지면
			return ntDao.deleteReceive(sqlSession, noteNo);
		} else { // 보낸 쪽지면
			return ntDao.deleteSend(sqlSession, noteNo);
		}
	}

	@Override
	public int returnSequence() {
		return ntDao.returnSequence(sqlSession);
	}

}
