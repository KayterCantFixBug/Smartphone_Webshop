package model;

import jakarta.persistence.*;

@Entity
@Table(name = "storages")
public class Storage {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "storage_id")
    private int id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;
    private int quantity;

    public Storage (){}
    public Storage(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Storage(int id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}