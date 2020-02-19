package com.arcs.cibus.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arcs.cibus.server.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByLogin(String login);

}
