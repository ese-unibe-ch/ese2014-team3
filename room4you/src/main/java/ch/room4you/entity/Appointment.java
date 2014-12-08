package ch.room4you.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;


import ch.room4you.entity.Ad;

@Entity
public class Appointment {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "appointmentAd_id")
	private Ad appointmentAd;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<User> visitors = new ArrayList<User>();
		
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private AppointmentDate appointDate;
	
	private int nmbrVisitors;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Ad getAppointmentAd() {
		return appointmentAd;
	}

	public void setAppointmentAd(Ad ad) {
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

	public void setNmbrVisitors(int maxVisitors) {
		this.nmbrVisitors = maxVisitors;
	}
	
	public void decrementNmbrVisitors() {
		nmbrVisitors--;
	}


}
