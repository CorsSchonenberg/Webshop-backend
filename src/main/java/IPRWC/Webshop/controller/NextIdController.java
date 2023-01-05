package IPRWC.Webshop.controller;

import IPRWC.Webshop.dao.OrderDao;
import IPRWC.Webshop.dao.ProductDao;
import IPRWC.Webshop.dao.PromoCodeDao;
import IPRWC.Webshop.dao.UserDao;
import IPRWC.Webshop.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/nextid")
@CrossOrigin("*")
public class NextIdController {
    private final UserDao userDao;
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final PromoCodeDao promoCodeDao;

    public NextIdController(UserDao userDao,
                            OrderDao orderDao,
                            ProductDao productDao,
                            PromoCodeDao promoCodeDao) {
        this.userDao = userDao;
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.promoCodeDao = promoCodeDao;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<User>> getNewUserId() {
        int nextId = this.userDao.giveUserNewId();
        return new ApiResponse(HttpStatus.ACCEPTED, nextId);
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Order>> getNewOrderId() {
        int nextId = this.orderDao.giveOrderNewId();
        return new ApiResponse(HttpStatus.ACCEPTED, nextId);
    }

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Product>> getNewProductId() {
        int nextId = this.productDao.giveProductNewId();
        return new ApiResponse(HttpStatus.ACCEPTED, nextId);
    }

    @RequestMapping(value = "/promocode", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<PromoCode>> getNewPromoCodeId() {
        int nextId = this.promoCodeDao.givePromoCodeNewId();
        return new ApiResponse(HttpStatus.ACCEPTED, nextId);
    }
}
