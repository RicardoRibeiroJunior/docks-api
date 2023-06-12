package br.com.docks.api.domain.form;

import br.com.docks.api.domain.Address;
import br.com.docks.api.domain.Supplier;
import br.com.docks.api.domain.dto.SupplierDto;
import jakarta.validation.constraints.NotEmpty;

public class SupplierForm {
	
	@NotEmpty
	private String name;
	@NotEmpty
	private String cnpj;
	@NotEmpty
	private String branch;
	@NotEmpty
	private String phone;
	private AddressForm addressForm;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AddressForm getAddressForm() {
		return addressForm;
	}

	public void setAddressForm(AddressForm addressForm) {
		this.addressForm = addressForm;
	}

	public Supplier toConvert() {

		Address address = addressForm.toConvert();

		Supplier supplier = new Supplier();
		supplier.setName(this.name);
		supplier.setCnpj(this.cnpj);
		supplier.setBranch(this.branch);
		supplier.setPhone(this.phone);
		supplier.setAddress(address);

		return supplier;
	}

	public SupplierDto update(Supplier supplier) {

		supplier.setName(this.name);
		supplier.setCnpj(this.cnpj);
		supplier.setBranch(this.branch);
		supplier.setPhone(this.phone);
		supplier.setAddress(this.addressForm.toConvert());

		SupplierDto supplierDto = new SupplierDto(supplier);

		return supplierDto;
	}

}
