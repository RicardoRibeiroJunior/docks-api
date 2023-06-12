package br.com.docks.api.domain.form;

import java.util.Arrays;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.docks.api.domain.Profile;
import br.com.docks.api.domain.User;
import br.com.docks.api.domain.dto.UserDto;
import br.com.docks.api.service.ProfileService;
import jakarta.validation.constraints.NotEmpty;

public class UserForm {

	@NotEmpty
	private String name;
	@NotEmpty
	private String email;
	@NotEmpty
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public User toConvert(ProfileService profileService) {

		User user = new User();
		user.setName(this.name);
		user.setEmail(this.email);
		user.setPassword(this.password);
		Profile profile = profileService.findByName("ROLE_USER");
		user.setProfile(Arrays.asList(profile));
		return user;
	}

	public UserDto update(User user) {
		
		user.setName(this.name);
		user.setEmail(this.email);
		user.setPassword(this.password);
		UserDto userDto = new UserDto(user);
		return userDto;

	}

}
