import entity.Product;
import service.ProductService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Simple Inventory System");
        ProductService products = new ProductService();

        // Add Products
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("xyz");
        p1.setCount(10);
        p1.setAmount(1000);

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("abc");
        p2.setCount(10);
        p2.setAmount(1000);

        products.addUpdateProduct(p2);
        products.addUpdateProduct(p1);

        System.out.println(products.getProducts());

        //remove single product count

        products.removeProduct(2);

        System.out.println(products.getProducts());

    }
}