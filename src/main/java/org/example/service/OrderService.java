package org.example.service;

import org.example.model.Order;
import org.example.model.Product;
import org.example.repository.OrderRepository;

import java.math.BigDecimal;

public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository orderRepository) {
        repository = orderRepository;
    }

    public Order createOrder(Order order){
        return repository.addOrder(order);
    }
    public String showOrder(Integer id){
       return searchById(id).toString();
    }
    public BigDecimal calculateTotalOfOrder(Order order){
        BigDecimal total = BigDecimal.ZERO;
        for (Product product: order.getProductList()){
            total = total.add(product.getPrice());
        }
        return total;
    }

    public Order searchById(Integer id) {
        Order order = repository.searchOrder(id);
        return order;
    }

}
