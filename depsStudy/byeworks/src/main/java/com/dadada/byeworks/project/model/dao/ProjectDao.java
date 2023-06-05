package com.dadada.byeworks.project.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dadada.byeworks.project.model.vo.ProjectMain;
import com.dadada.byeworks.project.model.vo.insert.ProjectMemberTB;
import com.dadada.byeworks.project.model.vo.insert.ProjectTB;
import com.dadada.byeworks.project.model.vo.insert.TaskDependencyTB;
import com.dadada.byeworks.project.model.vo.insert.TaskMemberTB;
import com.dadada.byeworks.project.model.vo.insert.TaskTB;

@Repository
public class ProjectDao {
	
	public ProjectMain selectProject(SqlSessionTemplate sqlSession, int memberNo) {
		ProjectMain pm = sqlSession.selectOne("projectMapper.selectProject", memberNo);
		return pm;
	}
	
	public Integer getmemberNo(SqlSessionTemplate sqlSession, String memberName) {
		Integer memNo = sqlSession.selectOne("projectMapper.selectMemberNo", memberName);
		return memNo;
	}
	
	public void insertProject(SqlSessionTemplate sqlSession, ProjectTB projectTB) {
		sqlSession.insert("projectMapper.insertProject", projectTB);
	}

	public void insertProjectMember(SqlSessionTemplate sqlSession, ProjectMemberTB projectMemberTB) {
		sqlSession.insert("projectMapper.insertProjectMember", projectMemberTB);
	}

	public Integer selectRecentlyAddedProject(SqlSessionTemplate sqlSession) {
		Integer proNo = sqlSession.selectOne("projectMapper.selectRecentlyAddedProject");
		return proNo;
	}

	public void insertTask(SqlSessionTemplate sqlSession, TaskTB taskTB) {
		sqlSession.insert("projectMapper.insertTask", taskTB);
	}

	public void insertTaskMember(SqlSessionTemplate sqlSession, TaskMemberTB taskMemberTB) {
		sqlSession.insert("projectMapper.insertTaskMember", taskMemberTB);
	}

	public Integer selectRecentlyAddedTask(SqlSessionTemplate sqlSession) {
		Integer lastIdeNo = sqlSession.selectOne("projectMapper.selectRecentlyAddedTask");
		return lastIdeNo;
	}

	public void insertTaskDependency(SqlSessionTemplate sqlSession, TaskDependencyTB taskDependencyTB) {
		sqlSession.insert("projectMapper.insertTaskDependency", taskDependencyTB);
	}

}