package ch.room4you.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "message")
public class Message {
	
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "sender_id")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name = "recipient_id")
	private User recipient;
	
	@Column(length = 2000)
	private String message;
	
	private Timestamp timestamp;
	
	private boolean unRead = true;
	
	@ManyToOne
	@JoinColumn(name = "messagead_id")
	private Ad messageAd;
	
	@Column(length = 1000)
	private String title;
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
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
	
	public void setMessageAd(Ad messageAd) {
		this.messageAd = messageAd;
	}
	
	public Ad getMessageAd() {
		return messageAd;
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

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public void createTimestamp() {
	   java.util.Date date= new java.util.Date();
	   Timestamp timestamp = new Timestamp(date.getTime());
	   this.timestamp = timestamp;
	   
	}

	public boolean isUnRead() {
		return unRead;
	}

	public void setUnRead(boolean read) {
		this.unRead = read;
	}
}