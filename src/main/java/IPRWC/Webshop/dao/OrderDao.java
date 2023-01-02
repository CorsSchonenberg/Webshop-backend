package IPRWC.Webshop.dao;

import IPRWC.Webshop.model.Order;
import IPRWC.Webshop.model.PromoCode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderDao {

    private final OrderRepository orderRepository;

    public OrderDao(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
}
