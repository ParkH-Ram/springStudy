package com.dadada.byeworks.project.model.vo;

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

public class ProjectMain {

	private int proNo;			// 프로젝트 pk
	private String proTitle;	// 프로젝트 제목
	private Date proStart;		// 프로젝트 시작일
	private Date proEnd;		// 프로젝트 종료일
	private int proMem;			// 프로젝트 작업 참여 직원 번호
	private String taskTitle;	// 작업 제목
	private Date taskStart;		// 작업 시작일
	private Date taskEnd;		// 작업 종료일
	
	private int indicator;		// 진행률
}