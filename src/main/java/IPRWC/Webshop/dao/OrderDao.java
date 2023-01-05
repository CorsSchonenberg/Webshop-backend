package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.Order;
import IPRWC.Webshop.model.PromoCode;
import IPRWC.Webshop.service.ReturnNewIdService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderDao {

    private final OrderRepository orderRepository;
    private final ReturnNewIdService returnNewIdService;

    public OrderDao(OrderRepository orderRepository, ReturnNewIdService returnNewIdService) {
        this.orderRepository = orderRepository;
        this.returnNewIdService = returnNewIdService;
    }

    public void saveToDatabase(Order order) {
        this.orderRepository.save(order);
    }
    public ArrayList<Order> getAllOrders() {
        return (ArrayList<Order>) this.orderRepository.findAll();
    }
    public void deleteOrderFromDatabase(Integer id) {
        this.orderRepository.deleteById(id);
    }

    public int giveOrderNewId() {
        ArrayList<Order> orders =
                (ArrayList<Order>) this.orderRepository.findAll();
        return this.returnNewIdService.returnNewOrderId(orders);
    }
}
