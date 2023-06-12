package br.com.docks.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.docks.api.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

	Profile findByName(String profile);

}
