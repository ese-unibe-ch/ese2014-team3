package ch.room4you.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "room_mate")
public class RoomMate {

	@Id
	@GeneratedValue
	private Integer id;
	
	
	@ManyToOne
	@JoinColumn(name = "ad_id")
	private Ad ad;
	
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

}
