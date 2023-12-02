package model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "line_item_id")
    private List<LineItem> lineItems;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_email")
    private User user;

    public Cart() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart(User user) {
        this.user = user;
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public void updateLineItem(LineItem lineItem, int quantity){
        int code = lineItem.getProduct().getId();
        for (int i = 0; i < this.lineItems.size(); i++) {
            if (lineItems.get(i).getProduct().getId() == code) {
                lineItems.get(i).setQuantity(quantity);
                return;
            }
        }

    }

    public int getQuantityItem (LineItem lineItem){
        int product_id = lineItem.getProduct().getId();
        for (int i = 0; i < lineItems.size(); i++) {
            LineItem lineItem_temp = lineItems.get(i);
            if (lineItem_temp.getProduct().getId() == product_id) {
                return lineItem_temp.getQuantity();
            }
        }
        return -1;
    }

    public void removeLineItem(LineItem lineItem) {
        int product_id = lineItem.getProduct().getId();
        for (int i = 0; i < lineItems.size(); i++) {
            LineItem lineItem_temp = lineItems.get(i);
            if (lineItem_temp.getProduct().getId()==product_id) {
                lineItems.remove(i);
                return;
            }
        }
    }
}