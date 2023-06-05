package com.dadada.byeworks.project.model.service;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dadada.byeworks.project.model.dao.ProjectDao;
import com.dadada.byeworks.project.model.vo.ProjectMain;
import com.dadada.byeworks.project.model.vo.Task;
import com.dadada.byeworks.project.model.vo.TasksCategory;
import com.dadada.byeworks.project.model.vo.insert.ProjectMemberTB;
import com.dadada.byeworks.project.model.vo.insert.ProjectTB;
import com.dadada.byeworks.project.model.vo.insert.TaskDependencyTB;
import com.dadada.byeworks.project.model.vo.insert.TaskMemberTB;
import com.dadada.byeworks.project.model.vo.insert.TaskTB;
import com.dadada.byeworks.project.model.vo.TasksCategories;

@Service("proService")
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private ProjectDao proDao;
	
	@Override
	public void saveTasks(TasksCategories allTasks) {
		List<TasksCategory> categories = allTasks.getTasksCategory();
		List<Task> tasks = new ArrayList<Task>();
		Integer memberNo = selectMemberName(allTasks.getMemberName());
		allTasks.setMemberNo(memberNo);
		for (int i = 0; i < categories.size(); i++) {
			String categoryValue = categories.get(i).getCategory();
			for (int j = 0; j < categories.get(i).getTasks().size(); j++) {
				categories.get(i).getTasks().get(j).setCategory(categoryValue);
				Integer tmpNo = selectMemberName(categories.get(i).getTasks().get(j).getMemberName());
				categories.get(i).getTasks().get(j).setMemberNo(tmpNo);
				tasks.add(categories.get(i).getTasks().get(j));
			}
		}
		ProjectTB project = new ProjectTB();
		project.setTitle(allTasks.getTitle());
		project.setStartDate(allTasks.getStartDate());
		project.setFinishDate(allTasks.getFinishDate());
		project.setNote(allTasks.getNote());
		insertProject(project);
		ProjectMemberTB projectMember = new ProjectMemberTB();
		Integer proNo = selectRecentlyAddedProject();
		projectMember.setProNo(proNo);
		projectMember.setMemberNo(allTasks.getMemberNo());
		insertProjectMember(projectMember);
		for (int i = 0; i < tasks.size(); i++) {
			TaskTB task = new TaskTB();
			task.setTaskNo(tasks.get(i).getTaskNo());
			task.setCategory(tasks.get(i).getCategory());
			task.setTitle(tasks.get(i).getTitle());
			task.setStartDate(tasks.get(i).getStart());
			task.setDuration(tasks.get(i).getDuration());
			task.setProNo(proNo);
			insertTask(task);
			TaskMemberTB taskMember = new TaskMemberTB();
			taskMember.setProNo(proNo);
			taskMember.setMemberNo(tasks.get(i).getMemberNo());
			Integer lastIdeNo = selectRecentlyAddedTask();
			taskMember.setIdeNo(lastIdeNo);
			insertTaskMember(taskMember);
			TaskDependencyTB taskDependency = new TaskDependencyTB();
			taskDependency.setIdeNo(lastIdeNo);
			taskDependency.setReqNo(tasks.get(i).getReqNo());
			insertTaskDependency(taskDependency);
		}
	}

	private void insertTaskDependency(TaskDependencyTB taskDependency) {
		proDao.insertTaskDependency(sqlSession, taskDependency);
	}

	private Integer selectRecentlyAddedTask() {
		return proDao.selectRecentlyAddedTask(sqlSession);
	}

	private void insertTaskMember(TaskMemberTB taskMember) {
		proDao.insertTaskMember(sqlSession, taskMember);
	}

	private void insertTask(TaskTB task) {
		proDao.insertTask(sqlSession, task);
	}

	private int selectRecentlyAddedProject() {
		return proDao.selectRecentlyAddedProject(sqlSession);
	}

	private void insertProjectMember(ProjectMemberTB projectMember) {
		proDao.insertProjectMember(sqlSession, projectMember);
	}

	private Integer selectMemberName(String memberName) {
		return proDao.getmemberNo(sqlSession, memberName);
	}

	@Override
	public ProjectMain selectProject(int memberNo) {
		return proDao.selectProject(sqlSession, memberNo);
	}

	public void insertProject(ProjectTB project) {
		proDao.insertProject(sqlSession, project);
	}

}
