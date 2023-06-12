package br.com.docks.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.docks.api.domain.Management;
import br.com.docks.api.domain.dto.ManagementDto;
import br.com.docks.api.domain.form.ManagementForm;
import br.com.docks.api.domain.form.SetErrorForm;
import br.com.docks.api.domain.form.SetStatusAndErrorForm;
import br.com.docks.api.domain.form.SetStatusForm;
import br.com.docks.api.service.ManagementService;
import br.com.docks.api.service.SupplierService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/management")
public class ManagementController {

	@Autowired
	private ManagementService managementService;

	@Autowired
	private SupplierService supplierService;

	@PostMapping
	@Transactional
	@CacheEvict(value = "list")
	public ResponseEntity<Management> create(@RequestBody @Valid ManagementForm managementForm,
			UriComponentsBuilder uriBuilder) {

		Management management = managementForm.toConvert(supplierService);

		managementService.save(management);
		URI uri = uriBuilder.path("/management/{id}").buildAndExpand(management.getId()).toUri();
		return ResponseEntity.created(uri).body(management);

	}

	@GetMapping
	@Cacheable(value = "list")
	public Page<ManagementDto> list(@RequestParam int pageNumber, @RequestParam int pageQuantity) {

		Pageable pagination = PageRequest.of(pageNumber, pageQuantity);
		Page<Management> managements = managementService.findAll(pagination);
		return ManagementDto.toConvert(managements);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ManagementDto> getById(@PathVariable("id") Long id) {

		return managementService.findById(id).map(management -> ResponseEntity.ok(new ManagementDto(management)))
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

	@PutMapping("/setStatus/{id}")
	@Transactional
	@CacheEvict(value = "list")
	public ResponseEntity<ManagementDto> setStatus(@PathVariable("id") Long id,
			@RequestBody @Valid SetStatusForm setStatusForm) {

		return managementService.findById(id).map(management -> ResponseEntity.ok(setStatusForm.update(management)))
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

	@PutMapping("/setError/{id}")
	@Transactional
	@CacheEvict(value = "list")
	public ResponseEntity<ManagementDto> setError(@PathVariable("id") Long id, @RequestBody SetErrorForm setErrorForm) {

		return managementService.findById(id).map(management -> ResponseEntity.ok(setErrorForm.update(management)))
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

	@PutMapping("setStatusAndError/{id}")
	@Transactional
	@CacheEvict(value = "list")
	public ResponseEntity<ManagementDto> setStatusAndError(@PathVariable("id") Long id,
			@RequestBody SetStatusAndErrorForm setStatusAndErrorForm) {

		return managementService.findById(id)
				.map(management -> ResponseEntity.ok(setStatusAndErrorForm.update(management)))
				.orElseGet(() -> ResponseEntity.notFound().build());

	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "list")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {

		managementService.deleteById(id);
		return ResponseEntity.noContent().build();

	}

}
