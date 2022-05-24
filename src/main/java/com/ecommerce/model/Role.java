package com.ecommerce.model;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
