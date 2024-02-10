package vttp.day21workshop.repo;

public class Queries {

    public static final String SQL_GET_ALL_CUSTOMERS = """
            select first_name, last_name
            from customers
            """;

    public static final String SQL_GET_CUSTOMER_FROM_ID = """
            select first_name, last_name
            from customers
            where id = ?
            """;

    public static final String SQL_GET_ORDERS_BY_ID = """
            select * 
            from orders
            where customer_id = ?
            """;
}
