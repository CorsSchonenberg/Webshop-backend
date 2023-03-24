package IPRWC.Webshop.controller;

import IPRWC.Webshop.dao.OrderDao;
import IPRWC.Webshop.model.ApiResponse;
import IPRWC.Webshop.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/api/v1/order")
@CrossOrigin("*")
public class OrderController {
    private final OrderDao orderDao;

    public OrderController(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postOrder(@RequestBody ArrayList<Order> orders) {
        if (orders.size() < 1) {
            return new ApiResponse(HttpStatus.BAD_REQUEST, "You do not have items in your cart!");
        }
        for (Order order : orders) {
            this.orderDao.saveToDatabase(order);
        }
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }
}
