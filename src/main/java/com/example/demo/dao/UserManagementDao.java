package com.example.demo.dao;

import com.example.demo.model.UserManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserManagementDao extends JpaRepository<UserManagement, String> {

    @Query("SELECT um "
            + "FROM UserManagement um WHERE lower(um.email) =:email")
    UserManagement findByEmail(@Param("email") String email);
}
