package IPRWC.Webshop.service;

import IPRWC.Webshop.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class IndexOutOfBoundsService {
    public boolean checkIfProductIdIsNotOutOfBounds(ArrayList<Product> products, long id) {
        if (products.isEmpty()) {
            return false;
        }
        for (Product product : products) {
            if (product.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
