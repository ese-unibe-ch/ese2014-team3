package ch.room4you.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Alert {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	private String zip;
	
	private String city;
	
	private float nbrRoomsMin;
	
	private float nbrRoomsMax;
	
	private int nbrRoomsMatesMin;
	
	private int nbrRoomsMatesMax;

	
	private int rentPerMonthMin;
	
	private int rentPerMonthMax;
		

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public float getNbrRoomsMin() {
		return nbrRoomsMin;
	}

	public void setNbrRoomsMin(float nbrRoomsMin) {
		this.nbrRoomsMin = nbrRoomsMin;
	}

	public int getRentPerMonthMin() {
		return rentPerMonthMin;
	}

	public void setRentPerMonthMin(int rentPerMonthMin) {
		this.rentPerMonthMin = rentPerMonthMin;
	}

	
	public int getNbrRoomsMatesMin() {
		return nbrRoomsMatesMin;
	}

	public void setNbrRoomsMatesMin(int nbrRoomsMatesMin) {
		this.nbrRoomsMatesMin = nbrRoomsMatesMin;
	}

	public float getNbrRoomsMax() {
		return nbrRoomsMax;
	}

	public void setNbrRoomsMax(float nbrRoomsMax) {
		this.nbrRoomsMax = nbrRoomsMax;
	}

	public int getNbrRoomsMatesMax() {
		return nbrRoomsMatesMax;
	}

	public void setNbrRoomsMatesMax(int nbrRoomsMatesMax) {
		this.nbrRoomsMatesMax = nbrRoomsMatesMax;
	}

	public int getRentPerMonthMax() {
		return rentPerMonthMax;
	}

	public void setRentPerMonthMax(int rentPerMonthMax) {
		this.rentPerMonthMax = rentPerMonthMax;
	}

	@Override
	public String toString() {
		return "ZIP: " + zip
				+ ", City: " + city + ", Rooms: " + nbrRoomsMin+" to "+nbrRoomsMax
				+ ", Room mates: "
				+ nbrRoomsMatesMin + " to " + nbrRoomsMatesMax
				+ ", Rent: " + rentPerMonthMin + " to "
				+ rentPerMonthMax;
	}

}
