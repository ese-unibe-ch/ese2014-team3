package ch.room4you.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.Ad;
import ch.room4you.entity.FavCandidates;
import ch.room4you.entity.User;


public interface FavCandidatesRepository extends JpaRepository<FavCandidates, Integer> {

	List<FavCandidates> findByAd(Ad ad);

	List<FavCandidates> findByAdPlacer(User user);
	
}
