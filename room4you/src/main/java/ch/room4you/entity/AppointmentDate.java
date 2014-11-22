package ch.room4you.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class AppointmentDate {
	
/*	@OneToOne
	private Appointment appointment; */
	
	@Id
	@GeneratedValue
	private int id;

	private String appointDate;
	
	private String startTime;
	
	private String endTime;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(String date) {
		this.appointDate = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String toString() {
		return appointDate + " " + startTime + " " + endTime;
	}

}
