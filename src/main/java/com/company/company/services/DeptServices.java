package com.company.company.services;

import com.company.company.dto.department.ReqDto;
import com.company.company.dto.department.ResDto;

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
public class DeptServices {

    //CRUD
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ResDto create(ReqDto dto){

        Department department = new Department(null, dto.getName(), null, null);
        Department saved = departmentRepository.save(department);

        return new ResDto(
                saved.getId(),
                saved.getName(),
                null,
                null
        );
    }

    public ResDto displayDetails(Long id){

        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));

        String[] employees = department.getEmployees()
                .stream()
                .map(Employee::getName)
                .toArray(String[]::new);

        String[] projects = department.getProjects()
                .stream()
                .map(Project::getName)
                .toArray(String[]::new);

        return new ResDto(
                department.getId(),
                department.getName(),
                employees,
                projects
        );
    }

    // also have to remove all the meployees and projects associated with that dept
    public ResDto delete(Long id){

        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));

        List<Employee> employees = department.getEmployees();
        List<Project> projects = department.getProjects();

        String[] employeesPrev = employees
                .stream()
                .map(Employee::getName)
                .toArray(String[]::new);

        String[] projectsPrev = projects
                .stream()
                .map(Project::getName)
                .toArray(String[]::new);

        // here before removeing the employee they must be removed from dept
        for (Employee employee: employees){

            department.removeEmployee(employee);
            Project projectEmp = employee.getProject();
            if (projectEmp != null) {
                projectEmp.removeEmployee(employee);
            }
            employeeRepository.delete(employee);
        }
        // similarly
        for (Project project: projects){
            department.removeProject(project);
            projectRepository.delete(project);
        }

        departmentRepository.deleteById(id);

        return new ResDto(
                department.getId(),
                department.getName(),
                employeesPrev,
                projectsPrev
        );
    }
}