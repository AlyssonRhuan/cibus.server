package com.arcs.cibus.server.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.repository.UserRepository;
import com.arcs.cibus.server.service.exceptions.EmailAlreadyRegisteredException;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.springframework.util.StringUtils;


@Service
public class UserService {
	
	@Autowired
	private UserRepository usuarioRepository;
	
	public Page<User> getAll(int pagina, int qtdElementos, String name, String login) throws Exception {
		Pageable paginacao = PageRequest.of(pagina, qtdElementos);

		name = name.isEmpty() ? null : name.toLowerCase(Locale.ROOT);
		login = login.isEmpty() ? null : login.toLowerCase(Locale.ROOT);

		return usuarioRepository.findAll(paginacao, name, login);
	}
	
	public User getById(Long usuarioId) throws ObjectNotFoundException {
		Optional<User> usuario = usuarioRepository.findById(usuarioId);		
		return usuario.orElseThrow(() -> new ObjectNotFoundException(
				"User not found!"));
	}
	
	public User getByEmail(String email) throws ObjectNotFoundException {
		return usuarioRepository.findByLogin(email);
	}
	
	public void delete(Long userId) throws ConstraintViolationException, Exception {	
		User user = this.getById(userId);
		usuarioRepository.delete(user);
	}
	
	public User save(User user) throws Exception {
		if(user.getId() == null) {
			User validateUser = getByEmail(user.getLogin());
			
			if(validateUser != null) {
				throw new EmailAlreadyRegisteredException("Este login já foi registrado!");
			}			
		}

		if(StringUtils.isEmpty(user.getPass())) {
			User oldUser = getById(user.getId());
			user.setPass(oldUser.getPass());
		}
		
		return usuarioRepository.save(user); 
	}

    public List<User> getAllAdmins() {
		return null;
    }
}
