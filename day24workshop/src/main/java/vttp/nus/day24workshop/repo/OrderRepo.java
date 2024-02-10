package vttp.nus.day24workshop.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import vttp.nus.day24workshop.model.Order;

public class OrderRepo {

    @Autowired
    JdbcTemplate template;

    public boolean insertOrder(Order order) {
        return template.update(Queries.SQL_INSERT_INTO_ORDERS, 
                        order.getName(), order.getAddress(), order.getNotes(), order.getTax()) > 0;
    }
}
