package com.example.demo.dao;

import com.example.demo.model.BackendAuditrail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BackendAuditrailDao extends JpaRepository<BackendAuditrail, String> {
}
