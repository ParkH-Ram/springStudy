package com.dadada.byeworks.note.model.vo;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Note {
	
	private int noteNo;
	private String noteTitle;
	private String noteContent;
	//private Date sendDate;
	private Timestamp sendDate;
	private String sendDateStr; // timestamp를 포맷을 지정하여 string에 저장
	
	private int readCheck;
	private String sendStatus;
	private String receiveStatus;
	private int sendNo;
	private int receiveNo;
	
	// ㅎㅎ
	private String sendName; // 보낸 사람
	private String receiveName; // 받는 사람
	private String sendId; // 보낸 사람 아이디
	private String receiveId; // 받는 사람 아이디
	
}
