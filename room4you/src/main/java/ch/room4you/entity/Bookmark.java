package ch.room4you.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Bookmark {
	
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn( name = "bookmarker_id")
	private User bookmarker;
	
	@ManyToOne
	@JoinColumn( name = "bookmarkedAd_id" )
	private Ad bookmarkedAd;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getBookmarker() {
		return bookmarker;
	}

	public void setBookmarker(User bookmarker) {
		this.bookmarker = bookmarker;
	}

	public Ad getBookmarkedAd() {
		return bookmarkedAd;
	}

	public void setBookmarks(Ad bookmarkedAd) {
		this.bookmarkedAd = bookmarkedAd;
	}

	

}
