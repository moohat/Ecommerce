package com.submission.studio.moohat.bukatoko.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {

    @SerializedName("data")
    public List<Data> products;

    public List<Data> getProducts() {
        return products;
    }

    public class Data{
//        "id": 9,
//        "product": "Baju koko",
//        "price": 110000,
//        "stock": 99120,
//        "description": null,
//        "image":

        @SerializedName("id")
       private int id;
        @SerializedName("product")
       private String product;
        @SerializedName("price")
       private int price;
        @SerializedName("stock")
       private int stock;
        @SerializedName("image")
       private String image;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
