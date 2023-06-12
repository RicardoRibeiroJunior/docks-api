package br.com.docks.api.domain.dto;

public class TokenDto {

	private String token;
	private String tipo;
	private String userName;

	public TokenDto(String token, String tipo, String name) {
		this.token = token;
		this.tipo = tipo;
		this.userName = name;
	}

	public String getToken() {
		return token;
	}

	public String getTipo() {
		return tipo;
	}

	public String getUserName() {
		return userName;
	}
	
	

}
