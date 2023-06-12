package br.com.docks.api.domain.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.docks.api.domain.Management;
import br.com.docks.api.domain.Status;

public class ManagementDto {

	private Long id;
	private SupplierDto supplierDto;
	private Status status;
	private LocalDateTime arrivalTime;
	private LocalDateTime exitTime;
	private String error;

	public ManagementDto(Management management) {
		SupplierDto supplierDto = new SupplierDto(management.getSupplier());
		this.id = management.getId();
		this.supplierDto = supplierDto;
		this.status = management.getStatus();
		this.arrivalTime = management.getArrivalTime();
		this.exitTime = management.getExitTime();
		this.error = management.getError();

	}

	public Long getId() {
		return id;
	}

	public SupplierDto getSupplierDto() {
		return supplierDto;
	}

	public Status getStatus() {
		return status;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public String getError() {
		return error;
	}

	public static Page<ManagementDto> toConvert(Page<Management> managements) {
		return managements.map(ManagementDto::new);
	}

}
