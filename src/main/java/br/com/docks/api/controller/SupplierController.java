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

import br.com.docks.api.domain.Supplier;
import br.com.docks.api.domain.dto.SupplierDto;
import br.com.docks.api.domain.form.SupplierForm;
import br.com.docks.api.service.SupplierService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@PostMapping
	@Transactional
	@CacheEvict(value = "list")
	public ResponseEntity<SupplierDto> create(@RequestBody @Valid SupplierForm supplierForm, UriComponentsBuilder uriBuilder) {

		Supplier supplier = supplierForm.toConvert();
		supplierService.save(supplier);
		URI uri = uriBuilder.path("/supplier/{id}").buildAndExpand(supplier.getId()).toUri();
		return ResponseEntity.created(uri).body(new SupplierDto(supplier));

	}
	
	@GetMapping
	@Cacheable(value = "list")
	public Page<SupplierDto> list(@RequestParam int pageNumber, @RequestParam int pageQuantity){
		
		Pageable pagination = PageRequest.of(pageNumber, pageQuantity);
		Page<Supplier> supplier = supplierService.findAll(pagination);
		return SupplierDto.toConvert(supplier);
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<SupplierDto> getById(@PathVariable("id") Long id){
		
		return supplierService.findById(id).map(supplier -> ResponseEntity.ok(new SupplierDto(supplier)))
				.orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "list")
	public ResponseEntity<SupplierDto> update(@PathVariable("id") Long id, @RequestBody @Valid SupplierForm supplierForm){
		
		return supplierService.findById(id).map(supplier -> ResponseEntity.ok(supplierForm.update(supplier)))
				.orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "list")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id){
		
		supplierService.deleteById(id);
		return ResponseEntity.noContent().build();
		
	}
	
}
