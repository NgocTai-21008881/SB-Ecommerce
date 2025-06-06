package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @NotBlank
    @Size(min = 3, message = "product name cần chứa ít nhất 3 ký tự")
    private String productName;
    private String image;
    @NotBlank
    @Size(min = 6, message = "product description cần chứa ít nhất 6 ký tự")
    private String description;
    private int quantity;
    private double price; //100
    private double discount; //25
    private double specialPrice; //75

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
