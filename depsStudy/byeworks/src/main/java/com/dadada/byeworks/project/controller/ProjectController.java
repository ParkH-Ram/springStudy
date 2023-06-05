package com.dadada.byeworks.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dadada.byeworks.project.model.service.ProjectService;
import com.dadada.byeworks.project.model.vo.TasksCategories;

@Controller
public class ProjectController {
	@Autowired
	ProjectService projectService;
	
	@RequestMapping("insertProForm.pro")
	public String insertProForm() {
		return "project/insertProject";
	}
	
	@RequestMapping("postTasks")
	public String postTasks(@ModelAttribute TasksCategories allTasks) {
		projectService.saveTasks(allTasks);
		return "project/insertProject";
	}
}
