package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEE")
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id
    @NotNull
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "CITY", length = 50)
    private String city;
}
