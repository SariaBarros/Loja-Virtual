package org.example.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.example.model.Order;
import org.example.model.Product;
import org.example.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @InjectMocks
    OrderService orderService;

    @Mock
    OrderRepository orderRepository;

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
        when(orderRepository.addOrder(order)).thenReturn(order);
        Order orderCreated = orderService.createOrder(order);
        verify(orderRepository).addOrder(order);
        //then: deve ser salvo corretamente
        assertEquals(order, orderCreated);
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
        when(orderRepository.searchOrder(order.getId())).thenReturn(order);
        orderService.createOrder(order);
        String showOrder = orderService.showOrder(0);
        assertEquals(order.toString(), showOrder);

    }
    @Test
    public void naoDeveRetornarPedidoNaoSalvo(){
        when(orderRepository.searchOrder(0)).thenReturn(null);
        assertNull(orderService.searchById(0));
    }

}