package org.example.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.example.model.Product;
import org.example.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductRepositoryTest {
    static ProductRepository productRepository;
    Product product;

    @BeforeEach
    public void setup(){
        productRepository = new ProductRepository();
        product = new Product();
    }

    @Test
    public void deveAtualizarCorretamente(){
        //given : lista de produtos com mais de um produto
        Product product0 = new Product(0, "Produto 0");
        Product product1 = new Product(1, "Produto 1");
        Product product2 = new Product(2, "Produto 2");
        productRepository.adicionarProduto(product0);
        productRepository.adicionarProduto(product1);
        productRepository.adicionarProduto(product2);
        //when: atualizado um produto
        Product product1Att = new Product(1, "Produto 1 atualizado");
        productRepository.attProduto(1, product1Att);
        //then: o produto deve estar no índice correto
        assertEquals(product1Att, productRepository.buscarProduto(1));
    }
    //Testar exception na attProduto
    @Test
    public void deveRetornarNullQuandoNaoAcharId(){
        assertNull(productRepository.buscarProduto(0));
    }
}
