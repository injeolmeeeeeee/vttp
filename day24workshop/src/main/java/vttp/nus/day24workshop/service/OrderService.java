package vttp.nus.day24workshop.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.nus.day24workshop.model.Order;
import vttp.nus.day24workshop.repo.OrderDetailRepo;
import vttp.nus.day24workshop.repo.OrderRepo;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderDetailRepo orderDetailRepo;
    
    public boolean createOrder(Order order) {
        int orderId = Integer.parseInt(UUID.randomUUID().toString().substring(0, 8));
        order.setOrderId(orderId);
    
        boolean orderInserted = orderRepo.insertOrder(order);
        boolean orderDetailsInserted = orderDetailRepo.insertOrder(orderId, order.getOrderDetails());
        //why boolean?
    
        return orderInserted && orderDetailsInserted;
    }
    
}
