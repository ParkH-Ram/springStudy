package com.dadada.byeworks.reservation.model.vo;
import java.sql.Date;
import java.sql.Timestamp;

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
public class ReservationDTO {

	private int reservationNo;
	private int roomNo;
	private int peopleCount;
	private String conferenceTitle;
	private String conferenceContent;
	private Date createDate;
	private String startDate;
	private String endDate;
	private String status;
	private int memberNo;
	
	private String reStartDate;
	private String reEndDate;

	private String dateTime;
	private String startTime;
	private String endTime;
	
	// 회의실에서 작성자 명을 보여주기 위해 수정
	private String memberName;
	
	@Override
	public String toString() {
		return "ReservationDTO [reservationNo=" + reservationNo + ", roomNo=" + roomNo + ", peopleCount=" + peopleCount
				+ ", conferenceTitle=" + conferenceTitle + ", conferenceContent=" + conferenceContent + ", dateTime="
				+ dateTime + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", memberNo="
				+ memberNo + ", reStartDate=" + reStartDate + ", reEndDate=" + reEndDate + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}
	
	
	
	
}
