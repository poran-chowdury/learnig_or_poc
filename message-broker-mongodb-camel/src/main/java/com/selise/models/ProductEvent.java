package com.selise.models;

import lombok.Builder;

import java.util.Date;

@Builder
public class ProductEvent {
    private String ProductId;
    private String Name;
    private String Discreption;
    private String Id;
    private Date CreatedUtc;

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDiscreption() {
        return Discreption;
    }

    public void setDiscreption(String discreption) {
        Discreption = discreption;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Date getCreatedUtc() {
        return CreatedUtc;
    }

    public void setCreatedUtc(Date createdUtc) {
        CreatedUtc = createdUtc;
    }
}
