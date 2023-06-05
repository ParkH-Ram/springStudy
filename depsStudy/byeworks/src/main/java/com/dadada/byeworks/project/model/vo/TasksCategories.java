package com.dadada.byeworks.project.model.vo;

import java.sql.Date;
import java.util.List;

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
public class TasksCategories {
	private List<TasksCategory> tasksCategory;
	private String title;
	private Date startDate;
	private Date finishDate;
	private Integer memberNo;
	private String memberName;
	private String note;
}