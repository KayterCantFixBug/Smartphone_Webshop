package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int id;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public enum Status {
        DELIVERING, COMPLETE
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id")
    private List<OrderDetail> orderDetails;
    @Column(name = "date")
    private String Date;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "status")
    private Status status;
    @Column(name = "note")
    private String note;

    public Order() {

    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Order(String date) {
        Date = date;
    }

    public Order(int id, String address, String phone, String note) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.note = note;
    }

    public void addOrderDetail(OrderDetail orderDetail) {
        int code = orderDetail.getProduct().getId();
        int quantity = orderDetail.getQuantity();
        for (int i = 0; i < this.orderDetails.size(); i++) {
            OrderDetail orderDetail_temp = orderDetails.get(i);
            if (orderDetail_temp.getProduct().getId() == code) {
                orderDetail_temp.setQuantity(quantity);
                return;
            }
        }
        orderDetails.add(orderDetail);
    }
    public int getQuantityOrderDetail (OrderDetail orderDetail){
        int product_id = orderDetail.getProduct().getId();
        for (int i = 0; i < orderDetails.size(); i++) {
            OrderDetail orderDetail1_temp = orderDetails.get(i);
            if (orderDetail1_temp.getProduct().getId() == product_id) {
                return orderDetail1_temp.getQuantity();
            }
        }
        return -1;
    }

    public void removeOrderDetail(OrderDetail orderDetail) {
        int code = orderDetail.getProduct().getId();
        for (int i = 0; i < orderDetails.size(); i++) {
            OrderDetail orderDetail_temp = orderDetails.get(i);
            if (orderDetail_temp.getProduct().getId()==code) {
                orderDetails.remove(i);
                return;
            }
        }
    }

    public Order(User user, String date) {
        this.user = user;
        Date = date;
    }
}



