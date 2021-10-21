package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "ROLE_MANAGEMENT")
@EntityListeners(AuditingEntityListener.class)
public class RoleManagement extends Default {

    @Id
    @NotNull
    @Column(name = "ROLEID", nullable = false)
    private int roleId;

    @Column(name = "LASTNAME", length = 120, nullable = false)
    private String description;
}
