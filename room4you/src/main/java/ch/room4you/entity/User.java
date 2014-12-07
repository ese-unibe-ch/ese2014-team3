package ch.room4you.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import ch.room4you.entity.FavCandidates;

@Entity
@Table(name = "app_user")
public class User  {

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
	
	@OneToMany(mappedBy ="sender", cascade = CascadeType.ALL)
	private List<Message> sentMessages = new ArrayList<Message>();
	
	@OneToMany(mappedBy ="recipient", cascade = CascadeType.ALL)
	private List<Message> messages = new ArrayList<Message>();
	
	@ManyToMany(mappedBy = "visitors", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Appointment> appointments = new ArrayList<Appointment>();
	
//	@ManyToMany(mappedBy = "promisingCandidates",fetch = FetchType.EAGER)
//	private List<Appointment> appointmentPromisingCandidates = new ArrayList<Appointment>();
	
	@ManyToMany(mappedBy = "visitors", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private Set<FavCandidates> favCandidates;
	

	@ManyToMany
	@JoinTable
	private List<Role> roles;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
	private List<Ad> ads = new ArrayList<Ad>();
	
	@OneToMany(mappedBy = "user", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Alert> alerts = new ArrayList<Alert>();
	
	@OneToMany (mappedBy = "bookmarker", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Bookmark> bookmarkedAds = new ArrayList<Bookmark>();
	
	
	

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

/*	public void setBookmarkedAd(Bookmark bookmark) {
		bookmarkedAds.add(bookmark);
	}
*/
	
	public List<Bookmark> getBookmarkedAds(){
		return bookmarkedAds;
	}


	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public List<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public void setBookmarkedAds(List<Bookmark> bookmarkedAds) {
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

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointment(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	public void addAppointment(Appointment appointment) {
		this.appointments.add(appointment);
	}

	public Set<FavCandidates> getFavCandidates() {
		return favCandidates;
	}

	public void setFavCandidates(Set<FavCandidates> favCand) {
		this.favCandidates = favCand;
	}

	
	
	

}
