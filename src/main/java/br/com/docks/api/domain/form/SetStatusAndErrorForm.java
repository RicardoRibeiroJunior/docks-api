package br.com.docks.api.domain.form;

import br.com.docks.api.domain.Management;
import br.com.docks.api.domain.Status;
import br.com.docks.api.domain.dto.ManagementDto;

public class SetStatusAndErrorForm {

	private String status;
	private String error;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public ManagementDto update(Management management) {
		
		if(this.status.equals("RECEPCIONADO")) {
			management.setStatus(Status.RECEPCIONADO);
		}
		
		if(this.status.equals("AGUARD_RECEBIMENTO")){
			management.setStatus(Status.AGUARD_RECEBIMENTO);
		}
		
		if(this.status.equals("ERRO")){
			management.setStatus(Status.ERRO);
		}
		
		if(this.status.equals("FINALIZADO")){
			management.setStatus(Status.FINALIZADO);
		}
		
		management.setError(this.error);
		ManagementDto managementDto = new ManagementDto(management);
		return managementDto;
	}

}
