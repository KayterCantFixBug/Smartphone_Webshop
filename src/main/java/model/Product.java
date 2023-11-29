package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "product_id")
    private int id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @Column(name = "price")
    private double price;
    @Column(name = "storage")
    private double storage;
    @Column(name = "ram")
    private double ram;
    @Column(name = "os")
    private String os;
    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Product (){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public double getStorage() {
        return storage;
    }

    public void setStorage(double storage) {
        this.storage = storage;
    }

    public double getRam() {
        return ram;
    }

    public void setRam(double ram) {
        this.ram = ram;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Product(int id, String name, Brand brand, double price, double storage, double ram, String os, String description) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.storage = storage;
        this.ram = ram;
        this.os = os;
        this.description = description;
    }

    public Product(String name, Brand brand, double price, double storage, double ram, String os, String description) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.storage = storage;
        this.ram = ram;
        this.os = os;
        this.description = description;
    }
}