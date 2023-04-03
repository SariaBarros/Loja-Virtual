package org.example.service;
import static org.junit.jupiter.api.Assertions.*;
import org.example.model.Order;
import org.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    static OrderService orderService;
    @BeforeEach
    public void setup(){
        orderService = new OrderService();
    }
    @Test
    void deveCriarPedido() {
        //given: um pedido
        Product product01 = new Product(0, "Produto 00");
        Product product02 = new Product(1, "Produto 01");
        List<Product> listaProdutos = new ArrayList<>();
        listaProdutos.add(product01);
        listaProdutos.add(product02);
        Order order = new Order(0, listaProdutos);
        //when: criado
        orderService.createOrder(order);
        //then: deve ser salvo corretamente
        assertEquals(order, orderService.searchById(0));
    }

    @Test
    public void deveRetornarTotalDoPedido(){
        //given: um pedido
        Product product01 = new Product(0, "Produto 00", BigDecimal.valueOf(100));
        Product product02 = new Product(1, "Produto 01", BigDecimal.valueOf(55.50));
        List<Product> listaProdutos = new ArrayList<>();
        listaProdutos.add(product01);
        listaProdutos.add(product02);
        Order order = new Order(0, listaProdutos);


        //when: calculado seu total
        BigDecimal totalService = orderService.calculateTotalOfOrder(order);
        BigDecimal total = BigDecimal.valueOf(100 + 55.50);
        //then: deve retornar o valor  certo
        assertEquals(total, totalService);
    }

    @Test
    public void deveMostrarPedido(){
        //given: um pedido
        Product product01 = new Product(0, "Produto 00");
        Product product02 = new Product(1, "Produto 01");
        List<Product> listaProdutos = new ArrayList<>();
        listaProdutos.add(product01);
        listaProdutos.add(product02);
        Order order = new Order(0, listaProdutos);
        orderService.createOrder(order);
        String showOrder = orderService.showOrder(0);
        assertEquals(order.toString(), showOrder);

    }
    @Test
    public void naoDeveRetornarPedidoNaoSalvo(){
        assertNull(orderService.searchById(0));
    }

}