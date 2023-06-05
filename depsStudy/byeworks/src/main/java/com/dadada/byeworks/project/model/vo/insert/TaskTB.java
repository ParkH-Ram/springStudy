package com.dadada.byeworks.project.model.vo.insert;

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
public class TaskTB {
	private int ideNo;
	private int taskNo;
	private String category;
	private String title;
	private Date startDate;
	private int duration;
	private int proNo;
}
