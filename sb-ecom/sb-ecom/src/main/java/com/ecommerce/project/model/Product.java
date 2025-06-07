package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "products")
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long productId;

    @NotBlank
    @Size(min = 3, message = "product name cần chứa ít nhất 3 ký tự")
    @Column(name = "product_name")
    private String productName;

    @Column(name = "image")
    private String image;

    @NotBlank
    @Size(min = 6, message = "product description cần chứa ít nhất 6 ký tự")
    @Column(name = "description")
    private String description;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private double price; //100

    @Column(name = "discount")
    private double discount; //25

    @Column(name = "special_price")
    private double specialPrice; //75

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User user;
}
