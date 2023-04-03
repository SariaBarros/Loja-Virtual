package org.example.repository;

import org.example.model.Order;
import org.example.model.Product;
import org.example.service.OrderService;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    List<Order> repository = new ArrayList<>();

    public Order addOrder(Order order){
        repository.add(order);
        return order;
    }
    public Order searchOrder (Integer id){
        for (Order order: repository){
            if (order.getId() == id){
                return order;
            }
        }
        return null;
    }

}
