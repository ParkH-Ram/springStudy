package com.dadada.byeworks.note.model.service;

import java.util.ArrayList;

import com.dadada.byeworks.note.model.vo.Note;

public interface NoteService {
	
	// 받은 쪽지 전체 조회
	ArrayList<Note> receiveList(int no);

	// 보낸 쪽지 전체 조회
	ArrayList<Note> sendList(int no);
	
	// 쪽지 보내기
	int sendNote(Note n);
	
	// 쪽지 세부조회
	Note detailNote(int noteNo);
	
	// 쪽지 읽음 처리
	int updateReadcheck(Note n);
	
	// 쪽지 발송 취소
	int cancelNote(ArrayList<Integer> noteNo);
	
	// n개의 noteNo에 해당하는 note 정보 리턴
	ArrayList<Note> selectList(ArrayList<Integer> intList);
	
	// 쪽지 삭제
	int deleteNote(ArrayList<Integer> noteNo, String classStr);
	
	// 쪽지의 가장 큰 번호를 리턴하는 메서드
	int returnSequence();
}
