package com.alfansyah.produkku.viewmodels;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.alfansyah.produkku.entities.Product;
import com.alfansyah.produkku.repositories.ProductRepository;

import java.io.File;
import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private MutableLiveData<String> photo = new MutableLiveData<>("");
    private MutableLiveData<String> query = new MutableLiveData<>("%");
    private MutableLiveData<Product> product = new MutableLiveData<>();
    private LiveData<List<Product>> products;

    private ProductRepository repository;

    public void setPhoto(String photo) {
        this.photo.setValue(photo);
    }

    public void setProduct(Product product) {
        this.product.setValue(product);
    }

    public LiveData<String> getPhoto() {
        return photo;
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public ProductViewModel(@NonNull Application application) {
        super(application);

        repository = new ProductRepository(application);

        products = Transformations.switchMap(query, s -> repository.filteredProducts(s));
    }

    public void filter(String s) {
        String query = TextUtils.isEmpty(s) ? "%" : "%" + s + "%";
        this.query.postValue(query);
    }

    public void insertProduct(Product product) {
        repository.insert(product);
    }

    public void updateProduct(Product product) {
        repository.update(product);
    }

    public void deleteProduct(Product product) {
        File photo = new File(product.getPhoto());

        if (photo.exists() && photo.delete())
            System.out.println("DELETED: " + product.getPhoto());

        repository.delete(product);
    }

    public MutableLiveData<Product> getProduct() {
        return product;
    }
}
