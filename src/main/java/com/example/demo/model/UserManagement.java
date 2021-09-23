package com.example.demo.model;


import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.management.relation.Role;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "USER_MANAGEMENT")
@EntityListeners(AuditingEntityListener.class)
public class UserManagement {

    @Id
    @NotNull
    @Column(name = "USERNAME", length = 50, nullable = false)
    private String username;

    @Column(name = "FIRSTNAME", length = 120, nullable = false)
    private String firstName;

    @Column(name = "LASTNAME", length = 120, nullable = false)
    private String lastName;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "PASSWORD", length = 120, nullable = false)
    private String password;

    @Column(name = "ROLEID")
    private int roleId;

    @ManyToOne
    @JoinColumn(name = "ROLEID", insertable = false, updatable = false)
    private RoleManagement roleManagement;
}
