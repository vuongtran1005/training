package com.bluebelt.training.entities;

import javax.persistence.*;

@Entity
@Table
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
}
