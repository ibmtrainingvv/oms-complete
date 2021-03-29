package com.ibm.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ibm.demo.entity.Order;
import com.ibm.demo.repo.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	RestTemplate getTaxesTemplate;

	public String createOrder(Order order) {
		Float response = getTaxesTemplate.getForObject("http://localhost:8080/getTaxes?price={price}", Float.class,
				order.getPrice());
		System.out.println(response);
		Order savedOrder = orderRepository.save(order);
		return savedOrder.getId();
	}

//	public Optional<Order> getOrder(String orderID) {
//		return orderRepository.findById(orderID);
//	}

	public void updateOrder(Order order) {
		orderRepository.save(order);
	}

	public void deleteOrder(String orderID) {
		orderRepository.deleteById(orderID);
	}
//
//	public List<Order> getOrders() {
//		return orderRepository.findAll();
//	}
}
