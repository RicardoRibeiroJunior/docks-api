package br.com.docks.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.docks.api.domain.Management;

public interface ManagementRepository extends JpaRepository<Management, Long> {
	
	public Optional<Management> findBySupplierCnpj(String cnpj);	

}
