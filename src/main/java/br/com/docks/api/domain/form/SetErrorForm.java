package br.com.docks.api.domain.form;

import br.com.docks.api.domain.Management;
import br.com.docks.api.domain.dto.ManagementDto;
import jakarta.validation.constraints.NotEmpty;

public class SetErrorForm {
	
	@NotEmpty
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public ManagementDto update(Management management) {
		management.setError(this.error);
		ManagementDto managementDto = new ManagementDto(management);
		return managementDto;
	}

}
