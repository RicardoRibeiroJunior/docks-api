package br.com.docks.api.domain.dto;

import org.springframework.data.domain.Page;

import br.com.docks.api.domain.User;

public class UserDto {

	private Long id;
	private String name;
	private String email;

	public UserDto(User usuario) {
		this.id = usuario.getId();
		this.name = usuario.getName();
		this.email = usuario.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public static Page<UserDto> toConvert(Page<User> users) {
		return users.map(UserDto::new);
	}

}
