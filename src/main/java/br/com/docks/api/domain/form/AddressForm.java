package br.com.docks.api.domain.form;

import br.com.docks.api.domain.Address;
import br.com.docks.api.domain.dto.AddressDto;

public class AddressForm {

	private String street;
	private String neighborhood;
	private String cep;
	private String uf;
	private String city;
	private String number;
	private String complement;

	public void setStreet(String street) {
		this.street = street;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setComplement(String complement) {
		this.complement = complement;
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

	public Address toConvert() {

		Address address = new Address();

		address.setStreet(this.street);
		address.setNeighborhood(this.neighborhood);
		address.setCep(this.cep);
		address.setUf(this.uf);
		address.setCity(this.city);
		address.setNumber(this.number);
		address.setComplement(this.complement);

		return address;

	}

	public AddressDto update(Address address) {

		address.setStreet(this.street);
		address.setNeighborhood(this.neighborhood);
		address.setCep(this.cep);
		address.setUf(this.uf);
		address.setCity(this.city);
		address.setNumber(this.number);
		address.setComplement(this.complement);

		AddressDto addressDto = new AddressDto(address);

		return addressDto;

	}

}
