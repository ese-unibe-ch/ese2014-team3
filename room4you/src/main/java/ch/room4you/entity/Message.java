package ch.room4you.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "message")
public class Message {
	
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@OneToOne (fetch = FetchType.LAZY)
	private User sender;
	
	@OneToOne (fetch = FetchType.LAZY)
	private User recipient;
	
	private String message;
	
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