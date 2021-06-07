package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Getter
@Setter
@Entity
@Table(name = "BACKEND_AUDIT_TRAIL")
@EntityListeners(AuditingEntityListener.class)
public class BackendAuditrail {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "REQUEST_API")
    private String requestApi;

    @Column(name = "RESPONSE_API")
    private String responseApi;

    @Column(name = "REQUEST_PARAMETER")
    private String requestParameter;

    @Column(name = "HIT_DATE")
    private Date hitDate;
}
