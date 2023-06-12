package br.com.docks.api.domain.dto;

import org.springframework.data.domain.Page;

import br.com.docks.api.domain.Supplier;

public class SupplierDto {

	private Long id;
	private String name;
	private String cnpj;
	private String branch;
	private String phone;
	private AddressDto addressDto;

	public SupplierDto(Supplier supplier) {

		this.id = supplier.getId();
		this.name = supplier.getName();
		this.cnpj = supplier.getCnpj();
		this.branch = supplier.getBranch();
		this.phone = supplier.getPhone();

		AddressDto addressDto = new AddressDto(supplier.getAddress());
		this.addressDto = addressDto;

	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getBranch() {
		return branch;
	}

	public String getPhone() {
		return phone;
	}

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public static Page<SupplierDto> toConvert(Page<Supplier> supplier) {
		return supplier.map(SupplierDto::new);
	}

}
