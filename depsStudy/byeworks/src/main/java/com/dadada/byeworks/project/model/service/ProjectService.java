package com.dadada.byeworks.project.model.service;

import com.dadada.byeworks.project.model.vo.ProjectMain;
import com.dadada.byeworks.project.model.vo.TasksCategories;

public interface ProjectService {
	void saveTasks(TasksCategories allTasks);
	
	// 이슬희: 메인에서 프로젝트 정보와 작업 정보를 조회하기 위해
	ProjectMain selectProject(int memberNo);

}