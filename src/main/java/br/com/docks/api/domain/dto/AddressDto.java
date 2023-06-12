package br.com.docks.api.domain.dto;

import org.springframework.data.domain.Page;

import br.com.docks.api.domain.Address;

public class AddressDto {

	private String street;
	private String neighborhood;
	private String cep;
	private String uf;
	private String city;
	private String number;
	private String complement;

	public AddressDto(Address address) {

		this.street = address.getStreet();
		this.neighborhood = address.getNeighborhood();
		this.cep = address.getCep();
		this.uf = address.getUf();
		this.city = address.getCity();
		this.number = address.getNumber();
		this.complement = address.getComplement();

	}

	public String getStreet() {
		return street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public String getCep() {
		return cep;
	}

	public String getUf() {
		return uf;
	}

	public String getCity() {
		return city;
	}

	public String getNumber() {
		return number;
	}

	public String getComplement() {
		return complement;
	}

	public static Page<AddressDto> toConvert(Page<Address> address) {
		return address.map(AddressDto::new);
	}

}
