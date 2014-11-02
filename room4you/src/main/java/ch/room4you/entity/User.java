package ch.room4you.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;

import ch.room4you.annotation.UniqueUsername;

@Entity
@Table(name = "app_user")
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 3, message = "Name must be at least 3 characters!")
	@Column(unique = true)
	@UniqueUsername(message = "Such username already exists!")
	private String name;

	@Size(min = 1, message = "Invalid email address!")
	@Email(message = "Invalid email address!")
	private String email;

	@Size(min = 5, message = "Name must be at least 5 characters!")
	private String password;

	private boolean enabled;
	
	@Lob
	@Type(type = "org.hibernate.type.StringClobType")
	@Column(length = Integer.MAX_VALUE)
	private String aboutMe;
	
	@Lob
    @Basic(optional=true)
    String image;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy ="sender", cascade = CascadeType.ALL)
	private Message sender;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy ="recipient", cascade = CascadeType.ALL)
	private Message recipient;

	@ManyToMany
	@JoinTable
	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Ad> ads = new ArrayList<Ad>();
	
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Alert> alerts = new ArrayList<Alert>();
	
	@ManyToMany (fetch = FetchType.EAGER)
	private List<Ad> bookmarkedAds = new ArrayList<Ad>();
	

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<Ad> getAds() {
		return ads;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBookmarkedAd(Ad ad) {
		bookmarkedAds.add(ad);
	}

	public boolean isBookmarkedAd(Ad ad) {
		return bookmarkedAds.contains(ad);
	}
	
	public void unBookmarkAd(Ad ad) {
		bookmarkedAds.remove(ad);
	}
	
	public List<Ad> getBookmarkedAds(){
		return bookmarkedAds;
	}



	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public Message getSender() {
		return sender;
	}

	public void setSender(Message sender) {
		this.sender = sender;
	}

	public Message getRecipient() {
		return recipient;
	}

	public void setRecipient(Message recipient) {
		this.recipient = recipient;
	}

	public void setBookmarkedAds(List<Ad> bookmarkedAds) {
		this.bookmarkedAds = bookmarkedAds;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Alert> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}
}
