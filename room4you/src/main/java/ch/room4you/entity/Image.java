package ch.room4you.entity;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;


@Entity
public class Image {
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ad_id")
	private Ad ad;
	
	@Lob
    @Basic(fetch=FetchType.EAGER, optional=true)
    byte[] image;
	
	
    @Transient
    private MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

   


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public byte[] getImage() {
		return image;
	}
	
	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public void setImageFromPath(String filePath) {
	   	 File file = new File(filePath);
	   	 setImage(file);
	}

	public void setImage(File image) {
		byte[] bFile = new byte[(int) image.length()];
	    try {
	    	
		     FileInputStream fileInputStream = new FileInputStream(image);
		     //convert file into array of bytes
		     fileInputStream.read(bFile);
		     fileInputStream.close();
	        } catch (Exception e) {
		     e.printStackTrace();
	        }
		this.image = bFile;
	}
	
	
}
