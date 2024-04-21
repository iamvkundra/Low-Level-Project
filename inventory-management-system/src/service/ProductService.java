package service;

import java.util.List;

import entity.Product;
import repository.ProductRepository;
import repository.Products;

public class ProductService implements Products {

    public Products products;
    public ProductService() {
        products = new ProductRepository();
    }

    @Override
    public boolean addUpdateProduct(Product product) {
        products.addUpdateProduct(product);
        return true;
    }

    @Override
    public void updateProduct() {
    }

    @Override
    public boolean removeProduct(int productId) {
        return products.removeProduct(productId);
    }

    @Override
    public Product getProduct(int productId) {
        return products.getProduct(productId);
    }

    @Override
    public List<Product> getProducts() {
        return products.getProducts();
    }
}
