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
public class Project {
	private Integer proNo; // 프로젝트 번호 project project_member
	private String title; // 프로젝트명 project
	private Date startDate; // 시작일 project
	private Date finishDate; // 종료일 project
	private String note; // 메모 project
	private String deleted; // 삭제 여부 project
	private Integer memberNo; // 직원번호 project_member
	private String memberName; // 직원번호 project_member
//	private int attachNo; // 첨부파일 번호 project_attachment
//	private String newTitle; // 수정명 project
	
	// 하지만 이 클래스는 장식 ^^7
}