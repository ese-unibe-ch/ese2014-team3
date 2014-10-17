package ch.room4you.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Image;
import ch.room4you.repository.ImageRepository;

@Service
public class ImageService {
	@Autowired
	private ImageRepository imageRepository;

	public List<Image> getImages() {
		return imageRepository.findAll();
	}
	
	public void save(Image image) {
		imageRepository.save(image);
	}
}
