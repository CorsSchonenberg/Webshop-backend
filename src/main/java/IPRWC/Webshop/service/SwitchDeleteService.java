package IPRWC.Webshop.service;

import IPRWC.Webshop.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SwitchDeleteService {

    public Product switchActiveFromProduct(ArrayList<Product> products, Integer id){
        for (Product product : products) {
            if (product.getId().equals(id)) {
                product.setActive(!product.isActive());
                return product;
            }
        }
        return null;

    }
}
