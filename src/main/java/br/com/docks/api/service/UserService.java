package br.com.docks.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.docks.api.domain.User;
import br.com.docks.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void save(User usuario) {		
		userRepository.save(usuario);		
	}

	public Page<User> findAll(Pageable pagination) {
		return userRepository.findAll(pagination);
	}

	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}

	public UserDetails findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	

}
