package com.dadada.byeworks.reservation.model.vo;
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
public class Reservation { 

	private int reservationNo;
	private int roomNo;
	private int peopleCount;
	private String conferenceTitle;
	private String conferenceContent;
	private String createDate;
	private String startDate;
	private String endDate;
	private String status;
	private int memberNo;

}
