package com.pet.buyselltrade.repositories;

import com.pet.buyselltrade.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <UserModel,Long> {
    UserModel findByEmail(String email);

}
