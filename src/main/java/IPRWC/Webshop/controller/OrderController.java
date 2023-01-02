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

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ArrayList<Order>> orders() {
        ArrayList<Order> orders = this.orderDao.getAllOrders();
        return new ApiResponse(HttpStatus.ACCEPTED, orders);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse postOrder(@RequestBody Order order) {
        this.orderDao.saveToDatabase(order);
        return new ApiResponse(HttpStatus.ACCEPTED, "You posted some data!");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ApiResponse deleteOrder(@PathVariable Integer id) {
        this.orderDao.deleteOrderFromDatabase(id);
        return new ApiResponse(HttpStatus.ACCEPTED, "You deleted some data!");
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ApiResponse updateOrder(@RequestBody Order order) {
        this.orderDao.saveToDatabase(order);
        return new ApiResponse(HttpStatus.ACCEPTED, "You updated some data!");
    }
}
