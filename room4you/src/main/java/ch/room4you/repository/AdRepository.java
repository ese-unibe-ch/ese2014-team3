package ch.room4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.Ad;
import ch.room4you.entity.User;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {

	List<Ad> findByUser(User user);
}
