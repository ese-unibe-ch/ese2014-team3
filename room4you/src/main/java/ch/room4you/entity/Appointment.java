package ch.room4you.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ch.room4you.entity.Ad;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne 
	@JoinTable(name = "ad_id")
	private Ad appointmentAd;
	
	@OneToMany
	@JoinTable(name="user")
	@Column(nullable = true)
	private List<User> visitors = new ArrayList<User>();
	
	@OneToMany
	@JoinTable(name="message")
	private List<Application> applications = new ArrayList<Application>();
	
	@OneToOne
	private AppointmentDate appointDate;
	
	private int nmbrVisitors;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ad getAd() {
		return appointmentAd;
	}

	public void setAd(Ad ad) {
		this.appointmentAd = ad;
	}

	public List<User> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<User> visitors) {
		this.visitors = visitors;
	}

	
	public AppointmentDate getAppointDate() {
		return appointDate;
	}

	public void setAppointDate(AppointmentDate date) {
		this.appointDate = date;
	}

	public int getNmbrVisitors() {
		return nmbrVisitors;
	}

	public void setNmbrVisitors(int nmbrVisitors) {
		this.nmbrVisitors = nmbrVisitors;
	}

	public List<Application> getApplications() {
		return applications;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}
	
	


	

}
