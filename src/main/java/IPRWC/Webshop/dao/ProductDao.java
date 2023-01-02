package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.Order;
import IPRWC.Webshop.model.Product;

import java.util.ArrayList;

public class ProductDao {
    private final ProductRepository productRepository;


    public ProductDao(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveToDatabase(Product product) {
        this.productRepository.save(product);
    }
    public ArrayList<Product> getAllProducts() {
        return (ArrayList<Product>) this.productRepository.findAll();
    }
    public void deleteProductFromDatabase(Integer id) {
        this.productRepository.deleteById(id);
    }
}
