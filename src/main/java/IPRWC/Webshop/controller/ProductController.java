package IPRWC.Webshop.controller;

import IPRWC.Webshop.dao.OrderDao;
import IPRWC.Webshop.dao.ProductDao;
import IPRWC.Webshop.dao.ProductRepository;
import IPRWC.Webshop.model.ApiResponse;
import IPRWC.Webshop.model.Order;
import IPRWC.Webshop.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/api/v1/product")
@CrossOrigin("*")
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao,
                             ProductRepository productRepository) {
        this.productDao = productDao;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Order>> products() {
        ArrayList<Product> products = this.productDao.getAllProducts();
        return new ApiResponse(HttpStatus.ACCEPTED, products);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postProducts(@RequestBody Product product) {
        this.productDao.saveToDatabase(product);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse deleteProduct(@PathVariable Integer id) {
        if (this.productDao.isProductNotOutOfBounds(id)) {
            this.productDao.deleteProductFromDatabase(id);
            return new ApiResponse(HttpStatus.ACCEPTED, "You deleted some data!");
        } else {
            return new ApiResponse(HttpStatus.BAD_REQUEST, "Index out of bounds");
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse updateProduct(@RequestBody Product product) {
        this.productDao.saveToDatabase(product);
        return new ApiResponse(HttpStatus.ACCEPTED, "You updated some data!");
    }
}