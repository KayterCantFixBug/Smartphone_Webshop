package service.impl;

import model.Product;
import service.IBase;
import service.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService, IBase<Product> {
    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public void insert(Product product) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Product findById(int id) {
        return null;
    }
}
