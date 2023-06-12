package br.com.docks.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.docks.api.domain.Supplier;
import br.com.docks.api.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public void save(Supplier supplier) {
		supplierRepository.save(supplier);
	}

	public Page<Supplier> findAll(Pageable pagination) {
		return supplierRepository.findAll(pagination);
	}

	public Optional<Supplier> findById(Long id) {
		return supplierRepository.findById(id);
	}

	public void deleteById(Long id) {
		supplierRepository.deleteById(id);
	}

	public Supplier findByCnpj(String cnpj) {
		return supplierRepository.findByCnpj(cnpj);
	}

}
