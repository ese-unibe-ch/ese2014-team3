package ch.room4you.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.room4you.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Role findByName(String name);

}
