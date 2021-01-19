package com.stackroute.foodieapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stackroute.foodieapp.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	User findUserByEmailAndPassword(String email, String password);
	
	User findUserByEmail(String email);

	@Modifying(clearAutomatically=true)
	@Transactional
	@Query(value = "update User u set u.password = ?1 where u.email = ?2")
	void setPasswordFor(String password, String email);
}
