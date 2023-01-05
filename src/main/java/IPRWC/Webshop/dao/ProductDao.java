package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.Order;
import IPRWC.Webshop.model.Product;
import IPRWC.Webshop.service.ReturnNewIdService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductDao {
    private final ProductRepository productRepository;
    private final ReturnNewIdService returnNewIdService;


    public ProductDao(ProductRepository productRepository, ReturnNewIdService returnNewIdService) {
        this.productRepository = productRepository;
        this.returnNewIdService = returnNewIdService;
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

    public int giveProductNewId() {
        ArrayList<Product> products =
                (ArrayList<Product>) this.productRepository.findAll();
        return this.returnNewIdService.returnNewProductId(products);
    }
}
