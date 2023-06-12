package br.com.docks.api.domain.form;

import br.com.docks.api.domain.Management;
import br.com.docks.api.domain.Supplier;
import br.com.docks.api.service.SupplierService;
import jakarta.validation.constraints.NotEmpty;

public class ManagementForm {
	
	@NotEmpty
	private String chaveDeAcessoDaNotaFiscal;

	public String getChaveDeAcessoDaNotaFiscal() {
		return chaveDeAcessoDaNotaFiscal;
	}

	public void setChaveDeAcessoDaNotaFiscal(String chaveDeAcessoDaNotaFiscal) {
		this.chaveDeAcessoDaNotaFiscal = chaveDeAcessoDaNotaFiscal;
	}

	public Management toConvert(SupplierService supplierService) {

		String cnpj = chaveDeAcessoDaNotaFiscal.substring(6, 20);

		Supplier supplier = supplierService.findByCnpj(cnpj);

		Management management = new Management();
		management.setSupplier(supplier);

		return management;
	}

}
