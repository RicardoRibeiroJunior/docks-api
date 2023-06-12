package br.com.docks.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.docks.api.domain.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	public Supplier findByCnpj(String cnpj);

}
