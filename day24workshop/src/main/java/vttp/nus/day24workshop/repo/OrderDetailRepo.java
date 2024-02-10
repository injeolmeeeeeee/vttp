package vttp.nus.day24workshop.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vttp.nus.day24workshop.model.OrderDetail;

public class OrderDetailRepo {

    @Autowired
    JdbcTemplate template;

    public boolean insertOrder(int orderId, List<OrderDetail> orderDetails) {

        int count = 0;

        for (OrderDetail orderDetail : orderDetails) {
            int inserted = template.update(Queries.SQL_INSERT_INTO_ORDERS, orderDetail.getProduct(), orderDetail.getUnitPrice(), orderDetail.getDiscount(), orderDetail.getQuantity());
            count += inserted;
        }
        System.out.printf("--- inserted: %d, actual: %d\n", count, orderDetails.size());
        return count == orderDetails.size();
    }
}
