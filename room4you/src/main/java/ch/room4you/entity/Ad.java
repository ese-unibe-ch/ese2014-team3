package ch.room4you.entity;

import java.io.File;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

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

	@Column(name = "published_date")
	private Date publishedDate;

	private String street;
	
	private int zip;
	
	private String city;
	
	private float nbrRooms;
	
	private Date availableFrom;
	
	private String rentPerMonth;
	
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(length = Integer.MAX_VALUE)
	private String additionalInformation;
	
	

	@Lob
	@Column(name="ROOM_IMAGES", nullable=false, columnDefinition="mediumblob")
	@OneToMany(mappedBy = "ad", cascade = CascadeType.REMOVE)
	private List<Image> images;
	

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


//	public List<Image> getImages() {
//		return images;
//	}
//
//	public void setImages(List<Image> images) {
//		this.images = images;
//	}
	
//	public void addImageFromPath(String filePath) {
//	   	 File file = new File(filePath);
//	   	 Image image = new Image();
//	   	 image.setImage(file);
//	   	 addImage(image);
//	}
	
	
	
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

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
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

	public String getRentPerMonth() {
		return rentPerMonth;
	}

	public void setRentPerMonth(String rentPerMonth) {
		this.rentPerMonth = rentPerMonth;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
}
