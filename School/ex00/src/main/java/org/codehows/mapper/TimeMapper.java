package org.codehows.mapper;
// 풀네임 -> org.codehows.mapper.TimeMapper.getTime2 ..


import org.apache.ibatis.annotations.Select;



public interface TimeMapper {
	
	
	// 시스템상 현재 시간 가져오기
	//간단한 쿼리문을 어노테이션으로 처리 함   //디비 연결 간단하게
	
	//; 없어야 한다
	@Select("SELECT sysdate FROM dual")
	String getTime();
	
	String getTime2();
	
	
}
