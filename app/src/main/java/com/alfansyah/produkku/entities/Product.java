package com.alfansyah.produkku.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.alfansyah.produkku.R;
import com.bumptech.glide.Glide;

import java.io.File;

@Entity(tableName = "products")
public class Product implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String photo, name, description;
    private long quantity, price;

    public Product(String photo, String name, long price, long quantity, String description) {
        this.photo = photo;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    protected Product(Parcel pl){
        id = pl.readLong();
        photo = pl.readString();
        name = pl.readString();
        description = pl.readString();
        quantity = pl.readLong();
        price = pl.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeLong(id);
        dest.writeString(photo);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeLong(quantity);
        dest.writeLong(price);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }


    @BindingAdapter("file")
    public static void setImage(ImageView view, String path) {
        if (TextUtils.isEmpty(path)) view.setImageResource(R.drawable.unnamed);
        else {
            File file = new File(path);

            if (file.exists()) Glide.with(view).load(file).into(view);
            else view.setImageResource(R.drawable.unnamed);
        }
    }
}
