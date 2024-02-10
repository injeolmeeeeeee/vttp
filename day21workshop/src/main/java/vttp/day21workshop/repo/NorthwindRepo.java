package vttp.day21workshop.repo;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.day21workshop.model.Customer;
import vttp.day21workshop.model.Order;

@Repository
public class NorthwindRepo {

    @Autowired
    private JdbcTemplate template;

    public List<Customer> getAllCustomers(int offset, int limit) {
        try {
    
            String query = Queries.SQL_GET_ALL_CUSTOMERS + " LIMIT " + offset + "," + limit;
            SqlRowSet rs = template.queryForRowSet(query);

            List<Customer> customers = new LinkedList<>();

            while (rs.next()) {
                Customer customer = new Customer();
                String lastName = rs.getString("last_name");
                String firstName = rs.getString("first_name");
                customer.setLastName(lastName);
                customer.setFirstName(firstName);
                customers.add(customer);
            }

            return customers;
        } catch (Exception e) {
            throw new RuntimeException("Error while fetching customers with offset and limit", e);
        }
    }

    public Customer getCustomerByID(int id) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_GET_CUSTOMER_FROM_ID);
        Customer customer = new Customer();

        while ((rs.next())) {
            String lastName = rs.getString("last_name");
            String firstName = rs.getString("first_name");
            customer.setLastName(lastName);
            customer.setFirstName(firstName);
        }
        return customer;

    }
    
    public List<Order> getOrderByID(int customerId) {
        SqlRowSet rs = template.queryForRowSet(Queries.SQL_GET_CUSTOMER_FROM_ID);
        List<Order> orders = new LinkedList<>();
        Order order = new Order();

        while ((rs.next())) {
            int id = rs.getInt("id");
            customerId = rs.getInt("customer_id");
            Date date = rs.getDate("order_date");
            order.setId(id);
            order.setCustomerId(customerId);
            order.setOrderDate(date);
            orders.add(order);
        }
        return orders;
    }
}
