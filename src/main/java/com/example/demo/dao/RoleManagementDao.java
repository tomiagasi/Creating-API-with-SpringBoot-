package com.example.demo.dao;

import com.example.demo.model.RoleManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleManagementDao extends JpaRepository<RoleManagement, Integer> {
}
