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
public class Task {
	private Integer ideNo; // 식별번호 task, task_member, task_dependency
	private Integer taskNo; // 작업번호 task
	private String category; // 카테고리 task
	private String title; // 작업명 task
	private Date start; // 시작일 task
	private Integer duration; // 기간 task
	private Integer proNo; // 프로젝트 번호 task, task_member
	private Integer memberNo; // 직원번호 task_member
	private Integer dependNo; // 종속성 번호 task_dependency
	private Integer reqNo; // 요구작업 번호 task_dependency
	private String memberName; // 직원이름
}