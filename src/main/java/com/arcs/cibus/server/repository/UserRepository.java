package com.arcs.cibus.server.repository;

import com.arcs.cibus.server.domain.Notification;
import com.arcs.cibus.server.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arcs.cibus.server.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByLogin(String login);

	@Query("SELECT u FROM cibus_users u "
			+ "WHERE (:name is null or LOWER(u.name) LIKE %:name%) "
			+ "AND (:login is null or LOWER(u.login) LIKE %:login%) "
			+ "ORDER BY u.name"
	)
	Page<User> findAll(Pageable pageable,
						  @Param("name") String name,
						  @Param("login") String login);
}
