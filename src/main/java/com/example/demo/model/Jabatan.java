package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "JABATAN")
@EntityListeners(AuditingEntityListener.class)
public class Jabatan {

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "KD_JABATAN", nullable = false)
    private Integer kdJabatan;

    @Column(name = "JABATAN", length = 60)
    private String jabatan;
}
