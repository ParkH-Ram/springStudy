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
public class ProjectTB {
	private int proNo;
	private String title;
	private Date startDate;
	private Date finishDate;
	private String note;
	private String deleted;
}
