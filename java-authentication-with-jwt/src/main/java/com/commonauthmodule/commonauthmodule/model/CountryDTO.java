package com.commonauthmodule.commonauthmodule.model;

import lombok.Data;

@Data
public class CountryDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
