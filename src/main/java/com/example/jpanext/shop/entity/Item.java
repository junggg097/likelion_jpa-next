package com.example.jpanext.shop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String description;
    private Integer price;
    @Setter
    // 재고는 주문이 들어오면 수량이 떨어지니까 setter 필요
    private Integer stock;

    @ManyToOne
    private Shop shop;
    @ManyToOne
    @JoinColumn(name="category_id")
    private ItemCategory itemCategory;

    @ManyToMany(mappedBy = "items")
    private final List<Order> orders = new ArrayList<>();
}
