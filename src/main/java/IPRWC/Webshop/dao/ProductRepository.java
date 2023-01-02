package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}