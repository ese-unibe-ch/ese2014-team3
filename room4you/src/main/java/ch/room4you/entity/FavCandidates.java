package ch.room4you.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class FavCandidates {

	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne
	private Ad ad;
	
	@ManyToOne (cascade = CascadeType.ALL)
	private User adPlacer;
	
	@ManyToMany (fetch = FetchType.EAGER)
	private List<User> visitors = new ArrayList<User>();
	
	//@OneToMany
	//private List<Appointment> appointments = new ArrayList<Appointment>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<User> visitors) {
		this.visitors = visitors;
	}

/*	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
*/
	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public User getAdPlacer() {
		return adPlacer;
	}

	public void setAdPlacer(User adPlacer) {
		this.adPlacer = adPlacer;
	}
}
