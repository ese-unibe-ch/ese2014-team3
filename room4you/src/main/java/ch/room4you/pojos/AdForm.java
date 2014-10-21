/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.room4you.pojos;

import java.sql.Timestamp;
import java.util.Currency;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import ch.room4you.entity.Image;
import ch.room4you.entity.User;



/**
 *
 * @author namibrider
 */
public class AdForm {

	private Long id;
	
	private Timestamp timeStampTeamCreation; 
	
	private User user;

	private String title;

	private String description;

	private Date publishedDate;

	private String street;
	
	private int zip;
	
	private String city;
	
	private float nbrRooms;
	
	private Date availableFrom;
	
	private String rentPerMonth;
	
	private String additionalInformation;

	private List<Image> images;
	
	
    
    public Timestamp getTimeStampTeamCreation() {
        return timeStampTeamCreation;
    }

    public void setTimeStampTeamCreation() {
        Date date= new java.util.Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        this.timeStampTeamCreation = timestamp;
    }   
     
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
    
}
