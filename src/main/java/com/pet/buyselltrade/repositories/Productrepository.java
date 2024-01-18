package com.pet.buyselltrade.repositories;

import com.pet.buyselltrade.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Productrepository extends JpaRepository<ProductModel,Long> {
    List<ProductModel> findByTitle(String title);
}
