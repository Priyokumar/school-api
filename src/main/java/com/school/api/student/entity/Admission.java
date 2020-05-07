package com.school.api.student.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "SC_ADMISSION")
public class Admission implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID", nullable = false, length = 100)
    private String id;

    @Column(name = "ADMISSION_REF_NO")
    private String admissionRefNo;

    @Column(name = "status")
    private String status;

    @Column(name = "ADMISSION_DATE")
    private Date admissionDate;

    @Column(name = "ADMISSION_AMOUNT")
    private Double admissionAmount;

    @Column(name = "PAID_AMOUNT")
    private Double paidAmount;

    @Column(name = "DUE_AMOUNT")
    private Double dueAmount;

    @Column(name = "PROMISE_TO_PAY_DATE")
    private Date promiseToPayDate;
}
