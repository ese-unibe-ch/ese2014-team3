package ch.room4you.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.room4you.entity.Ad;
import ch.room4you.entity.FavCandidates;
import ch.room4you.entity.User;
import ch.room4you.repository.AdRepository;
import ch.room4you.repository.FavCandidatesRepository;
import ch.room4you.repository.UserRepository;

@Service
public class FavCandidatesService {

	@Autowired
	private FavCandidatesRepository candidateRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdRepository adRepository;

	@Transactional
	public List<FavCandidates> findByAd(int adId) {
		return findByAd(adId);

	}

	@Transactional
	public List<FavCandidates> findByAdPlacer(String name) {
		User user = userRepository.findByName(name);
		return candidateRepository.findByAdPlacer(user);
	}

	/**
	 * creates a list of favorite visitors the adplacer has selected.
	 * 
	 * @param candidatesId
	 * @param adId
	 * @param userName
	 *            , the adplacer's name
	 */
	@Transactional
	public void createFavList(List<Integer> candidatesId, int adId,
			String userName) {
		FavCandidates favCandidates = new FavCandidates();
		Ad ad = adRepository.findOne(adId);
		User adPlacer = userRepository.findByName(userName);

		favCandidates.setAd(ad);
		favCandidates.setAdPlacer(adPlacer);
		favCandidates.setVisitors(intToUser(candidatesId));
		candidateRepository.save(favCandidates);

		Set<FavCandidates> favCand = adPlacer.getFavCandidates();
		favCand.add(favCandidates);
		adPlacer.setFavCandidates(favCand);
		userRepository.save(adPlacer);

		Set<FavCandidates> adFavCand = ad.getFavCandidates();
		adFavCand.add(favCandidates);
		ad.setFavCandidates(adFavCand);
		adRepository.save(ad);
	}

	/**
	 * This method receives a list of user id's and returns a list of users
	 * (visitors) to be added to favorites
	 * 
	 * @param candidatesId
	 *            List of id's from visitors
	 * @return List of users
	 */

	@Transactional
	public List<User> intToUser(List<Integer> candidatesId) {
		List<User> favVisitors = new ArrayList<User>();
		for (Integer number : candidatesId) {
			favVisitors.add(userRepository.findOne(number));
		}
		return favVisitors;
	}

	@Transactional
	public void delete(int listId) {
		FavCandidates favCand = candidateRepository.findOne(listId);
		Ad ad = favCand.getAd();
	
		Set<FavCandidates> adFavCand = ad.getFavCandidates();
		adFavCand.remove(favCand);
		ad.setFavCandidates(adFavCand);
		adRepository.save(ad);
		
		candidateRepository.delete(listId);

	}

}
