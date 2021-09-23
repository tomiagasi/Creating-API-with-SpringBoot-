package com.example.demo.dao;

import com.example.demo.model.UserManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementDao extends JpaRepository<UserManagement, String> {
}
