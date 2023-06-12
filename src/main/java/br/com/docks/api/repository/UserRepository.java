package br.com.docks.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.docks.api.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	UserDetails findByEmail(String email);

}
