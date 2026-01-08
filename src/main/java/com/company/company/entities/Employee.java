package com.company.company.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="department_id", nullable = false)
    private Department department;

    public void changeDepartment(Department department){
        this.department = department;
    }

    @ManyToOne
    @JoinColumn(name="project_id", nullable = false)
    private Project project;

    public void changeProject(Project project){
        this.project = project;
    }
}