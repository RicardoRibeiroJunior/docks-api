package br.com.docks.api.domain.form;

import br.com.docks.api.domain.Management;
import br.com.docks.api.domain.Status;
import br.com.docks.api.domain.dto.ManagementDto;
import jakarta.validation.constraints.NotEmpty;

public class SetStatusForm {
	
	@NotEmpty
	private String status;

	

	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
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
			
			
		ManagementDto managementDto = new ManagementDto(management);
		
		return managementDto;
	}
	
	

}
