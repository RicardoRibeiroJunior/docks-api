package br.com.docks.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.docks.api.domain.User;
import br.com.docks.api.domain.dto.UserDto;
import br.com.docks.api.domain.form.UserForm;
import br.com.docks.api.service.ProfileService;
import br.com.docks.api.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ProfileService profileService;

	@PostMapping
	@Transactional
	@CacheEvict(value = "list")
	public ResponseEntity<UserDto> create(@RequestBody @Valid UserForm userForm, UriComponentsBuilder uriBuilder) {

		User user = userForm.toConvert(profileService);
		userService.save(user);
		URI uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(user));

	}
	
	@GetMapping
	@Cacheable(value = "list")
	public Page<UserDto> list(@RequestParam int pageNumber, @RequestParam int pageQuantity){
		
		Pageable pagination = PageRequest.of(pageNumber, pageQuantity);
		Page<User> users = userService.findAll(pagination);
		return UserDto.toConvert(users);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable("id") Long id){
		
		return userService.findById(id).map(user -> ResponseEntity.ok(new UserDto(user)))
				.orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "list")
	public ResponseEntity<UserDto> update(@PathVariable("id") Long id, @RequestBody @Valid UserForm userForm){
		
		return userService.findById(id).map(user -> ResponseEntity.ok(userForm.update(user)))
				.orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "list")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		
		userService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}


}
