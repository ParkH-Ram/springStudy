package com.dadada.byeworks.notice.model.vo;

import java.sql.Date;

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
public class Notice {
	
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDate;
	private Date noticeModifyDate;
	private String status;
	private int noticeReadcnt;
	private String fileName;
	private String fRename;
	private int memberNo;
	
	// 공지사항에서 작성자 명을 보여주기 위해 수정
	private String memberName;
}
