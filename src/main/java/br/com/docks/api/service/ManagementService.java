package br.com.docks.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.docks.api.domain.Management;
import br.com.docks.api.repository.ManagementRepository;

@Service
public class ManagementService {

	@Autowired
	private ManagementRepository managementRepository;

	public Optional<Management> findBySupplierCnpj(String cnpj) {
		return managementRepository.findBySupplierCnpj(cnpj);
	}

	public void save(Management management) {
		managementRepository.save(management);
	}

	public Page<Management> findAll(Pageable pagination) {
		return managementRepository.findAll(pagination);
	}

	public Optional<Management> findById(Long id) {
		return managementRepository.findById(id);
	}

	public void deleteById(Long id) {
		managementRepository.deleteById(id);
	}

}
