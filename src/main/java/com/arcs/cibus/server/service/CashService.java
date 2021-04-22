package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Cash;
import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.Sale;
import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.domain.enums.SaleStatus;
import com.arcs.cibus.server.domain.enums.TipoSerializer;
import com.arcs.cibus.server.repository.CashRepository;
import com.arcs.cibus.server.repository.SaleRepository;
import com.arcs.cibus.server.service.exceptions.DataException;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class CashService {
	
	@Autowired
	private CashRepository cashRepository;
	
	public Page<Cash> getAll(int page, int quantity, String user, String description, String openDate, String closeDate) throws Exception {
		Pageable paginacao = PageRequest.of(page, quantity);

		if(!openDate.isEmpty()) {
			try {
				new SimpleDateFormat("dd/MM/yyyy").parse(openDate);
			} catch (Exception e) {
				throw new DataException("A data de abertura não está no padrão correto.");
			}
		}

		if(!closeDate.isEmpty()) {
			try {
				new SimpleDateFormat("dd/MM/yyyy").parse(closeDate);
			} catch (Exception e) {
				throw new DataException("A data de fechamento não está no padrão correto.");
			}
		}

		if(description.isEmpty()) description = null;
		if(user.isEmpty()) user = null;
		Date dateInitial = openDate.isEmpty() ? null : new SimpleDateFormat("dd/MM/yyyy").parse(openDate);
		Date dateFinal = closeDate.isEmpty() ? null : new SimpleDateFormat("dd/MM/yyyy").parse(closeDate);

		return cashRepository.findAll(paginacao, user, description, dateInitial, dateFinal);
	}

	public Cash getById(Long cashId) throws ObjectNotFoundException {
		Optional<Cash> cash = cashRepository.findById(cashId);
		return cash.orElseThrow(() -> new ObjectNotFoundException(
				"Caixa não encontrado! Id " + cashId + " não existe."));
	}

	public void delete(Long cashId) throws ConstraintViolationException, Exception {
		Cash cash = this.getById(cashId);
		cashRepository.delete(cash);
	}

	public Cash save(Cash cash) throws Exception {
		return cashRepository.save(cash);
	}

	public List<Cash> getAllOpenCashs() {
		return cashRepository.getAllOpenCashs();
	}
}
