package org.example.repository;

import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    List<Product> repository = new ArrayList<>();
    public void adicionarProduto(Product product){
        repository.add(product);
    }
    public void attProduto(Integer id, Product product){
        Integer index = 0;
        Integer attIndex = 0;

        if (buscarProduto(id) == null){
            throw new IllegalArgumentException();
        }

        for (Product productAux: repository){
            if (productAux.getId() == id){
                attIndex = index;
            }
            index++;
        }
        repository.remove(attIndex);
        repository.add(attIndex, product);
    }
    public List<Product> listarProdutos(){
        return repository;
    }
    public Product buscarProduto(Integer id){
        for (Product product: repository){
            if (product.getId() == id){
                return product;
            }
        }
        return null;
    }
}
