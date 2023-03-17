package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.Product;
import IPRWC.Webshop.service.IndexOutOfBoundsService;
import IPRWC.Webshop.service.SwitchDeleteService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDao {
    private final ProductRepository productRepository;
    private final IndexOutOfBoundsService indexOutOfBoundsService;

    private final SwitchDeleteService switchDeleteService;


    public ProductDao(ProductRepository productRepository,
                      IndexOutOfBoundsService indexOutOfBoundsService,
                      SwitchDeleteService switchDeleteServide) {
        this.productRepository = productRepository;
        this.indexOutOfBoundsService = indexOutOfBoundsService;
        this.switchDeleteService = switchDeleteServide;
    }

    public void saveToDatabase(Product product) {
        this.productRepository.save(product);
    }

    public ArrayList<Product> getAllProducts() {
        return (ArrayList<Product>) this.productRepository.findAll();
    }

    public String switchActiveInDatabase(Integer id) {
        ArrayList<Product> products = (ArrayList<Product>) this.productRepository.findAll();

        Product newProduct = this.switchDeleteService.switchActiveFromProduct(products, id);

        this.productRepository.save(newProduct);

        if (newProduct.isActive()) {
            return "You added some data!";
        } else {
            return "You deleted some data!";
        }
    }

    public boolean isProductNotOutOfBounds(int id) {
        ArrayList<Product> products =
                (ArrayList<Product>) this.productRepository.findAll();
        return this.indexOutOfBoundsService.
                checkIfProductIdIsNotOutOfBounds(products, id);
    }
}
