package repository;

import java.util.List;

import entity.Product;

public interface Products {
    boolean addUpdateProduct(Product product);

    void updateProduct();

    boolean removeProduct(int productId);

    Product getProduct(int productId);

    List<Product> getProducts();
}
