package ch.room4you.entity;

import java.io.File;
import java.util.ArrayList;
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


	@Lob
	@Column(name="ROOM_IMAGE", nullable=false, columnDefinition="mediumblob")
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


	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
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
}
