package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Cash;
import com.arcs.cibus.server.domain.Notification;
import com.arcs.cibus.server.domain.User;
import com.arcs.cibus.server.repository.CashRepository;
import com.arcs.cibus.server.repository.NotificationRepository;
import com.arcs.cibus.server.service.exceptions.DataException;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.aspectj.weaver.ast.Not;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;

	@Autowired
	private UserService userService;
	
	public List<Notification> getAll(Long userId) throws Exception {
		return notificationRepository.findAllOrdered(userId);
	}

	public Notification save(Notification notification) throws Exception {
		return notificationRepository.save(notification);
	}

	public int quantityUnread(Long userId) {
		return notificationRepository.countNotificationsUnread(userId);
	}

	public String markAsRead(List<Long> notificationsId) {
		int quantity = 0;
		for(Long notificationId : notificationsId){
			Notification notification = notificationRepository.findById(notificationId).get();
			notificationRepository.delete(notification);
			quantity++;
		}

		return quantity + (quantity > 1 ? " notificações atualizadas." : " notificação atualizada.");
	}

	public String markAsRead(Long userId) {
		List<Notification> notifications =  notificationRepository.findAllOrdered(userId);
		List<Long> notificationsId = new ArrayList<>();
		for(Notification notification : notifications){
			notificationsId.add(notification.getId());
		}
		return markAsRead(notificationsId);
	}

	public void notifyAllAdmins(Notification notification) throws Exception {
		User user = userService.getById(1L);
//		List<User> admins = userService.getAllAdmins();
//		for(User user : admins){
			notification.setUser(user);
			save(notification);
//		}
	}
}
