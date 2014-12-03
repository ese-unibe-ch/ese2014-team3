package ch.room4you.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.room4you.entity.FavCandidates;
import ch.room4you.entity.User;
import ch.room4you.repository.FavCandidatesRepository;
import ch.room4you.repository.UserRepository;

@Service
public class FavCandidatesService {
	
	@Autowired
	private FavCandidatesRepository candidateRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<FavCandidates> findByAd(int adId) {
		return findByAd(adId);
		
	}

	@Transactional
	public List<FavCandidates> findByAdPlacer(String name) {
		User user = userRepository.findByName(name);
		return candidateRepository.findByAdPlacer(user);
	}

}
