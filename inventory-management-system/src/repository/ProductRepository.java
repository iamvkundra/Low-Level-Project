package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entity.Product;

public class ProductRepository implements Products {

    private Map<Integer, Product> products;
    public ProductRepository() {
        this.products = new HashMap<>();
    }

    @Override
    public boolean addUpdateProduct(Product product) {
        try {
            products.put(product.getId(), product);
        } catch (Exception e) {
            throw new RuntimeException("Not able to add the product");
        }
        return true;
    }

    @Override
    public void updateProduct() {

    }

    @Override
    public boolean removeProduct(int productId) {
        Product product = products.get(productId);
        if(product == null || product.getCount() == 0) {
            throw new RuntimeException("No product available with productID or product count is 0: " +productId);
        }
        product.setCount(product.getCount() - 1);
        products.put(productId, product);
        return true;
    }

    @Override
    public Product getProduct(int productId) {
        return products.get(productId);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        products.forEach((key, value) ->
                productList.add(value)
        );
        return productList;
    }
}
