package com.company.company.entities;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="project")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name="department_id", nullable = false)
    private Department department;

    public void changeDepartment(Department department){
        this.department = department;
    }

    @OneToMany(mappedBy = "project")
    private List<Employee> employees;
}