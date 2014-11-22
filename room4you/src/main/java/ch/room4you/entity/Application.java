package ch.room4you.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Application {
	
	
	@Id
	@GeneratedValue
	private int id; 
	
	@OneToOne
	private Appointment appointment;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name = "recipient_id")
	private User recipient;
	
	private String message;
	
	@ManyToOne
	@JoinColumn(name = "applicatonad_id")
	private Ad applicationAd;
	
	@Column(length = 1000)
	private String title;
	
	

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	} 

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
	
	public User getSender() {
		return sender;
	}
	
	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}
	
	public User getRecipient() {
		return recipient;
	}
	
	public void setApplicationAd(Ad applicationAd) {
		this.applicationAd = applicationAd;
	}
	
	public Ad getApplicationAd() {
		return applicationAd;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

}
	
	