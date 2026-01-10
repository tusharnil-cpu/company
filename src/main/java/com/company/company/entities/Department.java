package com.company.company.entities;

import java.util.List;
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
    private Long id;

    private String name;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @OneToMany(mappedBy = "department")
    private List<Project> projects;
}