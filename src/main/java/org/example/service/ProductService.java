package org.example.service;

import org.example.model.Product;
import org.example.repository.ProductRepository;

public class ProductService {
    private final ProductRepository repository;

    public ProductService() {
        repository = new ProductRepository();
    }

    public void cadastrarProduto(Product product){
        if (repository.buscarProduto(product.getId()) == null){
            repository.adicionarProduto(product);
        }else {
            throw new IllegalArgumentException();
        }
    }
    public void atualizaProduto(Integer id, Product product){
        repository.attProduto(id, product);
    }
    public String listarProdutos(){
        return repository.listarProdutos().toString();
    }
    public Product buscarProdutoPorId(Integer id){
        Product product = repository.buscarProduto(id);
        return product;
    }

}
