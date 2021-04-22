package com.arcs.cibus.server.resource;

import com.arcs.cibus.server.domain.Cash;
import com.arcs.cibus.server.domain.Notification;
import com.arcs.cibus.server.service.CashService;
import com.arcs.cibus.server.service.NotificationService;
import com.arcs.cibus.server.service.exceptions.ObjectNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/notification")
public class NotificationResource {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Notification>> getAll(Long userId) throws Exception {
        List<Notification> notification = notificationService.getAll(userId);
        return ResponseEntity.ok(notification);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ResponseEntity<Integer> getQuantityUnread(@PathVariable Long userId) throws ObjectNotFoundException {
        int quantity = notificationService.quantityUnread(userId);
        return ResponseEntity.ok(Integer.parseInt(quantity + ""));
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<String> markAsRead(@PathVariable Long userId) throws ObjectNotFoundException {
        String message = notificationService.markAsRead(userId);
        return ResponseEntity.ok(message);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> markAsRead(@RequestBody List<Long> notificationsId) throws ObjectNotFoundException {
        String message = notificationService.markAsRead(notificationsId);
        return ResponseEntity.ok(message);
    }
}
