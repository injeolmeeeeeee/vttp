package vttp.nus.day24workshop.model;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Order {

    private int orderId;
    private Date orderDate;
    private String name;
    private String address;
    private String notes;
    private float tax;
    private List<OrderDetail> orderDetails = new LinkedList<>();
    
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public float getTax() {
        return tax;
    }
    public void setTax(float tax) {
        this.tax = tax;
    }
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }
    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
    
}


// order_id
//  order_date
//  customer_name
//  ship_address
//  notes
// tax