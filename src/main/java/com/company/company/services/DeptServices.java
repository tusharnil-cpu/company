package com.company.company.services;

import com.company.company.dto.department.ReqDto;
import com.company.company.dto.department.ResDto;
import com.company.company.entities.Department;
import com.company.company.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeptServices {

    //CRUD
    private final DepartmentRepository departmentRepository;

    public ResDto create(ReqDto dto){

        Department department = new Department(null, dto.getName(), null, null);
        Department saved = departmentRepository.save(department);

        return new ResDto(
                saved.getId(),
                saved.getName()
        );
    }

    public ResDto display(long id){

        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));

        return new ResDto(
                department.getId(),
                department.getName()
        );
    }

    public ResDto delete(long id){

        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
        departmentRepository.deleteById(id);

        return new ResDto(
                department.getId(),
                department.getName()
        );
    }
}