package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.Product;
import IPRWC.Webshop.service.IndexOutOfBoundsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductDao {
    private final ProductRepository productRepository;
    private final IndexOutOfBoundsService indexOutOfBoundsService;


    public ProductDao(ProductRepository productRepository, IndexOutOfBoundsService indexOutOfBoundsService) {
        this.productRepository = productRepository;
        this.indexOutOfBoundsService = indexOutOfBoundsService;
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

    public boolean isProductNotOutOfBounds(int id) {
        ArrayList<Product> products =
                (ArrayList<Product>) this.productRepository.findAll();
        return this.indexOutOfBoundsService.
                checkIfProductIdIsNotOutOfBounds(products, id);
    }
}
