package ch.room4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.FavCandidates;


public interface FavCandidatesRepository extends JpaRepository<FavCandidates, Integer> {

	
}
