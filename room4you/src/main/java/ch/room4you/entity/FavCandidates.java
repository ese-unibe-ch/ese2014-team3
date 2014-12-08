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

@Entity
public class FavCandidates {

	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne
	private Ad ad;
	
	@ManyToOne // (cascade = CascadeType.REMOVE)
	@JoinColumn (name = "adplacer_id")
	private User adPlacer;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "visitor_id")
	private List<User> visitors = new ArrayList<User>();

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
