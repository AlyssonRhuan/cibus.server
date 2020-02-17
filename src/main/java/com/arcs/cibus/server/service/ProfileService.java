package com.arcs.cibus.server.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.Profile;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.repository.ProfileRepository;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;


@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	public Page<Profile> getAll(int pagina, int qtdElementos) throws Exception {
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);
		return profileRepository.findAll(paginacao);
	}
	
	public Profile getById(Long perfilId) throws ObjectNotFoundException {
		Optional<Profile> perfil = profileRepository.findById(perfilId);		
		return perfil.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id " + perfilId + " não existe."));
	}
	
	public void delete(Long perfilId) throws ConstraintViolationException, Exception {	
		Profile perfil = this.getById(perfilId);
		profileRepository.delete(perfil);
	}
	
	public Profile save(Profile perfil) throws Exception {
		return profileRepository.save(perfil); 
	}

	public List<Profile> getAllValueLabel() {
		List<Profile> profilesValueLabel = profileRepository.findAll();
		
		for (Profile profile : profilesValueLabel) {
			profile.setTipoSerializer(TipoSerializer.VALUELABEL);
		}
		
		return profilesValueLabel;
	}
}
