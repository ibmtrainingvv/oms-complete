package com.ibm.demo;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.demo.entity.Order;
import com.ibm.demo.service.OrderService;

@RestController
public class OrderController {
	@Autowired
	OrderService orderService;

	@PostMapping("/order")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	String createOrder(@RequestBody @Valid Order order, BindingResult bindingResult) {
		validateModel(bindingResult);
		System.out.println(order);
		return orderService.createOrder(order);
	}

	private void validateModel(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("parameters absent");
		}
	}

	/**
	 * method to search for an order
	 * 
	 * @param orderId
	 * @return zero or matching order
	 */
//	@GetMapping("/order/{id}")
//	Optional<Order> getOrder(@PathVariable("id") String orderID) {
//		return orderService.getOrder(orderID);
//	}
//
//	@GetMapping("/order/")
//	List<Order> getOrders() {
//		return orderService.getOrders();
//
//	}

	@PutMapping("/order/{id}")
	void updateOrder(@RequestBody @Valid Order order, BindingResult bindingResult, @PathVariable("id") String orderID) {
		validateModel(bindingResult);
		order.setId(orderID);
		orderService.updateOrder(order);
	}

	@DeleteMapping("/order/{id}")

	void deleteOrder(@PathVariable("id") String orderID) {
		System.out.println(orderID);
		orderService.deleteOrder(orderID);
	}

}
