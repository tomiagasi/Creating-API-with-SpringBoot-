package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "MASTER_EMPLOYEE")
@EntityListeners(AuditingEntityListener.class)
public class Employee {

    @Id
    @NotNull
    @Column(name = "NIP", nullable = false)
    private Integer nip;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "TGL_LAHIR")
    private Date tglLahir;

    @Column(name = "KD_JABATAN")
    private Integer kdJabatan;

    @Column(name = "JENIS_KELAMIN", length = 50)
    private String jenisKelamin;

    @ManyToOne
    @JoinColumn(name = "KD_JABATAN", insertable = false, updatable = false)
    private Jabatan jabatan;
}
