package com.alfansyah.produkku.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.alfansyah.produkku.daos.ProductDao;
import com.alfansyah.produkku.databases.LocalDatabase;
import com.alfansyah.produkku.entities.Product;

import java.util.List;

public class ProductRepository {

    private ProductDao productDao;
    private LiveData<List<Product>> products;

    public ProductRepository(Application application) {
        LocalDatabase db = LocalDatabase.getDatabase(application);
        productDao = db.productDao();
        products = productDao.getProducts();
    }

    public void insert(Product product) {
        LocalDatabase.databaseWriteExecutor.execute(()->productDao.insertProduct(product));
    }

    public void update(Product product) {
        LocalDatabase.databaseWriteExecutor.execute(() -> productDao.updateProduct(product));
    }

    public void delete(Product product) {
        LocalDatabase.databaseWriteExecutor.execute(() -> productDao.deleteProduct(product));
    }

    public LiveData<List<Product>> filteredProducts(String s) {
        return productDao.getFilteredProducts(s);
    }
}
