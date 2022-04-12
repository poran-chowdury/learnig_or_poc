package com.commonauthmodule.commonauthmodule.repository;

import com.commonauthmodule.commonauthmodule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    public User getUserByname(String username);
}
