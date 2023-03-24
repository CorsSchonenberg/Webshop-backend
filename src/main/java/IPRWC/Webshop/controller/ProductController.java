package IPRWC.Webshop.controller;

import IPRWC.Webshop.dao.ProductDao;
import IPRWC.Webshop.dao.UserDao;
import IPRWC.Webshop.model.ApiResponse;
import IPRWC.Webshop.model.Order;
import IPRWC.Webshop.model.Product;
import IPRWC.Webshop.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/api/v1/product")
@CrossOrigin("*")
public class ProductController {
    private final ProductDao productDao;
    private final UserDao userDao;

    public ProductController(ProductDao productDao, UserDao userDao) {
        this.productDao = productDao;
        this.userDao = userDao;
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
        String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByEmail(email).get();
        if (!this.userDao.isUserAdmin(user)){
            return new ApiResponse(HttpStatus.UNAUTHORIZED, "Only Admins Are Allowed to add new products");
        }
        this.productDao.saveToDatabase(product);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse deleteProduct(@PathVariable Integer id) {
        String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByEmail(email).get();
        if (!this.userDao.isUserAdmin(user)){
            return new ApiResponse(HttpStatus.UNAUTHORIZED, "Only Admins Are Allowed to delete products");
        }
        if (this.productDao.isProductNotOutOfBounds(id)) {
            String responseMessage =this.productDao.switchActiveInDatabase(id);
            return new ApiResponse(HttpStatus.ACCEPTED, responseMessage);
        } else {
            return new ApiResponse(HttpStatus.BAD_REQUEST, "Index out of bounds");
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse updateProduct(@RequestBody Product product) {
        String email = (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findByEmail(email).get();
        if (!this.userDao.isUserAdmin(user)){
            return new ApiResponse(HttpStatus.UNAUTHORIZED, "Only Admins Are Allowed to update products");
        }
        this.productDao.saveToDatabase(product);
        return new ApiResponse(HttpStatus.ACCEPTED, "You updated some data!");
    }
}