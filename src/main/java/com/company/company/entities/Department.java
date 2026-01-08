package com.company.company.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="department")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Department {

    // the following will generate unique ids
    // this is just like having a primary key and an auto incriment in mysql code
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}