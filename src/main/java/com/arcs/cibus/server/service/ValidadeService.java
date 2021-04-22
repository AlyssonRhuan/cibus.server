package com.arcs.cibus.server.service;

import com.arcs.cibus.server.domain.Notification;
import com.arcs.cibus.server.domain.Product;
import com.arcs.cibus.server.domain.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Date;


@Service
public class ValidadeService {

	@Autowired
	private ProductService productService;

	@Autowired
	private NotificationService notificationService;

	private String STOCK_MESSAGE = "O produto %s está com o estoque abaixo do valor minimo.";

	public void validateStock(Sale sale) throws Exception {
		Product product = productService.getById(sale.getProduct().getId());
		product.setStockQuantity(product.getStockQuantity() - sale.getQuantity().intValue());
		if(product.isInStockMinimum()) this.notify(product);
		productService.save(product);
	}

	public void notify(Product product) throws Exception {
		String message = String.format(STOCK_MESSAGE, product.getName());
		Notification notification = Notification
				.builder()
				.notification(message)
				.date(new Date())
				.build();
		notificationService.notifyAllAdmins(notification);
	}
}
