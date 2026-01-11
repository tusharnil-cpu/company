package com.company.company.services;

import com.company.company.dto.project.ReqDto;
import com.company.company.dto.project.ResDto;

import com.company.company.entities.Department;
import com.company.company.entities.Employee;
import com.company.company.entities.Project;

import com.company.company.repository.DepartmentRepository;
import com.company.company.repository.EmployeeRepository;
import com.company.company.repository.ProjectRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServices {

    // CRUD
    private final ProjectRepository projectRepository ;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeesRepository;

    public ResDto create(Long dept_id, ReqDto dto){

        Department department = departmentRepository.findById(dept_id).orElseThrow(()->new RuntimeException("dept not found"));
        Project project = new Project(null, dto.getName(), department, null);
        Project saved = projectRepository.save(project);

        // add this project to the list of projects the dept holds
        department.addProject(project);

        return new ResDto(
                saved.getId(),
                saved.getName(),
                null,
                department.getName()
        );
    }

    public ResDto displayDetails( Long id ){

        Project project = projectRepository.findById(id).orElseThrow(()-> new RuntimeException("project with the given id does not exist"));
        Department department = project.getDepartment();

        String[] employee =  project.getEmployees()
                .stream()
                .map(Employee::getName)
                .toArray(String[]::new);

        return new ResDto(
                project.getId(),
                project.getName(),
                employee,
                department.getName()
        );

    }

    // here the catch is to set porject as null for all those employees assigned this project
    public ResDto delete(Long id, ResDto dto){

        Project project = projectRepository.findById(id).orElseThrow(()-> new RuntimeException("project with the given id does not exist"));
        Department department = project.getDepartment();
        List<Employee> employees = project.getEmployees();

        String[] empPrevious =  employees
                .stream()
                .map(Employee::getName)
                .toArray(String[]::new);

        for (Employee employee : employees){
            employee.changeProject(null);
        }

        projectRepository.deleteById(id);

        return new ResDto(
                project.getId(),
                project.getName(),
                empPrevious,
                department.getName()
        );
    }
}
