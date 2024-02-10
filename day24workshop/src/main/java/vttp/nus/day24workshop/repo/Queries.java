package vttp.nus.day24workshop.repo;

public class Queries {
    
    //create order

    public static final String SQL_INSERT_INTO_ORDERS = """
            insert into orders(customer_name, ship_address, notes, tax)
            values (?, ?, ?, ?)
            """;
    
    //create order_details
    public static final String SQL_INSERT_INTO_ORDER_DETAIL = """
            insert into orders(product, unit_price, discount, quantity)
            values (?, ?, ?, ?)
            """;
}