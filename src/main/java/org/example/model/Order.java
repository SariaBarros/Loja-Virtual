package org.example.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private Integer id;
    private List<Product> productList;
    private BigDecimal total;

    public Order(Integer id, List<Product> productList) {
        this.id = id;
        this.productList = productList;
        this.total = BigDecimal.ZERO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productList=" + productList +
                ", total=" + total +
                '}';
    }
}
