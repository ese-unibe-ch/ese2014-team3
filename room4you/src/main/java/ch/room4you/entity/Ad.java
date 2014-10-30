package ch.room4you.entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Ad {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(length = 1000)
	private String title;

	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(length = Integer.MAX_VALUE)
	private String description;
	
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(length = Integer.MAX_VALUE)
	private String weAreLookingFor;

	@Column(name = "published_date")
	private Date publishedDate;

	private String street;
	
	private String zip;
	
	private String city;
	
	private float nbrRooms;
	
	private int nbrRoomsMates;

	private Date availableFrom;
	
	private int rentPerMonth;
	
	private boolean sharedApartment;
	

	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(length = Integer.MAX_VALUE)
	private String additionalInformation;


	@Lob
	@Column(name="ROOM_IMAGES")
	@OneToMany(mappedBy = "ad", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Image> images;
	

	@Column(name="ROOM_MATES")
	@OneToMany(mappedBy = "ad", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<RoomMate> roomMates;
	


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
    public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	
	public void addImage(Image image){
		this.images.add(image);
	}

	public void removeImage(Image image){
		this.images.remove(image);
	}
	
	public void removeImage(int imageIdx){
		this.images.remove(imageIdx);
	}
	
	public void removeAllImage(){
		this.images.removeAll(images);
	}
	

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public float getNbrRooms() {
		return nbrRooms;
	}

	public void setNbrRooms(float nbrRooms) {
		this.nbrRooms = nbrRooms;
	}

	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	public int getRentPerMonth() {
		return rentPerMonth;
	}

	public void setRentPerMonth(int rentPerMonth) {
		this.rentPerMonth = rentPerMonth;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	
	public List<RoomMate> getRoomMates() {
		return roomMates;
	}

	public void setRoomMates(List<RoomMate> roomMates) {
		this.roomMates = roomMates;
	}
	
	public void addRoomMate(RoomMate roomMate){
		roomMates.add(roomMate);
	}
	public boolean isSharedApartment() {
		return sharedApartment;
	}

	public void setSharedApartment(boolean sharedApartment) {
		this.sharedApartment = sharedApartment;
	}
	
	public int getNbrRoomsMates() {
		return nbrRoomsMates;
	}

	public void setNbrRoomsMates(int nbrRoomsMates) {
		this.nbrRoomsMates = nbrRoomsMates;
	}
	

	public String getWeAreLookingFor() {
		return weAreLookingFor;
	}

	public void setWeAreLookingFor(String weAreLookingFor) {
		this.weAreLookingFor = weAreLookingFor;
	}
}
