package com.commonauthmodule.commonauthmodule.repository;

import com.commonauthmodule.commonauthmodule.entity.User;
import com.commonauthmodule.commonauthmodule.entity.UserCurrency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCurrencyRepository extends JpaRepository<UserCurrency, Long> {

}
