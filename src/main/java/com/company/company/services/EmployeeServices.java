package com.company.company.services;

import com.company.company.dto.employee.ReqDto;
import com.company.company.dto.employee.ResDto;

import com.company.company.entities.Department;
import com.company.company.entities.Employee;
import com.company.company.entities.Project;

import com.company.company.repository.DepartmentRepository;
import com.company.company.repository.EmployeeRepository;
import com.company.company.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServices {

    //CRUD
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ResDto create(ReqDto dto){

        Department department = departmentRepository.findById(dto.getDepartment()).orElseThrow(()->new RuntimeException("department with the given id does not exist"));

        Project project = projectRepository.findById(dto.getProject()).orElseThrow(()->new RuntimeException("project with the given id does not exist"));

        Employee employee = new Employee(null, dto.getName(), department, project);
        Employee saved = employeeRepository.save(employee);

        department.addEmployee(employee);
        project.addEmployee(employee);

        return new ResDto(
                saved.getId(),
                saved.getName(),
                department.getName(),
                project.getName()
        );
    }

    public ResDto display( Long id ){

        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("employee with the given id does not exist"));
        Department department = employee.getDepartment();
        Project project = employee.getProject();

        return new ResDto(
                employee.getId(),
                employee.getName(),
                department.getName(),
                project.getName()
        );
    }

    public ResDto updateProject(Long id, ReqDto dto){

        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("employee with the given id does not exist"));
        Department department = employee.getDepartment();

        // old/ already assigned project (can be a null)
        Project project = employee.getProject();
        // dosent allow updating the project to null (Project level concern)
        Project projectNew = projectRepository.findById(dto.getProject()).orElseThrow(()->new RuntimeException("project with the given id does not exist"));

        // remove the employee from the old project
        if (project != null){
            project.removeEmployee(employee);
        }
        projectNew.addEmployee(employee);
        employee.changeProject(projectNew);

        return new ResDto(
                employee.getId(),
                employee.getName(),
                department.getName(),
                projectNew.getName()
        );
    }

    public ResDto delete(Long id){

        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("employee with the given id does not exist"));
        Department department = employee.getDepartment();
        Project project = employee.getProject();

        department.removeEmployee(employee);
        project.removeEmployee(employee);

        employeeRepository.delete(employee);

        return new ResDto(
                employee.getId(),
                employee.getName(),
                department.getName(),
                project.getName()
        );

    }

}