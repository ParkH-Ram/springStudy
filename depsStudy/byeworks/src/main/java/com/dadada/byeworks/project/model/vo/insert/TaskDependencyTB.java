package com.dadada.byeworks.project.model.vo.insert;

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
public class TaskDependencyTB {
	private int dependNo;
	private int ideNo;
	private int reqNo;
}
