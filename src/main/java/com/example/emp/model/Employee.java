package com.example.emp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends AuditModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer Id;


    @Column(name = "first_name")
    @Basic(optional = false)
    private String firstName;

    @Column(name = "lsat_name")
    @Basic(optional = false)
    private String lastName;

    @Column(name = "dob")
    @Basic(optional = false)
    private Date dob;

    @Column(name = "email",unique = true)
    @Basic(optional = false)
    private String email;

}
