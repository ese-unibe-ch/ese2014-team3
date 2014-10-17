package ch.room4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	User findByName(String name);


}
