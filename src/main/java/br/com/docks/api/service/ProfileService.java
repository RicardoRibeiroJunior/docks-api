package br.com.docks.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.docks.api.domain.Profile;
import br.com.docks.api.repository.ProfileRepository;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;

	public Profile findByName(String profile) {
		return profileRepository.findByName(profile);
	}

}
